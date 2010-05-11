package rehearsalServer.houseGateway;

import java.util.List;

//INTERFACE THAT DECLARES THE METHODS THAT THE GATEWAYS ARE GOING TO
//PROVIDE WHICH IS IN FACT OBTAINING THE REHEARSASL FROM THE DIFERENT
//HOUSES (SERVERS)
//IT ALSO HAS GETSERVERNAME, WHICH IS GOING TO BE USED IN THE LOAD OF THE
//CACHE TO USE THE SAME NAME HAS THE SERVER

public interface IOperaHGateway {
	public List<RehearsalDO> getRehearsals();
	public String getServerName();
}
