package rehearsalServer.saxParser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//THIS CLASS IS A CONTAINER CLASS THAT HAS A LIST OF GATEWAY OBJECTS
//IT IS USED TO STORE THE NODES OF THE XML FILE ONCE IT HAS BEEN PARSED
//THE ATTRIBUTE IS USED LATER BY THE CREATION OF THE CACHE, TO CREATE
//AN ARRAY OF GATEWAYS, WITH THE INFORMATION COINTAINED IN THE OBJECTS
//OF THE ARRAY

public class ArrayGateways implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//THIS LIST IS GOING TO HAVE THE NODES OF THE XML FILE
	//IN THE FORM OF OBJECTS
	private List<GatewayObject> arrayXMLGateways = null;
	
	//CONSTRUCTOR
	public ArrayGateways()
	{
		this.arrayXMLGateways = new ArrayList<GatewayObject>();
	}
	
	
	//THIS METHOD ALLOWS US TO ADD A NEW NODE (GATEWAYOBJECT)
	//IN THE ARRAY
	public void addXMLGateway(GatewayObject gate)
	{
		this.arrayXMLGateways.add(gate);
	}
	
	//GETTER
	public List<GatewayObject> getArrayXMLGateway()
	{
		return this.arrayXMLGateways;
	}
}
