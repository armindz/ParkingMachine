
// import java.time.DayOfWeek;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import database.DatabaseStorage;
import machine.AdministratorMenu;
import machine.CoinManagementSystem;
import machine.ParkingTicket;

public class ParkingMachineMain {

	private static void menuDisplayContent(Date startDate, double halfHour, double hour, double twoHours,
			double allDay) {
		// MENU DISPLAY
		System.out.println("\n\n\n\n\n\n\n------------------\n " + startDate.toString());
		System.out.println("\n\n \t\t\t\t PARKING LOT");
		System.out.println("On Sunday parking is for free");
		System.out.println("Price : 30min= " + halfHour + "KM 1h= " + hour + "KM 2h= " + twoHours + "KM 24H=" + allDay);
		System.out.println(
				"\n Insert coins. \n   1) 0.5 KM |\n   2)  1 KM  |\n   3)  2 KM  |\n\n   4) Daily ticket |\n   5) CONFIG       |\n\n\n 0)  ENTER  |");
		System.out.println("Press 0 when done! ");

	}

	public static void main(String[] args) {

		try {

			ArrayList<ParkingTicket> usageHistory = new ArrayList<>();
			CoinManagementSystem cms = CoinManagementSystem.getInstance();
			AdministratorMenu adminMenu = new AdministratorMenu();

			Scanner scanner = new Scanner(System.in);
			Date startDate = new Date();
			Calendar endDate = Calendar.getInstance();
			endDate.setTime(startDate);

			String keyboard = "42";
			double coinAmount = 0;
			int duration;
			double overallCoinAmount = 0;
			System.out.println(startDate);

			if (cms.isSaturday(startDate)) {
				cms.setSaturdayPrice(0.3, 0.6, 1, 5);
			}

			if (cms.isSunday(startDate)) {
				cms.setSundayPrice(0, 0, 0, 0);
			}

			while (true) { // Infinite loop to keep program going

				double halfHour = cms.getHalfHourPrice();
				double hour = cms.getPrice1Hour();
				double twoHours = cms.getPrice2Hours();
				double allDay = cms.getPrice1Day();
				DatabaseStorage dst = new DatabaseStorage();

			
				// MENU DISPLAY

				menuDisplayContent(startDate, halfHour, hour, twoHours, allDay);

				while (!keyboard.equals("0")) {

					System.out.println("Amount: " + coinAmount);

					if (coinAmount > 0 && cms.coinValidation(coinAmount)) {

						duration = cms.getDuration();
						endDate.setTime(startDate);
						endDate.add(Calendar.MINUTE, duration);

						System.out.println("\nTicket expiration time");
						System.out.println(endDate.getTime());

					}

					keyboard = scanner.next();

					switch (keyboard) {

					case "1":

						// Inserting 0.5KM
						coinAmount += 0.5;
						cms.coinValidation(coinAmount);
						break;

					case "2":

						// Inserting 1KM
						coinAmount += 1;
						cms.coinValidation(coinAmount);
						break;

					case "3":

						// Inserting 2KM
						coinAmount += 2;
						cms.coinValidation(coinAmount);
						break;

					case "4":

						// PARKING TICKET FOR WHOLE DAY
						if (coinAmount >= allDay) {
							cms.purchaseDailyTicket(coinAmount);
							ParkingTicket ticket = new ParkingTicket(coinAmount, startDate, endDate, cms.getDuration());
							usageHistory.add(ticket);
							overallCoinAmount += coinAmount;
							coinAmount = 0;
						}

						else {

							System.out.println(coinAmount + "<" + allDay);
						}

						break;

					case "5":

						// PARKING MACHINE ADMINISTRATOR MENU LOGIN

						adminMenu.config();
						
						// USERS PARKING MACHINE USAGE INFO
						System.out.println(usageHistory.toString());
						System.out.println(usageHistory.size() + " users.");
						System.out.println("Overall amount in cashbox: " + overallCoinAmount);
						keyboard = "0";
						continue;
					}

				}

				overallCoinAmount += coinAmount;

				// IF RETURNS TRUE ALLOW TICKET PURCHASING
				if (coinAmount != allDay && coinAmount != 0.0 && cms.coinValidation(coinAmount)) {

					cms.purchaseIndividualTicket(coinAmount);
					ParkingTicket individualTicket = new ParkingTicket(coinAmount, startDate, endDate, cms.getDuration());
					usageHistory.add(individualTicket);

				}

			
					

				

				// SET PARAMETERS TO DEFAULT VALUE FOR NEXT LAP
				keyboard = "42";
				coinAmount = 0;
				duration = 0;

			}

		}

		catch (Exception e) {

			System.out.println("Something went wrong!");
			e.printStackTrace();
		}

	}
}
