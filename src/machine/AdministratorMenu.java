package machine;
import java.util.ArrayList;
import java.util.Scanner;

import database.DatabaseStorage;



public class AdministratorMenu {
	private DatabaseStorage ds = new DatabaseStorage();
	private CoinManagementSystem cms = CoinManagementSystem.getInstance();
	private Scanner scan = new Scanner(System.in);
	private String loginPassword = "2020";
	private String PW = "";
	private String keyboard = "";
	
	private double setPrice;
	private String setPassword;
 
	public void config() {

		try {

			while (!PW.equals(loginPassword)) { // PW = 2020

				// MENU DISPLAY
				System.out.println("Please log in. Type PW : ");
				PW = scan.next();

			}

			// CONFIG MENU
			System.out.println(" SET PRICE \n 1) 30min  \n 2) 1h \n 3) 2h \n 4) 24h \n 5) PW change \n 6) LOG & BACK");
			keyboard = scan.next();

			switch (keyboard) {

			case "1":

				System.out.println("Set price for 30min = _._ KM");
				setPrice = scan.nextDouble();
				cms.setHalfHour(setPrice);
				System.out.println("Price successfully changed!");
				break;

			case "2":

				System.out.println("Set price for 1h = _._ KM");
				setPrice = scan.nextDouble();
				cms.setPrice1Hour(setPrice);
				System.out.println("Price successfully changed! ");
				break;

			case "3":

				System.out.println("Set price for 2h = _._ KM");
				setPrice = scan.nextDouble();
				cms.setPrice2Hours(setPrice);
				System.out.println("Price successfully changed! ");
				break;

			case "4":

				System.out.println("Set price for 24h = _._ KM");
				setPrice = scan.nextDouble();
				cms.setPrice1Day(setPrice);
				System.out.println("Price successfully changed! ");
				break;

			case "5":

				System.out.println("Enter new PW");
				setPassword = scan.next();
				setLoginPassword(setPassword);
				System.out.println("PW successfully changed! ");
				break;

			case "6":
				
				displayDatabaseContent();
				break;

			}
			PW = "";
		}

		catch (Exception e) {
			System.out.println("Something went wrong with administrator menu !");
			e.printStackTrace();
		}

		finally {

		}
	}

	
	private void displayDatabaseContent() {  // method for displaying database content with ArrayList
	
		ArrayList <ParkingTicket> userInfo = new ArrayList<ParkingTicket> ();
		ds.fetchDatabaseContent(userInfo);
		System.out.println(userInfo);
	}
	
	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
}
