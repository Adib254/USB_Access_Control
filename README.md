# USB_Access_Control

This was my CSE323 (Operating system design) course's project.
Here I demonstrated how you can control access to USB devices via a hardware finger print system.
Companies can use such a system to restrict access to unauthorized USB devices in their systems and protect their employees from being victims of attacks such as drop drive hacks.

 <br />
 How it works:
 <br />
 1. Admin creates a list of pendrive to be whitelisted. This gets stored in the control server's database.  <br />
 2. When a whitelisted pendrive is entered into a machine, its given all the access a pendrive can have. <br />
 3. If a blacklisted pendrive is entered into a machine:  <br />
 &nbsp;-That pendrive will be instantly unmounted.<br />
 &nbsp;-A log would be created about the unauthorized entry into the control server's database.<br />
 &nbsp;-A realtime notification would be sent to the machine's user's android smartphone about the event.<br />
 &nbsp;-A consecutive of 3 times entry would put the whole USB system of that machine in lockdown and start off a loud alarm. Only the admin and the user can turn off the alarm and undo the system lockdown through admin panel and android app respectively.<br />
 4. In order to add a new pendrive to the whitelist, the desktop application has to be logged in to the admin panel and click on the Add to whitelist button. Then a prompt would tell you to insert the new pendrive. Once inserted, the fingerprint of that pendrive would be registered and updated into the control server's database and a success message would be displayed.<br />
 <br />
 Thats all about it, thanks for showing your interest in my project :)
