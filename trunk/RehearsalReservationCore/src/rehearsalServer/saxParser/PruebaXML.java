package rehearsalServer.saxParser;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import rehearsalServer.houseGateway.IOperaHGateway;
import rehearsalServer.houseGateway.OperasHGatewayFactory;

public class PruebaXML 
{

	
	public static void main(String[] args)
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setValidating(true);
		
		try
		{
			System.out.println("Parsing using SAX...");
			
			SAXParser saxParser = factory.newSAXParser();
			HouseGatewaysSAXHandler handler = new HouseGatewaysSAXHandler();
			saxParser.parse(args[0], handler);
			
			ArrayGateways arrayXMLGateways = new ArrayGateways();
			arrayXMLGateways = handler.getArrayXMLGateways();
			
			//ahora nos creamos el array de gateways pero estos ya son gateways reales
			//no son nodos del xml
			
	
			
			//ahora recorremos el arrayXML para coger los datos de los distintos
			//servicios
			
			int size = arrayXMLGateways.getArrayXMLGateway().size();
			System.out.println(size);
			
			for(int i =0; i < size; i++)
			{
				String serviceUri = "";
				GatewayObject gate = arrayXMLGateways.getArrayXMLGateway().get(i);
				System.out.println(gate.getName());
				System.out.println("	" + gate.getTech());
				List<String> details = gate.getDetails();
				System.out.println("	" + details.size());
				for(int j =0; j < details.size(); j++)
				{
					System.out.println("		" + details.get(j));
					serviceUri = serviceUri + " " + details.get(j);
				}
				serviceUri = serviceUri + " " + gate.getName();
				System.out.println(serviceUri);
				
			}
		}
		catch (Exception e)
		{
			System.out.println("ERROR --> Main(): " + e.getMessage());
			e.printStackTrace();
		}
	}
}