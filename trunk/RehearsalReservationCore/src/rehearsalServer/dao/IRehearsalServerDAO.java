package rehearsalServer.dao;

//INTERFACE THAT DECLARES THE METHODS THAT ARE GOING TO BE IMPLEMENTED BY THE
//DAO OF THE RESERVATIONS DB IN ORDER TO RESERVE A SEAT AND TO KNOW
//HOW MANY SEATS OF A GIVEN OPERAHOUSE AND OPERANAME ARE FREE

public interface IRehearsalServerDAO {
	public void connect();

	public int getReservationsCount(String operaHouse, String operaName);

	public void reserveSeat(String studName, String operaHouse, String operaName);

	public void disconnect();
}
