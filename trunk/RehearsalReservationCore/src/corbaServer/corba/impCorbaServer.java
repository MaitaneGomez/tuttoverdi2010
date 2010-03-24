package corbaServer.corba;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import corbaServer.RehearsalDO;
import corbaServer.dao.CorbaOperaHouseDAO;

//esta clase es la que implementa el metodo remoto de la interfaz idl

public class impCorbaServer extends ICorbaServerPOA{
	
	private String DBName="";

	
	public impCorbaServer(String dataBaseName)
	{
		DBName = dataBaseName;
	}
	
	public corbaServerRehearsalDTO[] getRehearsals() {
		
		//creaamos un obejto para llamar a los metodos de la base de datos
		CorbaOperaHouseDAO COHD = new CorbaOperaHouseDAO();
		
		//Creamos la lista para ser retornada por la base de datos:
		List<RehearsalDO> DOList = new ArrayList<RehearsalDO>();
		
		//creamos la lista que va a ser retornada:
		corbaServerRehearsalDTO [] DTOArray = null ; 
		
		//llevamos a cabo la invocacion de conectar, obtencion y desconexion
		
		try
		{
			COHD.connect(DBName);
			DOList = COHD.getRehearsals();
			DTOArray = new corbaServerRehearsalDTO [DOList.size()];
			
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
