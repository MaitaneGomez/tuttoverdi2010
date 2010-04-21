package rehearsalServer.houseGateway;

import java.util.List;

public interface IOperaHGateway {
	public List<RehearsalDO> getRehearsals();
	public String getServerName();
}
