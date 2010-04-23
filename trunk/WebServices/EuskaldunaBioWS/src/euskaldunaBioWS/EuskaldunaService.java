package euskaldunaBioWS;

import euskaldunaBioWS.dao.EuskaldunaBioWSDAO;

public class EuskaldunaService 
{
	public EuskaldunaService()
	{}
	
	public RehearsalDTO[] getRehearsals()
	{
		RehearsalDTO[] array = null;
		EuskaldunaBioWSDAO dao = new EuskaldunaBioWSDAO();
		dao.connect();
		array = dao.getRehearsals();
		dao.disconnect();
		
		return array;
		
	}
}