
/*                       		  Mehanizam primanja kovanica u vrijednosti od 0.5, 1 i 2KM

	                        -  metoda prima dva argumenta: broj kovanica, vrijednost kovanice
		                 - metoda ispisuje grešku ukoliko vrijednost kovanice nije jedna od gore navedenih
		                     - ukoliko metoda ispiše grešku, pitati korisnika da ponovo “ubaci” kovanice                       */

package machine;

import java.util.Date;
import database.DatabaseStorage;
import java.util.Calendar;

public class CoinManagementSystem {

	private double halfHourPrice = 0.5;
	private double price1Hour = 1;
	private double price2Hours = 2;
	private double price1Day = 15;
	private int duration = 0;
	private double ticketPrice;
	private final int TWENTY_FOUR_HOURS_INTO_MINUTES = 1440;
	private final int ONE_DAY_LENGTH_IN_HOURS = 24;
	private Date startDate;
	private Calendar endDate;
	private Date startWorking;
	private Date stopWorking;
	private static CoinManagementSystem ic = new CoinManagementSystem();

	// private constructor
	private CoinManagementSystem() {
	}

	public static CoinManagementSystem getInstance() { // singleton method to get instance from CoinManagementSystem
		return ic;
	}
	
	
	public boolean coinValidation(double coinAmount) {	// first step of validating coin amount

		if (coinAmount == halfHourPrice) {
			duration = 30;
			return true;
		}

		if (coinAmount == price1Hour) {
			duration = 60;
			return true;
		}

		if (coinAmount == price2Hours) {

			duration = 120;
			return true;
		}

		if (coinAmount != price2Hours || coinAmount != price1Hour || coinAmount != halfHourPrice) {

			double duration1 = (coinAmount / price1Hour) * 60;
			duration = (int) duration1;

			if (duration % 30 == 0)

				return true;
		}

		return false;

	}


	public void purchaseIndividualTicket(double coinAmount) {	// method for purchasing ticket depending on amount inserted

		startDate = new Date();
		endDate = Calendar.getInstance();

		endDate.setTime(startDate);

		// if inserted amount is equal to machines given prices, set duration and ending date&time according to coin amount
		
		if (coinAmount == halfHourPrice) {					
			duration = 30;
			endDate.add(Calendar.MINUTE, duration);
		}

		if (coinAmount == price1Hour) {
			duration = 60;
			endDate.add(Calendar.MINUTE, duration);

		}

		if (coinAmount == price2Hours) {
			duration = 120;
			endDate.add(Calendar.MINUTE, duration);

		}

		if (coinAmount != price2Hours && coinAmount != price1Hour && coinAmount != halfHourPrice) {
			double duration1 = (coinAmount / price1Hour) * 60;
			duration = (int) duration1;
			if (duration % 30 == 0)
				endDate.add(Calendar.MINUTE, duration);

		}
		
		ticketPrice = coinAmount; 
		displayIndividualTicketInfo(startDate, coinAmount, duration, endDate);
		
		storeIndividualTicketToDatabase();

	}

	private void displayIndividualTicketInfo(Date startDate, double coinAmount, int duration, Calendar endDate) {

		System.out.println("\n\n\n\n Current time \n" + startDate);
		System.out.println("Coin amount: " + coinAmount + " KM ");
		System.out.println("\nTime remaining \n" + duration / 60 + " hrs " + duration % 60 + "min");

		System.out.println("\nTicket expiration time");
		System.out.println(endDate.getTime());
	}

	public void storeIndividualTicketToDatabase() {
		
		try {
			DatabaseStorage dst = new DatabaseStorage();
			dst.storeToDatabase();

		}

		catch (Exception e) {

			System.out.println("Something went wrong !");
			e.printStackTrace();
		}

		finally {
			
		}
	}

	

	public void purchaseDailyTicket(double coinAmount) {

		if (coinAmount >= price1Day) {	// if coin amount is equal to parking machines daily ticket price, set duration and ending date&time

			startDate = new Date();
			endDate = Calendar.getInstance();

			endDate.setTime(startDate);
			endDate.add(Calendar.HOUR_OF_DAY, ONE_DAY_LENGTH_IN_HOURS);
			duration = TWENTY_FOUR_HOURS_INTO_MINUTES;
			ticketPrice = coinAmount;
			
			displayDailyTicketInfo(startDate, endDate);
			storeDailyTicketToDatabase();
			
			
		}

		else if (coinAmount < price1Day) {		// if amount is not enough, notify user
			System.out.println("Not enough coins to buy daily ticket.");

		}

	}

	private void displayDailyTicketInfo(Date startDate, Calendar endDate) {

		System.out.println("Current time \n" + startDate);
		System.out.println("DAILY TICKET");
		System.out.println("\nTime remaining \n" + 24 + " hrs");

		System.out.println("\nTicket expiration time");
		System.out.println(endDate.getTime());

	}

	public void storeDailyTicketToDatabase () {
		try {

			DatabaseStorage dst = new DatabaseStorage();
			dst.storeToDatabase();

		}

		catch (Exception e) {

			System.out.println("Something went wrong !");
			e.printStackTrace();
		}

	}

	@SuppressWarnings("deprecation")
	public boolean isSaturday(Date currentDate) {  // mechanism for determining whether a particular date falls on a Saturday

		if (currentDate.getDay() == 6) {
			return true;
		}

		return false;
	}

	@SuppressWarnings("deprecation")
	public boolean isSunday(Date currentDate) {   // mechanism for determining whether a particular date falls on a Sunday

		if (currentDate.getDay() == 7) {
			return true;
		}

		return false;
	}

	

	public void setSaturdayPrice(double halfHourPrice, double hourPrice, double twoHoursPrice, double allDayPrice) {

		this.halfHourPrice = halfHourPrice;
		this.price1Hour = hourPrice;
		this.price2Hours = twoHoursPrice;
		this.price1Day = allDayPrice;

	}

	public void setSundayPrice(double halfHourPrice, double hourPrice, double twoHoursPrice, double allDayPrice) {

		this.halfHourPrice = halfHourPrice;
		this.price1Hour = hourPrice;
		this.price2Hours = twoHoursPrice;
		this.price1Day = allDayPrice;
	}

	public boolean isWorkingHour(Date currentDate) {

		if (currentDate.after(startWorking) && currentDate.before(stopWorking)) {
			return true;
		}

		return false;
	}

	public Date getStartWorking() {
		return startWorking;
	}

	public void setStartWorking(int startHour) {
	

		startWorking.setHours(startHour);
	}

	public Date getStopWorking() {
		return stopWorking;
	}

	public void setStopWorking(int stopHour) {
		
		stopWorking.setHours( stopHour);
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}


	public double getHalfHourPrice() {

		return halfHourPrice;
	}

	public void setHalfHour(double halfHour) {

		this.halfHourPrice = halfHour;

	}

	public double getPrice1Hour() {
		return price1Hour;
	}

	public void setPrice1Hour(double price1Hour) {
		this.price1Hour = price1Hour;
	}

	public double getPrice2Hours() {
		return price2Hours;
	}

	public void setPrice2Hours(double price2Hours) {
		this.price2Hours = price2Hours;
	}

	public double getPrice1Day() {
		return price1Day;
	}

	public void setPrice1Day(double price1Day) {
		this.price1Day = price1Day;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
}
