package service;

import service.dao.*;
import service.data.RehearsalDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class EuskaldunaService {
    
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
