package rehearsalServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReservationCounter {
	
	private static Connection conn;
	private static Statement stat;
	
	public static void connect() throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:db/rmi-db/reservations.db");
	}
	
	public static int obtainOcupiedSeats(String operaHouseName, String operaName) throws SQLException
	{
		//en este metodo accedemos a la base de datos de reservas para comprobar
		//si hay alguna reserva hecha para una determinada opera y una actuacion
		//devolviendonos asi el numero de asientos ocupados
		
		//tenemos que conectarnos a la base de datos de reservas
		
		String query = "select count (*) from reservationsT where operahouse = '" + operaHouseName + "' and operaname = '" + operaName + "'";
		stat = conn.createStatement();
		ResultSet rs = stat.executeQuery(query);
		rs.next();
		int reservedSeats = rs.getInt(1);
	
		return reservedSeats;
	}
	
	public static void disconnect() throws SQLException
	{
		conn.close();
	}

}
