package database;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import machine.ParkingTicket;

import machine.CoinManagementSystem;

public class DatabaseStorage {

	private static String statementToStoreData = "INSERT INTO Parking"
			+ "(Parking_Ticket_ID, Entry_Time, Exit_Time, Duration, Ticket_Price) values " + " (?,?,?, ?, ?);";
	private static String statementToDisplayData = "SELECT * FROM Parking";

	ParkingTicket parkingTicket;
	CoinManagementSystem cms = CoinManagementSystem.getInstance();

	public void storeToDatabase(ParkingTicket ticket) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("HH:mm dd-MM-yyyy");
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(statementToStoreData);

			preparedStmt.setInt(1, generateParkingTicketId()); // Parking_Ticket_ID Column
			preparedStmt.setString(2, dateFormat.format(ticket.getStartDate().getTime())); // Entry_Time Column
			preparedStmt.setString(3, dateFormat.format(ticket.getEndDate().getTime())); // Exit_Time Column
			preparedStmt.setInt(4, ticket.getDuration()); // Duration Column
			preparedStmt.setDouble(5, ticket.getTicketPrice()); // Ticket_Price Column

			preparedStmt.execute();

			conn.close();
			preparedStmt.close();

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ArrayList<ParkingTicket> fetchDatabaseContent(ArrayList<ParkingTicket> userList) {  // mechanism for fetching content from database and returning as ArrayList

		try {
			DateFormat dateFormat = new SimpleDateFormat("HH:mm dd-MM-yyyy");
			Connection conn = DatabaseConnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(statementToDisplayData);

			while (rset.next()) {

				Date date = dateFormat.parse(rset.getString("Exit_Time"));
				Calendar endDate = Calendar.getInstance();
				endDate.setTime(date);

				ParkingTicket pt = new ParkingTicket(rset.getDouble("Ticket_Price"),
						dateFormat.parse(rset.getString("Entry_Time")), endDate, rset.getInt("Duration"));
				userList.add(pt);

			}

		}

		catch (Exception e) {

			System.out.println("Something went wrong");
			e.printStackTrace();
		}
		return userList;

	}

	private int generateParkingTicketId() {			// mechanism for generating parking ticket ID

		try {

			Connection conn = DatabaseConnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(statementToDisplayData);
			int ticketID = 0;
			while (rs.next()) {
				
				if (rs.isLast()) {
					ticketID = rs.getInt("Parking_Ticket_ID");
					ticketID++;
				}
			}

			return ticketID;

		}

		catch (Exception e) {
			System.out.println("Something went wrong with generating Parking_Ticket_ID");
			e.printStackTrace();
		}

		return 0;

	}
}