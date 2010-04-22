package euskaldunaBioWS.dao;

public interface IEuskaldunaBioWSDAO {
	
	public void connect() throws SQLException, ClassNotFoundException;

	public RehearsalDTO[] getRehearsals() throws SQLException;

	public void disconnect() throws SQLException;
	
}
