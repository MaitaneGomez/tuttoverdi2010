package rehearsalServer.houseGateway;



import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


import rehearsalServer.houseGateway.proxies.*;

public class WSHouseGateway implements IOperaHGateway {
	
	private String serviceName;
	private String serviceUri;

	
	public WSHouseGateway(String serviceUri)
	{
		StringTokenizer st = new StringTokenizer(serviceUri);
		this.serviceUri = st.nextToken();
		this.serviceName = st.nextToken();
	}
	

	
	
	public List<RehearsalDO> getRehearsals() {
		List<RehearsalDO> result = new ArrayList<RehearsalDO>();
		
		  EuskaldunaBioStub.RehearsalDTO[] array = null;

          try {
        	  EuskaldunaBioStub stub = new EuskaldunaBioStub(this.serviceUri);
              
              array = stub.getRehearsals();
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
	public String getServerName() {
		
		return this.serviceName;
	}
}
