package rehearsalServer;

import java.rmi.RemoteException;
import java.util.List;

import rehearsalServer.houseGateway.IOperaHGateway;
import rehearsalServer.houseGateway.OperasHGatewayFactory;
import rehearsalServer.houseGateway.RehearsalDO;

public class PruebaFactory {

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException 
	{
		OperasHGatewayFactory  gate = OperasHGatewayFactory.getInstance();
		IOperaHGateway corbaGate = gate.getOperaHGateway("", "corba");
		List <RehearsalDO> lista = corbaGate.getRehearsals();
		System.out.println(lista.size());
		for(int i = 0; i < lista.size(); i++)
		{
			System.out.println(lista.get(i).getOperaName() + " " + lista.get(i).getAvailableSeats() + " "+ lista.get(i).getDate());
		}
	
	}

}
