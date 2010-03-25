package rehearsalServer.houseGateway;

public class OperasHGatewayFactory {

	/**
	 * ADD your code here; this class must be a SINGLETON String serviceUri =
	 * ip, port and name of the service String serverTech = technology of the
	 * server, 'corba' or 'ws'
	 */
	
	private static OperasHGatewayFactory instance = null;
	
	public static OperasHGatewayFactory getInstance()
	{
		if(instance == null)
		{
			instance = factoryInstance();
		}
		return instance;
	}
	
	private static OperasHGatewayFactory factoryInstance() 
	{
		return new OperasHGatewayFactory();
	}
	
	public IOperaHGateway getOperaHGateway(String serviceUri, String serverTech) 
	{
		IOperaHGateway the_gateway = null;

		if(serverTech.equals("corba"))
			the_gateway = new CorbaHouseGateway("scalaMilano");		
		return the_gateway;
	}
}
