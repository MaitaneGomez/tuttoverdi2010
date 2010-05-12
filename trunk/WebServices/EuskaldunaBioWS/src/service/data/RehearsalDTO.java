package service.data;

import java.io.Serializable;

//THE AIM OF THIS CLASS IS TO DEFINE THE OBJECTS THAT ARE GOING TO BE
//RETRIVED FROM THE EUSKALDUNA BD BY THE GETREHEARSALS METHOD
//IT HAS ITS CONSTRUCTOR, GETTERS AND SETTERS

public class RehearsalDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String operaName;
	private String date;
	private int seats;
	
	public RehearsalDTO(String operaName, String date, int seats)
	{
		this.operaName = operaName;
		this.date = date;
		this.seats = seats;
	}
	
	public String getOperaName()
	{
		return this.operaName;
	}
	
	public String getDate()
	{
		return this.date;
	}
	
	public int getSeats()
	{
		return this.seats;
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
