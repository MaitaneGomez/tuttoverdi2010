package service;

import java.sql.SQLException;
import javax.naming.NamingException;
import service.dao.EuskaldunaBioWSDAO;
import service.data.RehearsalDTO;

//THIS CLASS IMPLEMENTS THE SERVICE THE EUSKALDUNABIO WS OFFERS
//IT ONLY OFFERS ONE METHOD: GETREHEARSALS.
//TO DO THIS, WE NEED A DAO, TO DEAL WITH THE DB


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
