package corbaServer.corba;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import corbaServer.RehearsalDO;
import corbaServer.dao.CorbaOperaHouseDAO;

//this class implements the remote method of the IDL interface
//it means, the remote method that corba servers are going to provide
//which is getRehearsals from scalaMilano and SanCarloNapoli

public class impCorbaServer extends ICorbaServerPOA{
	
	//attribute which is used to save the name of the DB we want to access
	//depending on the operaHouse
	private String DBName="";

	
	//constructor. Initializes the name of the dataBase
	public impCorbaServer(String dataBaseName)
	{
		DBName = dataBaseName;
	}
	
	
	//Remote method. It obtains the rehearsals from the dataBase
	//it can be scalaMilano or SanCarloNapoli, deppending on the name
	//of the DB
	public corbaServerRehearsalDTO[] getRehearsals() 
	{
		
		//creation of a DAO object to invoke the methods which are going 
		//to access to de DB
		CorbaOperaHouseDAO COHD = new CorbaOperaHouseDAO();
		
		//Creation of a list to store the results of the DB methods (DAO)
		List<RehearsalDO> DOList = new ArrayList<RehearsalDO>();
		
		//Creation of the list which is going to be returned by the method
		corbaServerRehearsalDTO [] DTOArray = null ; 
		
		//Now we deal with the connection, obtaining and disconnection of
		//the DB (DAO)
		
		try
		{
			COHD.connect(DBName);
			DOList = COHD.getRehearsals();
			COHD.disconnect();
			DTOArray = new corbaServerRehearsalDTO [DOList.size()];
			
			//convertion from RehearsalDO to corbaServerRehearsalDTO
			for(int i = 0; i< DOList.size();i++)
			{
				DTOArray[i] = new corbaServerRehearsalDTO(DOList.get(i).getOperaName(),DOList.get(i).getDate(),DOList.get(i).getSeats());	
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
		
		return DTOArray;
	}

}
