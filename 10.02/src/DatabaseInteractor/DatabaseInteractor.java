package databaseInteractor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Person;

public class DatabaseInteractor {

	static final String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
	static final String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");

	// JDBC driver name and database URL
	private String DB_URL;
	private String database = "";

	// Database credentials
	private String USER = "team";
	private String PASS = "pass";

	Connection conn = null;
	Statement stmt = null;

	public DatabaseInteractor() {
		selectDatabase("/familyDB");
		DB_URL = "jdbc:mysql://" + host + ":" + port;
	}

	public void selectDatabase(String dbName) {
		if (!dbName.startsWith("/"))
			dbName = "/" + dbName;
		database = dbName;
	}

	public boolean connectToDatabase(String dbName) {
		selectDatabase(dbName);
		return connectToDatabase();
	}

	public void useLocalDB() {
		DB_URL = "jdbc:mysql://localhost";
	}

	public boolean connectToDatabase() {
		boolean success = true;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

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
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
		return success;
	}

	public ResultSet executeQuery(String query) {

		List<Person> people = new ArrayList<Person>();
		ResultSet rs = null;

		if (conn == null)
			connectToDatabase();
		try {

			// STEP 4: Execute a query
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			// Clean-up environment
			stmt.close();
			conn.close();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try

		return rs;
	}

	public List<Person> getAllPeople() {

		String query = "SELECT * FROM person";
		List<Person> people = new ArrayList<Person>();
		ResultSet rs = executeQuery(query);

		if (rs == null)
			return people;
		try {

			// STEP 4: Execute a query
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				// Get info to create Person object
				int personID = rs.getInt("person_id");
				String first = rs.getString("first_name");
				String last = rs.getString("last_name");
				Date birth = rs.getDate("birthday");

				// Create new person
				Person newPerson = new Person(personID, first, last, birth);

				// Add person to return list
				people.add(newPerson);
			}
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}// end finally try
		}// end try

		return people;
	}
}
