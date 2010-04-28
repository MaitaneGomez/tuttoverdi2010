package service.dao;

import java.sql.SQLException;

import javax.naming.NamingException;

import service.data.RehearsalDTO;

public interface IEuskaldunaBioWSDAO 
{
	
	public void connect() throws NamingException, SQLException;

	public RehearsalDTO[] getRehearsals() throws SQLException;

	public void disconnect() throws SQLException;

}
