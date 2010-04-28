package service.dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import service.data.RehearsalDTO;

public interface IEuskaldunaBioWSDAO 
{
	
	public void connect() throws NamingException, SQLException;

	public List<RehearsalDTO> getRehearsals() throws SQLException;

	public void disconnect() throws SQLException;

}
