package rehearsalServer.houseGateway;

public class OperasHGatewayFactory {

	/**
	 * ADD your code here; this class must be a SINGLETON String serviceUri =
	 * ip, port and name of the service String serverTech = technology of the
	 * server, 'corba' or 'ws'
	 */
	public IOperaHGateway getOperaHGateway(String serviceUri, String serverTech) {
		IOperaHGateway the_gateway = null;

		return the_gateway;
	}
}
