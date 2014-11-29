package databaseInteractor;

import java.sql.ResultSet;

public class GameDBInteractor {

	private static DatabaseInteractor DBi = null;

	static String query = null;
	static ResultSet rs = null;

    protected static String tableName = "table";
    protected static String keyName = "key";
    protected static String defaultSearchField = "search";
    protected static String defaultSortField = "sort";

	public GameDBInteractor() {
		DBi = new DatabaseInteractor();
		DBi.selectDatabase("guessing_game");
		DBi.setUser("game_player", "");
	}

	protected static ResultSet readRecord(String tableName, String keyName,
			int key) {

		// Query String
		query = " SELECT * FROM " + tableName + " WHERE " + keyName + " = :"
				+ key;
		ResultSet rs = null;
		try {
			if (DBi.conn == null)
				DBi.connectToDatabase();
			rs = DBi.executeQuery(query);
			DBi.closeConnection();
			return rs;

		} catch (Exception ex) {
			System.err.print(ex.getMessage());
			return null;
		}
	}

	protected static ResultSet readRecords(String tableName, String keyName,
			String defaultSortField, int[] excludedKeys) {

		query = "SELECT * FROM " + tableName;

		// Add clause for excluded keys, if necessary
		if (excludedKeys != null) {
			query += " WHERE " + keyName + " NOT IN (";
			for (int i = 0; i < excludedKeys.length; i++) {
				query += excludedKeys[i];
				if ((i > 1) && (i < excludedKeys.length - 1))
					query += ", ";
			}
			query += ")";
		}

		// Add sorting
		query += " ORDER BY " + defaultSortField;
		try {
			if (DBi.conn == null)
				DBi.connectToDatabase();
			rs = DBi.executeQuery(query);
			DBi.closeConnection();
			return rs;

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}

	protected static ResultSet searchRecords(String criteria) {
		query = "SELECT * FROM " + tableName + " WHERE " + defaultSearchField
				+ " LIKE :" + criteria + " ORDER BY " + defaultSortField;
		try {
			rs = DBi.executeQuery(query);
			DBi.closeConnection();
			return rs;

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}
}
