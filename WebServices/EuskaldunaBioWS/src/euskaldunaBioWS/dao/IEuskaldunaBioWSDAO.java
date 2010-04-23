package euskaldunaBioWS.dao;

import euskaldunaBioWS.RehearsalDTO;

public interface IEuskaldunaBioWSDAO 
{
	
	public void connect();

	public RehearsalDTO[] getRehearsals();

	public void disconnect();

}
