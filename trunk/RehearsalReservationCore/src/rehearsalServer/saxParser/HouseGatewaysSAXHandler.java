package rehearsalServer.saxParser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXParseException;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HouseGatewaysSAXHandler extends DefaultHandler
{
	
	private GatewayObject gateway;
	private ArrayGateways arrayXMLGateways;
	private String detail = null;
	private String text = null;

	public void startDocument() throws SAXException
	{
		//se invoca cuando se detecta que el documento empieza
		System.out.println("Parsing...");
		this.arrayXMLGateways = new ArrayGateways();
		this.text = "";
	}
	
	public void endDocument() throws SAXException
	{
		//se invoca cuando detecta que el docuemnto a acabado.
		System.out.println("Parsing end...");
	}
	
	public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException
	{
		System.out.println(" * [Etiqueta] -> <" + qName + ">");
		
		if(qName.equals("service"))
		{
			this.gateway = new GatewayObject();
			this.arrayXMLGateways.addXMLGateway(gateway);
		}
		else if(qName.equals("detail"))
			 {
			 	this.detail = attrs.getValue("name");
			 	this.gateway.setDetails(detail);
			 }
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException
	{
		if(this.text.length() > 0)
		{
			System.out.println("	- [Texto] -> " + text);
			if(qName.equals("serviceName"))
			{
				this.gateway.setName(this.text);
			}
			else if(qName.equals("tech"))
				 {
				 	this.gateway.setTech(this.text);
				 }
		}
		
		this.text = "";
		System.out.println(" * [Etiqueta] -> <" + qName + ">");
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException
	{
		//esto es para cuando encuentra una cadena de texto como scalaMILANO
		
		String s = String.valueOf(ch, start, length).trim();
		this.text += s;
	}
	
	public void processingInstruction(String target, String data) throws SAXException
	{
		System.out.println(" *  [PI] -> <?" + target + data + "?>");
	}
	
	//*********************************************************************//
	//********************METODOS PARA ERRORES*****************************//
	//*********************************************************************//
	
	
	public void error(SAXParseException exception) throws SAXException
	{
		System.out.println("[ERROR!!!!!] -->" + exception.getMessage());
	}
	
	public void warning(SAXParseException exception) throws SAXException
	{
		System.out.println("[WARNING!!!!!] -->" + exception.getMessage());
	}
	
	public void fatalError(SAXParseException exception) throws SAXException
	{
		System.out.println("[FATAL ERROR!!!!!] -->" + exception.getMessage());
	}
	
	public ArrayGateways getArrayXMLGateways()
	{
		return this.arrayXMLGateways;
	}
	
	public GatewayObject getGateway()
	{
		return this.gateway;
	}
}
