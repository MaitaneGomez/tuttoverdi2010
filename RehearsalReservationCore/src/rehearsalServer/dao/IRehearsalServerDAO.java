package rehearsalServer.dao;

public interface IRehearsalServerDAO {
	public void connect();

	public int getReservationsCount(String operaHouse, String operaName);

	public void reserveSeat(String studName, String operaHouse, String operaName);

	public void disconnect();
}
