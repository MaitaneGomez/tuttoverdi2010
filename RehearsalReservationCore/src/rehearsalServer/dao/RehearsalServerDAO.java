package rehearsalServer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class RehearsalServerDAO implements IRehearsalServerDAO{
	
	private static Connection conn;
	private static Statement stat;

	@Override
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
	public int getReservationsCount(String operaHouse, String operaName) {
		//En este metodo accedemos a la base de datos de reservas para comprobar
		//si hay alguna reserva hecha para una determinada opera y una actuacion
		//devolviendonos asi el numero de asientos ocupados
		
		//Tenemos que conectarnos a la base de datos de reservas
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
	public void reserveSeat(String studName, String operaHouse, String operaName) {
		//reservationDate es la fecha actual del sistema
		//aqui cojemos la fecha del sistema:
		
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
