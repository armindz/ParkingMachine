

package machine;
import java.util.Calendar;
import java.util.Date;

public class ParkingTicket {
	
	private Date startDate;
	private Calendar endDate;
	private double ticketPrice;
	private int duration;
	
	
	

	public ParkingTicket (double ticketPrice, Date startDate, Calendar endDate, int duration) {
		
		this.startDate = startDate;
		this.endDate = endDate;
		this.ticketPrice = ticketPrice;
		this.duration = duration;
	}

	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "DATA LOG: \n \t\t\t  \n FROM: " + startDate + "\n TO: " + endDate.getTime() + "\n Price: " + ticketPrice
				+ "\n Duration " + duration + "\n-------------------------- \n";
	
	}
	}



