package JMSOperaHouse;

import java.io.Serializable;

//CLASS THAT REPRESENTS THE OBJECT WHICH ARE GOING TO BE RETURN 
//BY THE DAO IN A LIST AND PUT IN THE QUEUE
//IT HAS CONSTRUCTOR, GETTERS AND SETTERS

public class RehearsalJMSDTO implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String operaName;
	private String date;
	private int seats;
	
	public RehearsalJMSDTO(String operaName, String date, int seats)
	{
		this.operaName = operaName;
		this.date = date;
		this.seats = seats;
	}

	public String getOperaName() 
	{
		return operaName;
	}

	public String getDate() 
	{
		return date;
	}

	public int getSeats() 
	{
		return seats;
	}

	public void setOperaName(String operaName) 
	{
		this.operaName = operaName;
	}

	public void setDate(String date) 
	{
		this.date = date;
	}

	public void setSeats(int seats) 
	{
		this.seats = seats;
	}
}
