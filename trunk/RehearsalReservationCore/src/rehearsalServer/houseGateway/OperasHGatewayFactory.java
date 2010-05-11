package rehearsalServer.houseGateway;

//SINGLETON
public class OperasHGatewayFactory {

	/**
	 * ADD your code here; this class must be a SINGLETON String serviceUri =
	 * ip, port and name of the service String serverTech = technology of the
	 * server, 'corba' or 'ws'
	 */
	
	//THE AIM OF THIS CLASS IS TO CREATE GATEWAYS TO ACCESS THE DIFFERENT
	//OPERAHOUSES. IT IS A SINGLETON THAT'S WHY IT DOESN'T HAVE
	//A COMMON CONSTRUCTOR
	
	
	private static OperasHGatewayFactory instance = null;
	
	//METHOD THAT RETURN AN INSTANCE OF THE FACTORY
	public static OperasHGatewayFactory getInstance()
	{
		if(instance == null)
		{
			instance = factoryInstance();
		}
		return instance;
	}
	
	//PRIVATE CONSTRUCTOR THAT IS GOING TO BE USED BY THE GETINSTANCE
	//USERS ARE NEVER GOING TO HAVE ACCESS TO THIS METHOD
	private static OperasHGatewayFactory factoryInstance() 
	{
		return new OperasHGatewayFactory();
	}
	
	
	//METHOD THAT RETURNS A GATEWAY DEPENDING ON THE TECHNOLOGY
	//THAT WANTS TO BE USED. IT CALLS TO THE DIFFERENT CONSTRUCTOR
	//PASSING THE SERVICEURI (IP+PORT+NAME; QUEUENAME; URL)
	//SERVICEURI + SERVERTECH HAVE BEEN RETRIEVED FROM THE XML
	public IOperaHGateway getOperaHGateway(String serviceUri, String serverTech) 
	{
		IOperaHGateway the_gateway = null;

		if(serverTech.equals("corba"))
			the_gateway = new CorbaHouseGateway(serviceUri);
		if(serverTech.equals("ws"))
			the_gateway = new WSHouseGateway(serviceUri);
		if(serverTech.equals("jms"))
			the_gateway = new JMSHouseGateway(serviceUri);
		return the_gateway;
	}
}
