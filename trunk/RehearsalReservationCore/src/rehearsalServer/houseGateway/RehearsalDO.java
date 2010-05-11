package rehearsalServer.houseGateway;

// DO = Domain Object; Details come from different sources

//THIS CLASS REPRESENTS THE OBJECT THAT EVERY GATEWAY IS GOING TO 
//DEAL WITH, IN THEIR GETREHEARSALS METHOD

public class RehearsalDO {

	private String operaName;
	private String date;
	private int availableSeats;

	public RehearsalDO(String n, String d, int s) {
		operaName = n;
		date = d;
		availableSeats = s;
	}

	public String getOperaName() {
		return operaName;
	}

	public String getDate() {
		return date;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}
}
