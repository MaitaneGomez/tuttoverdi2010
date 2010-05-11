package service;

import service.dao.*;
import service.data.RehearsalDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;


public class EuskaldunaService {
    
	public RehearsalDTO[] getRehearsals()
	{
		RehearsalDTO[] array = null;
		EuskaldunaBioWSDAO dao = new EuskaldunaBioWSDAO();
		try 
		{
			dao.connect();
			array = dao.getRehearsals();
			dao.disconnect();
		} 
		catch (NamingException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return array;
		
	}
	
}
