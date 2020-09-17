package database;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;



import machine.CoinManagementSystem;

public class DatabaseStorage {

	private static String statementToStoreData = "INSERT INTO Parking"
			+ "(UserID, EntryTime, ExitTime, Duration, TicketPrice) values " + " (?,?,?, ?, ?);";
	private static String statementToDisplayData = "SELECT * FROM Parking";
	
	CoinManagementSystem cms = CoinManagementSystem.getInstance();

	public void storeToDatabase() {
		try {

			DateFormat dateFormat = new SimpleDateFormat("HH:mm dd-MM-yyyy");
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(statementToStoreData);

			

			preparedStmt.setInt(1, 3); // UserID Column
			preparedStmt.setString(2, dateFormat.format(cms.getStartDate()));	// EntryTime Column
			preparedStmt.setString(3, dateFormat.format(cms.getEndDate().getTime())); // ExitTime Column
			preparedStmt.setInt(4, cms.getDuration());  // Duration Column
			preparedStmt.setDouble(5, cms.getTicketPrice());  // TicketPrice Column

			preparedStmt.execute();

			conn.close();
			preparedStmt.close();

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ResultSet displayDatabaseContent() {

		try {

			Connection conn = DatabaseConnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(statementToDisplayData);

			System.out.println("UserID  |  Entry time  |  Exit time  |  Duration  |  Ticket price");
			
			while (rset.next()) {
				System.out.println(rset.getString(1) + " | " + rset.getString(2) + " | " + rset.getString(3) + " | "
						+ rset.getInt(4) + "min. | " + rset.getDouble(5) + "KM");
			}

			conn.close();
			stmt.close();
			rset.close();
			return rset;
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}