package corbaServer.dao;

import java.sql.SQLException;

import java.util.List;

import corbaServer.RehearsalDO;

public class CorbaOperaHouseDAO implements ICorbaOperaHouseDAO {

	public void connect() {

	}

	public List<RehearsalDO> getRehearsals() throws SQLException {
		List<RehearsalDO> rehearsals = null;

		return rehearsals;
	}

	public void disconnect() {

	}
}
