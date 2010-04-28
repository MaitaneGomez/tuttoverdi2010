package service.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import service.data.RehearsalDTO;

public class EuskaldunaBioWSDAO implements IEuskaldunaBioWSDAO {
	
	private static Connection conn;
	private static Statement stat;

	@Override
	public void connect() throws NamingException, SQLException 
	{
		Context iniCtx = new InitialContext();
		Context envCtx = (Context) iniCtx.lookup("java:comp/env");
			
		DataSource ds = (DataSource) envCtx.lookup("jdbc/euskaldunaBioDB");
		conn = ds.getConnection();
		stat = conn.createStatement();
	}

	@Override
	public void disconnect() throws SQLException
	{
		conn.close();
		stat.close();
	}
	
	private int calculateSize() throws SQLException
	{
		int size = 0;
        ResultSet rs = null;
        
        String query = "select count (*) from rehearsalsT";
        
        stat = conn.createStatement();
        rs = stat.executeQuery(query);
        
        rs.next();
        size = rs.getInt(1);
        rs.close();
        stat.close();
        
        return size;
	}
	
	@Override
	public RehearsalDTO[] getRehearsals() throws SQLException
	{
		ResultSet rs = null;
        RehearsalDTO[] DTOArray = null ;
        
        DTOArray = new RehearsalDTO[this.calculateSize()]; 
        String query = "SELECT * FROM rehearsalsT";
        
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
		
		return DTOArray;
		
	}

}

