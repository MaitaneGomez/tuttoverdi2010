package rehearsalServer.saxParser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// One Gateway Object per gateway in the XML file
// For the 2nd assignment

//THIS CLASS REPRESENTS THE OBJECT THAT ARE GOING TO BE CREATE
//WITH THE INFORMATION THAT IS IN EACH XML NODE, WHICH ARE IN FACT
//THE DATA OF A GATEWAY
public class GatewayObject implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//ATTRIBUTES NEEDED TO DEAL WITH THE BEHAVIOUR OF THE CLASS
	//EVERY XML NODE HAS A NAME, A TECHNOLOGY AND A SERIE OF PARAMETERS
	//SUCH AS IP, PORT, URL...
	private String name = null;
	private String tech = null;
	private List<String> details = null;
	
	
	//CONSTRUCTOR, WE INITIALIZE THE LIST OF DETAILS
	public GatewayObject()
	{
		details = new ArrayList<String>();
	}

	//NAME GETTER
	public String getName() 
	{
		return name;
	}

	//NAME SETTER
	public void setName(String name) 
	{
		this.name = name;
	}

	//TECHNOLOGY GETTER
	public String getTech() 
	{
		return tech;
	}

	//TECHNOLOGY SETTER
	public void setTech(String tech) 
	{
		this.tech = tech;
	}
	
	//DETAILS GETTER
	public List<String> getDetails()
	{
		return this.details;
	}
	
	//DETAILS SETTER, IT ALLOWS US TO ADD NEW ELEMENTS (DETAILS) INTO
	//THE LIST OF DETAILS
	public void setDetails(String detail)
	{
		this.details.add(detail);
	}
	
}
