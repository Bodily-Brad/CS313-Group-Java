package databaseInteractor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * An interaction object used to interface with a MySQL database either in OpenShift or
 * on a local machine.
 * @author cave
 *
 */
public class DatabaseInteractor {

	// OpenShift host and port numbers.
	// If null, we are not on openShift and assumed to be local.
	static final String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
	static final String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");

	// JDBC driver name and database URL
	public static String DB_URL;
	public static String database = "";

	// Database credentials
	public static String USER = "team";
	public static String PASS = "pass";
	//private static String USER = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");	
	//private static String PASS = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");

	// Connection and statement objects for use throughout
	static Connection conn = null;
	static Statement stmt = null;

	/**
	 * Default constructor.
	 * Selects the familyDB as the default database and tries to connect to the 
	 * OpenShift database. If the OpenShift variables are not set, it will default 
	 * to the localhost.
	 */
	public DatabaseInteractor() {
		selectDatabase("/familyDB");
		if (host != null && port != null)
			DB_URL = "jdbc:mysql://" + host + ":" + port;
		else
			useLocalDB();
		// STEP 2: Register JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Set User.
	 * This function allows the username and password to be set.
	 */
	public static void setUser(String pUsername, String pPassword){
		USER = pUsername;
		PASS = pPassword;
	}
	
	
	/**
	 * Allows the use of databases other than the familyDB
	 * @param dbName A string of the database to be used. Can include a '/'
	 * or may optionally omit it.
	 */
	public static void selectDatabase(String dbName) {
		if (!dbName.startsWith("/"))
			dbName = "/" + dbName;
		database = dbName;

	}

	/**
	 * Establishes the connection to a specific database.
	 * @param dbName
	 * @return True if connection is successful.
	 */
	public static boolean connectToDatabase(String dbName) {
		selectDatabase(dbName);
		return connectToDatabase();
	}

	/**
	 * Allows the forced use of localhost as the database URL
	 */
	public static void useLocalDB() {
		DB_URL = "jdbc:mysql://localhost";
	}

	/**
	 * Establishes the connection to the database. This may be called before executing
	 * a query, or alternatively will be called by default if the connection has not been made.
	 * @return Success status of database connection
	 */
	public static boolean connectToDatabase() {
		boolean success = true;
		try {
			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL + database, USER, PASS);

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
			success = false;
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
			success = false;
		}// end try
		return success;
	}

	/**
	 * Generic query execution. Allows any SQL query to be executed. 
	 * @param query string.
	 * @return A ResultSet containing the results of the query.
	 */
	public static ResultSet executeQuery(String query) {

		ResultSet rs = null;

		if (conn == null)
			connectToDatabase();
		try {

			// STEP 4: Execute a query
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}// end try

		
		return rs;
	}


	/**
	 * Closes the connection from the database and all statements.
	 * This function MUST be called when finished or it will cause a memory leak
	 * on OpenShift.
	 */
	public static void closeConnection() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException se2) {
		}// nothing we can do
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}// end try
	}
	
	protected static ResultSet readRecord(String tableName, String keyName, int key)
	{
		String query =	"SELECT * from " + tableName + " WHERE " + keyName + "= " + key;
		ResultSet rs = executeQuery(query);
		return rs;
	}
	
	protected static ResultSet readRecords(String tableName)
	{
		String query =	"SELECT * from " + tableName;
		ResultSet rs = executeQuery(query);
		return rs;
	}
}
