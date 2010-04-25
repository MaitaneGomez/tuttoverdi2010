package rehearsalServer.saxParser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ArrayGateways implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private List<GatewayObject> arrayXMLGateways = null;
	
	public ArrayGateways()
	{
		this.arrayXMLGateways = new ArrayList<GatewayObject>();
	}
	
	public void addXMLGateway(GatewayObject gate)
	{
		this.arrayXMLGateways.add(gate);
	}
	
	public List<GatewayObject> getArrayXMLGateway()
	{
		return this.arrayXMLGateways;
	}
}
