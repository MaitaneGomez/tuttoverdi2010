package JMSOperaHouse.dao;

import java.sql.SQLException;
import java.util.List;

import JMSOperaHouse.RehearsalJMSDTO;

public class PruebaJMSDAO {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{

		JMSOperaHouseDAO dao =  new JMSOperaHouseDAO();
		try 
		{
			dao.connect();
			List<RehearsalJMSDTO> list = dao.getRehearsals();
			dao.disconnect();
			for (int i =0; i < list.size(); i++)
			{
				System.out.println(list.get(i).getOperaName() + " " + list.get(i).getDate() + " " + list.get(i).getSeats());
				
			}
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
