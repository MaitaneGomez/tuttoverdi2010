package rehearsalServer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

//CLASS THAT DEALS WITHE EVERY ASPECT OF THE RESERVATIONS DB,
//IMPPLEMENTING ITS INTERFACE

public class RehearsalServerDAO implements IRehearsalServerDAO{
	
	private static Connection conn;
	private static Statement stat;

	@Override
	//CONNECTION TO THE DB
	public void connect() {
		try 
		{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:db/rmi-db/reservations.db");
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Unable to connect to the database");
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Unable to connect to the database");
			e.printStackTrace();
		}
		
	}

	@Override
	//DISCONNECTION FROM THE DATABASE
	public void disconnect() {
		try 
		{
			conn.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Unable to disconnect from the database");
			e.printStackTrace();
		}
	
	}

	@Override
	//THIS METHOD ACCESSES TO THE RESERVATIONS DATABASE IN ORDER TO
	//CHECK IF THERE IS ANY RESERVATION FOR A GIVEN OPERAHOUSE AND OPERANAME
	//IT RETURNS THE NUMBER OF SEATS THAT ARE OCUPIED, IT MEANS, THE
	//NUMBER OF RESERVATIONS
	public int getReservationsCount(String operaHouse, String operaName) {

		String query = "select count (*) from reservationsT where operahouse = '" + operaHouse + "' and operaname = '" + operaName + "'";
		int reservedSeats=0;
		
		try 
		{
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(query);
			rs.next();
			reservedSeats = rs.getInt(1);
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Error obtainOcupiedSeats failed");
			e.printStackTrace();
		}
	
		return reservedSeats;
	}

	@Override
	//THIS METHOD INTRODUCE A NEW ROW IN THE TABLE OF RESERVATIONS
	//OF THE RESERVATIONS DB.
	//TO INTRODUCE IT, WE NEED A STUDEN NAME, THE OPERAHOUSE
	//AND THE OPERANAME HE/SHE WANTS TO RESERVE
	//IN ADDITION, THE DATE WHEN THE RESERVATION IS DONE
	//IS TAKEN FROM THE SYSTEM
	public void reserveSeat(String studName, String operaHouse, String operaName) {
		
		//WE OBTAIN THE DATE WHEN THE RESERVATION IS DONE
		Calendar c = Calendar.getInstance();
		String day = Integer.toString(c.get(Calendar.DATE));
		String month = Integer.toString(c.get(Calendar.MONTH));
		String year = Integer.toString(c.get(Calendar.YEAR));
		
		String date = day + "/" + month + "/" + year;
		
		String update = "insert into ReservationsT (STUDENT, OPERAHOUSE, OPERANAME, RESERVATIONDATE) VALUES( '" + studName + "','" + operaHouse + "','" + operaName + "','" + date + "')";
		try 
		{
			stat = conn.createStatement();
			stat.executeUpdate(update);
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
