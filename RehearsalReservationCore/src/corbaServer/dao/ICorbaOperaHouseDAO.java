package corbaServer.dao;

// ADDITIONAL OPERATIONS MAY BE NEEDED 
import java.sql.SQLException;
import java.util.List;
import corbaServer.RehearsalDO;

//Interface which defines the method that the DAO is going to have

//NOTE: the connect method has as a parameter the name of a database 
//just to reuse it by 2 servers, milano and napoli

public interface ICorbaOperaHouseDAO {
	public void connect(String dataBase) throws SQLException, ClassNotFoundException;

	public List<RehearsalDO> getRehearsals() throws SQLException;

	public void disconnect() throws SQLException;
}
