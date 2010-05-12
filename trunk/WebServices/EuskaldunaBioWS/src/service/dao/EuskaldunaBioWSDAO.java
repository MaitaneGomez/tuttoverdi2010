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

//THIS CLASS IMPLEMENTS ITS INTERFACE SO ITS GOING TO PROVIDE
//ACCESS TO THE EUSKALDUNA DB; CONNECTION, OBTAINING AND DISCONNECTION

public class EuskaldunaBioWSDAO implements IEuskaldunaBioWSDAO {
	
	private static Connection conn;
	private static Statement stat;

	@Override
	//CONNECTION: PDF
	public void connect() throws NamingException, SQLException 
	{
		Context iniCtx = new InitialContext();
		Context envCtx = (Context) iniCtx.lookup("java:comp/env");
			
		DataSource ds = (DataSource) envCtx.lookup("jdbc/euskaldunaBioDB");
		conn = ds.getConnection();
		stat = conn.createStatement();
	}

	@Override
	//DISCONNECTION
	public void disconnect() throws SQLException
	{
		conn.close();
		stat.close();
	}
	
	//THIS METHOD IS PRIVATE BECAUSE IT'S ONLYE GOING TO BE USED FROM 
	//THIS CLASS. ITS AIM IS TO CALCULATE THE NUMBER OF REHEARSALS THAT 
	//THERE ARE IN THE DB IN ORDER TO USE IT, TO CREATE AN ARRAY
	//WITH THE CORRECT SIZE
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
	//THIS METHOD RETURN THE LIST (ARRAY) OF REHEARSALS THAT THERE ARE
	//IN THE EUSKALDUNA DB
	public RehearsalDTO[] getRehearsals() throws SQLException
	{
		ResultSet rs = null;
        RehearsalDTO[] DTOArray = null ;
        
        DTOArray = new RehearsalDTO[this.calculateSize()]; 
        String query = "SELECT * FROM rehearsalsT";
        
        stat = conn.createStatement();
        rs = stat.executeQuery(query);
        int i = 0;
        //TRANSFORM THE RESULTSET INTO REHEARSALDTO
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

