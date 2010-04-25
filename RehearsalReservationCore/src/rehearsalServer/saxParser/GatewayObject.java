package rehearsalServer.saxParser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// One Gateway Object per gateway in the XML file
// For the 2nd assignment

public class GatewayObject implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name = null;
	private String tech = null;
	private List<String> details = null;
	
	public GatewayObject()
	{
		details = new ArrayList<String>();
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getTech() 
	{
		return tech;
	}

	public void setTech(String tech) 
	{
		this.tech = tech;
	}
	
	public List<String> getDetails()
	{
		return this.details;
	}
	
	public void setDetails(String detail)
	{
		this.details.add(detail);
	}
	
}
