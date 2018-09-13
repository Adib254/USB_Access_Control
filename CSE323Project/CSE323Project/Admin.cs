using Microsoft.Win32;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Microsoft.VisualBasic;
using System.Net;

namespace CSE323Project
{
    public partial class Admin : Form
    {

        public bool stopMonitor = false;
        public bool whitelisting = false;
        public bool lockdownoverride = false;
        public Admin()
        {
            InitializeComponent();
        }

        protected override void OnFormClosing(FormClosingEventArgs e)
        {
            stopMonitor = false;
            base.OnFormClosing(e);
            if (e.CloseReason == CloseReason.UserClosing)
            {
                e.Cancel = true;
                Hide();
            }
        }

        private void Admin_Load(object sender, EventArgs e)
        {

        }

        private void button3_Click(object sender, EventArgs e)
        {
            USB_enableAllStorageDevices();
            lockdownoverride = true;

            WebClient client = new WebClient();
            string url = "http://cse323.ddns.net/cse323/addlog.php?P=12345&log=LockdownOverride";
            client.DownloadData(url);


        }

        void USB_enableAllStorageDevices()
        {
            Microsoft.Win32.RegistryKey key = Registry.LocalMachine.OpenSubKey
                ("SYSTEM\\CurrentControlSet\\Services\\UsbStor", true);
            if (key != null)
            {
                key.SetValue("Start", 3, RegistryValueKind.DWord);
            }
            key.Close();
        }

        private void REG_EnableRegedit()
        {
            RegistryKey key = Registry.CurrentUser.OpenSubKey
                ("Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\System", true);
            key.SetValue("DisableRegistryTools", 0, RegistryValueKind.DWord);
            key.Close();
        }

        private void REG_DisableRegedit()
        {
            RegistryKey key = Registry.CurrentUser.OpenSubKey
                ("Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\System", true);
            key.SetValue("DisableRegistryTools", 1, RegistryValueKind.DWord);
            key.Close();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            REG_DisableRegedit();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            REG_EnableRegedit();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            
            MessageBox.Show("Enter a pendrive to add it to the whitelist");
            whitelisting = true;
        }
    }
}
