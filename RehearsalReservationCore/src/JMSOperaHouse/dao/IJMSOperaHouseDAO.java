package JMSOperaHouse.dao;

import java.sql.SQLException;
import java.util.List;

import JMSOperaHouse.RehearsalJMSDTO;

//INTERFACE WHICH IS GOING TO DECLARE THE METHODS THAT ARE GOING 
//TO BE IMPLEMENTED BY THE DAO OF JMS

public interface IJMSOperaHouseDAO 
{
	
	public void connect() throws ClassNotFoundException, SQLException;

	public List<RehearsalJMSDTO> getRehearsals() throws SQLException;

	public void disconnect() throws SQLException;

}
