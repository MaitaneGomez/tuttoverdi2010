package rehearsalServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Clase auxiliar para gestionar la base de datos de reservas (Reservations)
public class ReservationCounter {
	
	private static Connection conn;
	private static Statement stat;
	
	public static void connect() 
	{
		
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
	
	public static int obtainOcupiedSeats(String operaHouseName, String operaName) 
	{
		//En este metodo accedemos a la base de datos de reservas para comprobar
		//si hay alguna reserva hecha para una determinada opera y una actuacion
		//devolviendonos asi el numero de asientos ocupados
		
		//Tenemos que conectarnos a la base de datos de reservas
		String query = "select count (*) from reservationsT where operahouse = '" + operaHouseName + "' and operaname = '" + operaName + "'";
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
	
	public static void reserve(RehearsalRMIDTO DTO, String student)
	{
		String update = "insert into ReservationsT (STUDENT, OPERAHOUSE, OPERANAME, RESERVATIONDATE) VALUES( '" + student + "','" + DTO.getOperaHouse() + "','" + DTO.getOperaName() + "','" + DTO.getDate() + "')";
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
	
	public static void disconnect()
	{
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

}
