package rehearsalServer.houseGateway;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


import rehearsalServer.houseGateway.proxies.*;

//THE AIM OF THIS CLASS IS TO OBTAIN THE REHEARSAL OBJECT FROM 
//EUSKALDUNABIO. THIS FUNCTIONALLITY IS GIVEN BY THE WEB SERVICE EUSKALDUNABIO


public class WSHouseGateway implements IOperaHGateway {
	
	//ATTRIBUTES NEEDED TO DEAL WITH THE RETRIEVING OF THE OBJECTS
	//SERVICEURI IS IN FACT A URL (OBTAINING FROM THE XML AND PASSING
	//FROM THE FACTORY)
	private String serviceName;
	private String serviceUri;

	
	//CONSTRUCTOR WHICH ARE GOING TO INITIZALIZATE THE ATTRIBUTES
	public WSHouseGateway(String serviceUri)
	{
		StringTokenizer st = new StringTokenizer(serviceUri);
		this.serviceUri = st.nextToken();
		this.serviceName = st.nextToken();
	}
	

	
	//METHOD THAT ARE GOING TO OBTAIN THE REHEARSALS FROM EUSKALDUNA
	//TO DO THAT WE HAVE TO INVOKE THE GETREHEARSALS OF THE WS
	public List<RehearsalDO> getRehearsals() {
		List<RehearsalDO> result = new ArrayList<RehearsalDO>();
		
		  EuskaldunaBioStub.RehearsalDTO[] array = null;

          try {
        	  //WE CREATE A STUB IN ORDER TO INVOKE THE METHOD
        	  EuskaldunaBioStub stub = new EuskaldunaBioStub(this.serviceUri);
              
        	  //WE INVOKE THE METHOD OF THE WEB SERVICE
              array = stub.getRehearsals();
              
              //TRANSFORMING REHEARSALDTO INTO REHEARSALDO
              for(int i =0; i < array.length; i++)
              {
            	  RehearsalDO newDO = new RehearsalDO(array[i].getOperaName(), array[i].getDate(), array[i].getSeats());
            	  result.add(newDO);
              }
                
          } catch (Exception e) {
              System.err.println("Error in accessing to the webService: " + e.getMessage());
          }

		return result;
	}

	@Override
	//THIS METHOD IS USED TO KNOW EXCATLY THE NAME OF THE SERVICE THE GATEWAY
	//IS ACCESSING TO, IN ORDER TO HAVE THE SAME NAME IN THE CACHE
	public String getServerName() {
		
		return this.serviceName;
	}
}
