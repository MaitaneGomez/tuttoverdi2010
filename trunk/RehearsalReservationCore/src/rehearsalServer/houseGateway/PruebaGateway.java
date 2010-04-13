package rehearsalServer.houseGateway;

import java.util.List;

//Prueba para el gateway de corba, ya no lo necesitamos
public class PruebaGateway {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		CorbaHouseGateway gate = new CorbaHouseGateway("scalaMilano");
		List<RehearsalDO> list = gate.getRehearsals();
		for(int i = 0; i < list.size(); i++)
		{
			System.out.println(list.get(i).getOperaName() + " " + list.get(i).getAvailableSeats() + " "+ list.get(i).getDate());
		}
		
		
		// TODO Auto-generated method stub

	}

}
