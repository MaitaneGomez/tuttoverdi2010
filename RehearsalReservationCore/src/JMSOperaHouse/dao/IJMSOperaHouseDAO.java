package JMSOperaHouse.dao;

import java.sql.SQLException;
import java.util.List;

import JMSOperaHouse.RehearsalJMSDTO;



public interface IJMSOperaHouseDAO 
{
	
	public void connect() throws ClassNotFoundException, SQLException;

	public List<RehearsalJMSDTO> getRehearsals() throws SQLException;

	public void disconnect() throws SQLException;

}
