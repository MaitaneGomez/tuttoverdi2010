package corbaServer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import corbaServer.RehearsalDO;

public class CorbaOperaHouseDAO implements ICorbaOperaHouseDAO {
	
	Connection conn;
	Statement stat;
	String dataBaseName="";
	
	public void connect(String dataBase) throws ClassNotFoundException, SQLException {
		
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:db/corba-db/" + dataBase + ".db");
		dataBaseName = dataBase;

	}

	public List<RehearsalDO> getRehearsals() throws SQLException {
		List<RehearsalDO> rehearsals = new ArrayList<RehearsalDO>();
		String query = "select * from rehearsalst";
		stat = conn.createStatement();
		
		ResultSet rs = stat.executeQuery(query);
		
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

	public void disconnect() throws SQLException {
		conn.close();
	}
}
