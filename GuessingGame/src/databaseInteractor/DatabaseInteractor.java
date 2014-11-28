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
	private String DB_URL;
	private String database = "";

	// Database credentials
	private String USER = "team";
	private String PASS = "pass";

	// Connection and statement objects for use throughout
	Connection conn = null;
	Statement stmt = null;

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
	 * Allows the use of databases other than the familyDB
	 * @param dbName A string of the database to be used. Can include a '/'
	 * or may optionally omit it.
	 */
	public void selectDatabase(String dbName) {
		if (!dbName.startsWith("/"))
			dbName = "/" + dbName;
		database = dbName;

	}

	/**
	 * Establishes the connection to a specific database.
	 * @param dbName
	 * @return True if connection is successful.
	 */
	public boolean connectToDatabase(String dbName) {
		selectDatabase(dbName);
		return connectToDatabase();
	}

	/**
	 * Allows the forced use of localhost as the database URL
	 */
	public void useLocalDB() {
		DB_URL = "jdbc:mysql://localhost";
	}

	/**
	 * Establishes the connection to the database. This may be called before executing
	 * a query, or alternatively will be called by default if the connection has not been made.
	 * @return Success status of database connection
	 */
	public boolean connectToDatabase() {
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
	public ResultSet executeQuery(String query) {

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
	public void closeConnection() {
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
}
