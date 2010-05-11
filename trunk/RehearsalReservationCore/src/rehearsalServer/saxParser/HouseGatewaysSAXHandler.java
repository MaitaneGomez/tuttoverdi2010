package rehearsalServer.saxParser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXParseException;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//THIS CLASS IS THE MOST IMPORTANT CLASS IN THE XML FIELD
//ITS AIM IS TO PARSE THE XML FILE AND CREAT GATEWAYOBJECTS
//WITH THE INFORMATION CONTAINED IN EACH TAG

public class HouseGatewaysSAXHandler extends DefaultHandler
{
	
	//ATTRIBUTES NEEDED. WE NEED A GATEWAYOBJECT BECAUSE IT IS GOING TO 
	//BE CREATE EACH TIME A NEW NODE IS DETECTED (<GATEWAY>)
	//THE ARRAYGATEWAYS IS NEEDED JUST TO STORE ALL THE GATEWAYOBJECTS
	//THAT WILL BE CREATED, TO ACCESS THEM LATER
	//DETAIL AND TEXT ARE NEEDED TO RETRIEVE INFORMATION FROM THE TAGS
	private GatewayObject gateway;
	private ArrayGateways arrayXMLGateways;
	private String detail = null;
	private String text = null;

	//METHOD THAT IS INVOKED WHEN THE BEGINING OF THE XML FILE IS DETECTED
	public void startDocument() throws SAXException
	{
		System.out.println("Parsing...");
		//WE CREATE AN INSTANCE OF THE ARRAYGATEWAYS
		this.arrayXMLGateways = new ArrayGateways();
		this.text = "";
	}
	
	//THIS METHOD IS INVOKED WHEN THE END OF THE XML FILE IS DETECTED
	public void endDocument() throws SAXException
	{
		System.out.println("Parsing end...");
	}
	
	
	//THIS METHOD IS INVOKED WHEN AN OPENNING TAG IS READ
	public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException
	{
		System.out.println(" * [Etiqueta] -> <" + qName + ">");
		
		//WHEN THE TAG "GATEWAY" IS DETECTED, WE HAVE TO CREATE A NEW
		//GATEWAYOBJECT THAT WILL BE ADDED INTO THE ARRAY OF THE 
		//ARRAYGATEWAYS
		if(qName.equals("service"))
		{
			this.gateway = new GatewayObject();
			this.arrayXMLGateways.addXMLGateway(gateway);
		}
		//IF THE TAG DETECTED IS "DETAIL" WE HAVE TO RETRIEVE IT
		//AND STORE IT IN THE CORRESPOND GATEWAYOBJECT (IN THE LIST OF DETAILS)
		else if(qName.equals("detail"))
			 {
			 	this.detail = attrs.getValue("name");
			 	this.gateway.setDetails(detail);
			 }
	}
	
	
	//THIS METHOD IS INVOKED WHEN A CLOSING TAG IS DETECTED
	public void endElement(String uri, String localName, String qName) throws SAXException
	{
		if(this.text.length() > 0)
		{
			System.out.println("	- [Texto] -> " + text);
			
			//THE ENDING TAG IS THE NAME OF THE SERVICE
			if(qName.equals("serviceName"))
			{
				this.gateway.setName(this.text);
			}
			//THE ENDING TAG IS THE TECHNOLOGY USED
			else if(qName.equals("tech"))
				 {
				 	this.gateway.setTech(this.text);
				 }
		}
		
		this.text = "";
		System.out.println(" * [Etiqueta] -> <" + qName + ">");
	}
	
	
	//THIS METHOD IS INVOKED WHEN THE PARSER FINDS A STRING SUCH AS "SCALAMILANO"
	public void characters(char[] ch, int start, int length) throws SAXException
	{
		
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
	
	
	//ARRAYGATEWAYS GETTER 
	public ArrayGateways getArrayXMLGateways()
	{
		return this.arrayXMLGateways;
	}
	
	//GATEWAY GETTER
	public GatewayObject getGateway()
	{
		return this.gateway;
	}
}
