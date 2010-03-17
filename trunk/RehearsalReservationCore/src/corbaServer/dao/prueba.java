package corbaServer.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import corbaServer.RehearsalDO;

public class prueba {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		CorbaOperaHouseDAO base = new CorbaOperaHouseDAO();
		List <RehearsalDO> lista = new ArrayList<RehearsalDO>();
		
		try 
		{
			base.connect("scalaMilano");
			lista = base.getRehearsals();
			
			for(int i = 0; i< lista.size();i++)
			{
				System.out.println(lista.get(i).getOperaName() + lista.get(i).getDate() + lista.get(i).getSeats());
				
			}
			
			base.disconnect();
			
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
