# Parking ticket pay machine project

This is a project which can help parking lot to manage It's ticket paying service. It is an automated ticketing system that facilitates interactions between user and parking ticket service.  



### *Project description & abilities*

• 		Receiving coin values : **0.5,** **1** , **2**;

•		Determining **length of service** according to the coin value inserted .


•		Once the ticket purchase is processed, all the details which contains **service duration, place and price data** is being stored in file.

•		**Administrator password-protected menu**. After password verification, admin would be able to **change** 30min, 1h, 2h, or 24h **parking ticket prices, change password** or even get a **history log** of all ticket purchases stored in file.




### *Installation*

*Software required to run this project :*

- **[JAVA 8](https://www.java.com/en/download/)**  / [Installation instructions](https://www.java.com/en/download/help/ie_online_install.xml)
- **[Eclipse](https://www.eclipse.org/downloads/)**  / [Installation instructions](https://www.eclipse.org/downloads/packages/installer) 
or 
- **[Intellij IDEA](https://www.jetbrains.com/idea/download/)** / [Installation instructions](https://www.jetbrains.com/help/idea/installation-guide.html)  




### *Usage*

After installation, open **/src folder** and run **ParkingMachineMain.java**. (Use CTRL + F11 to run in software).
It should display main menu with current date and time, pricing and 5 options to choose.


Using keyboard numbers  you should navigate trough menu.



 
- Press **1** if you want to add coin with value of 0.5KM;
- Press **2** if you want to add coin with value of 1KM;
- Press **3** if you want to add coin with value of 2KM;
- Press **4** if you want to purchase daily ticket. (This option is available only if amount you entered in machine equals with price of daily ticket).
- Press **5** if you want to use administrator menu named CONFIG.


Every time when coin is added, amount at display increases and shows time when service duration ends according to coins inserted. Once you inserted amount you needed, press number 0 to confirm. 
Purchase data should be stored in log file named “**Zone1Parking.txt**” or “**Zone1ParkingDaily.txt**”  if it's 24h reservation in “**/ParkingMachine_**” folder.

If you press number **5**, you should get a prompt to enter administrator password. If you enter wrong password it will keep prompting all the time until you enter valid one. ( Default password is : “**2020**” ).
If you entered valid password you should get **CONFIG menu** at display with 6 options. Just like the main menu, navigate through config menu using keyboard numbers to get option you want.

**1)**	  To set price for 30min;

**2)**	To set price for 1h;

**3)**	To set price for 2h;

**4)**	To set price for 24h (all day);

**5)**	To change administrator password.

**6)**	To exit CONFIG menu and get a history log of all ticket purchases and amount of money stored in cashbox.


