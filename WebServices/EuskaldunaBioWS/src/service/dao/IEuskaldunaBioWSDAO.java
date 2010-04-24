package service.dao;

import service.data.RehearsalDTO;

public interface IEuskaldunaBioWSDAO 
{
	
	public void connect();

	public RehearsalDTO[] getRehearsals();

	public void disconnect();

}
