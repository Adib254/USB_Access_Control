using Microsoft.Win32;
using System;
using System.IO;
using System.Net;
using System.Security.Cryptography;
using System.Text;
using System.Threading;
using System.Windows.Forms;
using Microsoft.VisualBasic;
using Newtonsoft.Json;


namespace CSE323Project
{
    public partial class formMain : Form
    {

        private static readonly string CR = Environment.NewLine;
        private UsbManager manager;

        USBEject usb = new USBEject();
        int invalidcounter = 0;


        Admin admin = new Admin();

        public formMain()
        {
            InitializeComponent();

            disableAutoRun();
            REG_DisableRegedit();
            manager = new UsbManager();
            UsbDiskCollection disks = manager.GetAvailableDisks();

            textBox.AppendText(CR);
            textBox.AppendText("Available USB disks" + CR);

            foreach (UsbDisk disk in disks)
            {
                textBox.AppendText(disk.ToString() + CR);
                textBox.AppendText("Serial: "+ getUSBSerial(disk.Name) + CR);

                string hash = sha256(getUSBSerial(getUSBSerial(disk.Name)));
                if (!checkAgainstWhitelist(hash))
                {

                    usb.Eject(disk.Name);

                }

            }

            textBox.AppendText(CR);

            manager.StateChanged += new UsbStateChangedEventHandler(DoStateChanged);

        }


        private void DoStateChanged(UsbStateChangedEventArgs e)
        {
            if (admin.lockdownoverride)
            {
                invalidcounter = 0;
                SendNotificationFromFirebaseCloud("Lockdown Manual Override by Admin");

            }

            if (admin.whitelisting)
            {
                Thread threadx = new Thread(() =>
                {
                    WebClient client = new WebClient();

                    

                    string hash = sha256(getUSBSerial(e.Disk.Name));

                    string url = "http://cse323.ddns.net/cse323/addwhitelist.php?P=12345&hash="+ hash;
                    client.DownloadData(url);

                    url = "http://cse323.ddns.net/cse323/addlog.php?P=12345&log=NewWhitelisting";
                    client.DownloadData(url);
                    MessageBox.Show("USB Drive added to the whitelist!");
                    SendNotificationFromFirebaseCloud("New USB Drive added to whitelist");
                });
                threadx.Start();
                threadx.Join(); //wait for the thread to finish
            }

            admin.whitelisting = false;

            if (admin.stopMonitor)
            {
                return;
            }

            textBox.AppendText(e.State + " " + e.Disk.ToString() + CR);

            Thread thread = new Thread(() =>
            {
                

                string hash = sha256(getUSBSerial(e.Disk.Name));
                if (!checkAgainstWhitelist(hash))
                {
                    
                    usb.Eject(e.Disk.Name);
                    
                }
                
            });
            thread.Start();
            thread.Join(); //wait for the thread to finish
        }

        private bool checkAgainstWhitelist(string sl)
        {
            WebClient client = new WebClient();

            string url = "http://cse323.ddns.net/cse323/getwhitelist.php";
            byte[] html = client.DownloadData(url);
            UTF8Encoding utf = new UTF8Encoding();
            string mystring = utf.GetString(html);


            string hash = sha256(sl);


            if (!string.IsNullOrEmpty(sl) && mystring.ToLower().Contains(sl))
            {
                if (invalidcounter < 3)
                {
                    invalidcounter = 0;
                }

                return true;
            }
            else
            {
                if (!string.IsNullOrEmpty(sl))
                {
                    url = "http://cse323.ddns.net/cse323/addlog.php?P=12345&log=USBBlocked";
                    client.DownloadData(url);
                    invalidcounter++;
                    SendNotificationFromFirebaseCloud("Blocked an unauthorized access!");
                    if (invalidcounter >= 3)
                    {
                        System.Media.SoundPlayer player = new System.Media.SoundPlayer();
                        player.SoundLocation = "Sound.wav";
                        player.Play();
                        USB_disableAllStorageDevices();
                        
                        url = "http://cse323.ddns.net/cse323/addlog.php?P=12345&log=USBSystemLockDown";
                        client.DownloadData(url);
                        SendNotificationFromFirebaseCloud("USBSYSTEM IN LOCKDOWN!");



                    }

                }
                
                return false;
            }


        }

        private void REG_DisableRegedit()
        {
            RegistryKey key = Registry.CurrentUser.OpenSubKey
                ("Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\System", true);
            key.SetValue("DisableRegistryTools", 1, RegistryValueKind.DWord);
            key.Close();
        }

        void USB_disableAllStorageDevices()
        {
            RegistryKey key = Registry.LocalMachine.OpenSubKey
                ("SYSTEM\\CurrentControlSet\\Services\\UsbStor", true);
            if (key != null)
            {
                key.SetValue("Start", 4, RegistryValueKind.DWord);
            }
            key.Close();
            timer1.Enabled = true;
        }

        void USB_enableAllStorageDevices()
        {
            RegistryKey key = Registry.LocalMachine.OpenSubKey
                ("SYSTEM\\CurrentControlSet\\Services\\UsbStor", true);
            if (key != null)
            {
                key.SetValue("Start", 3, RegistryValueKind.DWord);
            }
            key.Close();
            timer1.Enabled = false;
        }


        static string sha256(string str)
        {
            try
            {
                using (SHA256 sha256Hash = SHA256.Create())
                {
                    // ComputeHash - returns byte array  
                    byte[] bytes = sha256Hash.ComputeHash(Encoding.UTF8.GetBytes(str));

                    // Convert byte array to a string   
                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < bytes.Length; i++)
                    {
                        builder.Append(bytes[i].ToString("x2"));
                    }
                    return builder.ToString();
                }
            }
            catch { return null; }
            
        }


        private string getUSBSerial(string drive)
        {
            USBSerialNumber usbSL = new USBSerialNumber();
            string serial = usbSL.getSerialNumberFromDriveLetter(drive);
            return serial;

        }


        private void disableAutoRun()
        {
            RegistryKey Rkey;

            Rkey = Registry.LocalMachine.OpenSubKey("Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer", true);
            try
            {
                Rkey.SetValue("NoDriveTypeAutoRun", 255); //disable for all media types, recommended 
                                                          //Rkey.SetValue("NoDriveTypeAutoRun", 95); //disable
                                                          //Rkey.SetValue("NoDriveTypeAutoRun", 145); //enable
            }
            catch
            {
                Rkey = Registry.LocalMachine.OpenSubKey("Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer", true);
                Rkey.CreateSubKey("NoDriveTypeAutoRun");
                Rkey = Registry.LocalMachine.OpenSubKey("Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\NoDriveTypeAutoRun", true);
                Rkey.SetValue("NoDriveTypeAutoRun", 255);

            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            string x =Interaction.InputBox("Admin login", "Enter Password", "", -1, -1);
            if (!string.IsNullOrEmpty(x) && x == "12345")
            {
                admin.Show();
                admin.stopMonitor = true;
            }
                
        }

        public void SendNotificationFromFirebaseCloud(string xx)
        {
          
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            WebClient client = new WebClient();
            string url = "http://cse323.ddns.net/cse323/getremotecommand.php?P=12345";
            byte[] html = client.DownloadData(url);
            UTF8Encoding utf = new UTF8Encoding();
            string mystring = utf.GetString(html);


            if (mystring.Contains("unlock"))
            {
                USB_enableAllStorageDevices();
                invalidcounter = 0;

            }


        }
    }

}
