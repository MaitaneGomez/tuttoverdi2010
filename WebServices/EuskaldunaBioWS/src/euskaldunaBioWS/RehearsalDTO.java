package euskaldunaBioWS;

public class RehearsalDTO 
{
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
		return operaName;
	}
	
	public void setOperaName(String operaName) 
	{
		this.operaName = operaName;
	}
	
	public String getDate() 
	{
		return date;
	}
	
	public void setDate(String date) 
	{
		this.date = date;
	}
	
	public int getSeats() 
	{
		return seats;
	}
	
	public void setSeats(int seats) 
	{
		this.seats = seats;
	}
	
	

}
