package rehearsalServer;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import rehearsalServer.loginGateway.ValidationException;

public class PruebaParaMAP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try 
		{
			OperaRehearsalServer server = new OperaRehearsalServer(args);	
			Map<String, Map<String, RehearsalRMIDTO>> cache = server.getRehearsalCache();
			

			Iterator<Entry<String, Map<String, RehearsalRMIDTO>>> itGeneralMap = cache.entrySet().iterator();
			while(itGeneralMap.hasNext())
			{
				Map.Entry<String, Map<String, RehearsalRMIDTO>> entry = (Map.Entry<String, Map<String, RehearsalRMIDTO>>) itGeneralMap.next();
				System.out.println(entry.getKey());
				Map<String,RehearsalRMIDTO> internalMap = entry.getValue();
				Iterator<Entry<String, RehearsalRMIDTO>> itInternalMap = internalMap.entrySet().iterator();
				while(itInternalMap.hasNext())
				{
					Map.Entry<String, RehearsalRMIDTO> entry2 = (Entry<String, RehearsalRMIDTO>) itInternalMap.next();
					RehearsalRMIDTO DTO = entry2.getValue();
					System.out.println("       " + DTO.getOperaHouse() + " " + DTO.getOperaName() + " " + DTO.getDate()+ " " + DTO.getAvailableSeats());
				}
			}
			
			System.out.println("el usuario es: " + server.login("stud1", "1111"));
			
		} 
		
		
		catch (RemoteException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidationException e)
{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
