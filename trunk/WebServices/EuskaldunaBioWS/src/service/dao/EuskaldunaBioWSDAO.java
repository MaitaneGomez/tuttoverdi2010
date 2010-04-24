package service.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import service.data.RehearsalDTO;

public class EuskaldunaBioWSDAO implements IEuskaldunaBioWSDAO {
	
	private static Connection conn;
	private static Statement stat;

	@Override
	public void connect() 
	{
		try 
		{
			Context iniCtx = new InitialContext();
			Context envCtx = (Context) iniCtx.lookup("java:comp/env");
			
			DataSource ds = (DataSource) envCtx.lookup("jdbc/euskaldunaBioDB");
			conn = ds.getConnection();
			stat = conn.createStatement();
		}
		catch(SQLException se)
		{
			System.out.println("SQL Exception: " + se.getMessage());
			se.printStackTrace(System.out);	
			//this.disconnect();				
		}
		catch(NamingException ne)
		{
			System.out.println("Naming Exception: " + ne.getMessage());
			ne.printStackTrace(System.out);					
		}

	}

	@Override
	public void disconnect()
	{
		try 
		{
			conn.close();
			stat.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Unable to disconnect from the database");
			e.printStackTrace();
		}

	}
	
	public int calculateSize()
	{
		int size = 0;
		ResultSet rs = null;
		
		String query = "select count (*) from rehearsalsT";
		try 
		{
			stat = conn.createStatement();
			rs = stat.executeQuery(query);
			
			rs.next();
			size = rs.getInt(1);
			rs.close();
			stat.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return size;
	}

	@Override
	public RehearsalDTO[] getRehearsals()
	{
		ResultSet rs = null;
		RehearsalDTO[] DTOArray = null ;
		
		DTOArray = new RehearsalDTO[this.calculateSize()]; 
		String query = "SELECT * FROM rehearsalsT";

		try
		{
			stat = conn.createStatement();
			rs = stat.executeQuery(query);
			int i = 0;
			while(rs.next())
			{
				String operaName = rs.getString(1);
				String date = rs.getString(2);
				int seats = rs.getInt(3) ;
				RehearsalDTO newRehearsalDTO = new RehearsalDTO(operaName, date, seats);
				DTOArray[i] = newRehearsalDTO;
				i++;
			}
			rs.close();
			stat.close();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DTOArray;
		
	}

}

