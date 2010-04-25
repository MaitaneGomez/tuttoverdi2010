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
        	  //String url = "http://localhost:8080/axis2/services/EuskaldunaBio";
        	  System.out.println("justo antes de buscar la stub");
        	  EuskaldunaBioStub stub = new EuskaldunaBioStub(this.serviceUri);
        	  System.out.println("tengo la stub");
              //EuskaldunaBioStub stub = new EuskaldunaBioStub("http://localhost:8080/axis2/services/EuskaldunaBio");
              
              array = stub.getRehearsals();
              System.out.println("el tamaño del array es: " + array.length);
              for(int i =0; i < array.length; i++)
              {
            	  RehearsalDO newDO = new RehearsalDO(array[i].getOperaName(), array[i].getDate(), array[i].getSeats());
            	  System.out.println(array[i].getOperaName()+ " " + array[i].getDate() + " " + array[i].getSeats());
            	  result.add(newDO);
              }
                
          } catch (Exception e) {
              System.err.println("Peto al entrar: " + e.getMessage());
          }

		return result;
	}

	@Override
	public String getServerName() {
		
		return this.serviceName;
	}
}
