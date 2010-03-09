package corbaServer;

/* OBJECT NEEDED TO HOLD DETAILS THAT COME FROM THE DATABASE
 * BUT NEED TO BE TRANSMITED TO THE CORBA CLIENT INSIDE A STRUCT
 */
public class RehearsalDO {
	private String operaName;
	private String date;
	private int seats;

	public RehearsalDO(String o, String d, int s) {
		operaName = o;
		date = d;
		seats = s;
	}

	public String getOperaName() {
		return operaName;
	}

	public String getDate() {
		return date;
	}

	public int getSeats() {
		return seats;
	}
}
