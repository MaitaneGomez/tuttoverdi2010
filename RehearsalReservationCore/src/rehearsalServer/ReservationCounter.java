package rehearsalServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			e.printStackTrace();
		}
		 catch (SQLException e) 
		 {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int obtainOcupiedSeats(String operaHouseName, String operaName) 
	{
		//en este metodo accedemos a la base de datos de reservas para comprobar
		//si hay alguna reserva hecha para una determinada opera y una actuacion
		//devolviendonos asi el numero de asientos ocupados
		
		//tenemos que conectarnos a la base de datos de reservas
		
		int reservedSeats = 0;
		try
		{
			String query = "select count (*) from reservationsT where operahouse = '" + operaHouseName + "' and operaname = '" + operaName + "'";
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(query);
			rs.next();
			reservedSeats = rs.getInt(1);
		}
		catch(SQLException e)
		{}
	
		return reservedSeats;
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
			e.printStackTrace();
		}
	}

}
