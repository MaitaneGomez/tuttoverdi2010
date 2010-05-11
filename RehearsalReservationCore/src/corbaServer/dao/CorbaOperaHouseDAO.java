package corbaServer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import corbaServer.RehearsalDO;


//this class is the one which is going to deal with every aspect
//of the DB (sanCarloNapoli or scalaMilano)

public class CorbaOperaHouseDAO implements ICorbaOperaHouseDAO {
	
	private Connection conn;
	private Statement stat;
	String dataBaseName=""; //need to be used by different servers
	
	
	//connection to the DB
	public void connect(String dataBase) throws ClassNotFoundException, SQLException {
		
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:db/corba-db/" + dataBase + ".db");
		dataBaseName = dataBase;

	}

	
	//method that queries the DB in order to obtain the rehearsals of 
	//an operaHouse
	public List<RehearsalDO> getRehearsals() throws SQLException {
		List<RehearsalDO> rehearsals = new ArrayList<RehearsalDO>();
		String query = "select * from rehearsalst";
		stat = conn.createStatement();
		
		ResultSet rs = stat.executeQuery(query);
		
		//convertion of the resultSet into a list of rehearsalDO
		while(rs.next())
		{
			String operaName = rs.getString(1);
			String date = rs.getString(2) ;
			int seats = rs.getInt(3);
			
			RehearsalDO temp = new RehearsalDO(operaName,date,seats);
			rehearsals.add(temp);
		}
		rs.close();
		stat.close();

		return rehearsals;
	}

	
	//disconnection of the DB
	public void disconnect() throws SQLException {
		conn.close();
	}
}
