package databaseInteractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class GameDBInteractor {

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

	
	// Protected Members
    protected static String mKey;
    
    // Properties
    public static String GetKey() {
    	return mKey;
    }
    
    public static Object createFromRecord(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}
    
    
    // Public Methods
    public static Object LoadFromDatabase(String key)
    {
    	Object obj = null;
        ResultSet record = readRecord(key);
        if (record != null)
        {
            obj = createFromRecord(record);
        }
        return obj;
    }
    
    private static List<Object> convertRStoList(ResultSet rs){
    	Object obj = null;
        List<Object> objects = new ArrayList<Object>();

        if (rs != null)
        {
            try {
				while (rs.next())
				{
				    Object object = createFromRecord(rs);
				    objects.add(object);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return objects;
    }
    
    public static List<Object> LoadAllFromDatabase()
    {
    	return LoadAllFromDatabase(null);
    }
    
    public static List<Object> LoadAllFromDatabase(String[] excludedKeys)
    {
        ResultSet rs = readRecords(excludedKeys);
    	return convertRStoList(rs);
    }
    
    public static List<Object> Search()
    {
    	return Search(null);
    }
    
    public static List<Object> Search(String criteria)
    {
        ResultSet records = searchRecords(criteria);
        return convertRStoList(records);
    }
    
    
	protected static ResultSet readRecord(String key) {

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

	protected static ResultSet readRecords(String[] excludedKeys) {

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
