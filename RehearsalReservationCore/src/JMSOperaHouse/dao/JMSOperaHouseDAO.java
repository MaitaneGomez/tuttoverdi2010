package JMSOperaHouse.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import JMSOperaHouse.RehearsalJMSDTO;

//THIS CLASS DEALS WITH EVERY ASPECT OF THE DATABASE OF LONDON
//IT IMPLEMENTS ITS INTERFACE

public class JMSOperaHouseDAO implements IJMSOperaHouseDAO 
{
	private  Connection conn;
	private  Statement stat;

	@Override
	//CONNECTION
	public void connect() throws ClassNotFoundException, SQLException 
	{
	
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:db/jms-db/royalOperaLondon.db");
	}

	@Override
	//DISCONNECTION
	public void disconnect() throws SQLException 
	{
		conn.close();
	}

	@Override
	//METHOD THAT ACCESSES TO THE DATABASE (LONDON) AND OBTAINS THE 
	//REHEARSALS OF IT
	public List<RehearsalJMSDTO> getRehearsals() throws SQLException 
	{
		List<RehearsalJMSDTO> rehearsals = new ArrayList<RehearsalJMSDTO>();
		String query = "select *  from rehearsalsT";
		stat = conn.createStatement();
		
		ResultSet rs = stat.executeQuery(query);
		
		//CONVERTION FROM THE RESULTSET TO REHEARSALJMSDTO OBJECTS
		while(rs.next())
		{
			String operaName = rs.getString(1);
			String date = rs.getString(2) ;
			int seats = rs.getInt(3);
			
			RehearsalJMSDTO newRehearsalJMSDTO = new RehearsalJMSDTO(operaName, date, seats);
			rehearsals.add(newRehearsalJMSDTO);
		}
		rs.close();
		stat.close();
		
		return rehearsals;
	}

}
