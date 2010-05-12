package service.dao;

import java.sql.SQLException;

import javax.naming.NamingException;

import service.data.RehearsalDTO;

//THIS INTERFACE DECLARES THE METHODS THAT ARE GOING TO BE PROVIDED 
//BY THE EUSKALDUNABIOWSDAO
//ALL THESE METHODS HAVE TO DEAL WITH THE EUSKALDUNA DB

public interface IEuskaldunaBioWSDAO 
{
	
	public void connect() throws NamingException, SQLException;

	public RehearsalDTO[] getRehearsals() throws SQLException;

	public void disconnect() throws SQLException;

}
