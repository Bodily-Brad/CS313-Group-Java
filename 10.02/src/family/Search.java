package family;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseInteractor.DatabaseInteractor;

import models.Person;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Person> people = new ArrayList<Person>();
		
		//Load Driver
		try {
			   Class.forName("com.mysql.jdbc.Driver");
			}
			catch(ClassNotFoundException ex) {
			   System.out.println("Error: " + ex.getMessage());
			   System.exit(1);
			}
		
		//Connect to Database
		String URL = "jdbc:mysql://localhost/familydb";
		String USER = "team";
		String PASS = "pass";
		
		// Try database interactor
		DatabaseInteractor db = new DatabaseInteractor();
		
		
		try{
			//Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Connection conn = DriverManager.getConnection(URL);
			
			//Get data set
			 Statement stmt = conn.createStatement();

		     String sql = "SELECT * FROM Person";
		     ResultSet rs = stmt.executeQuery(sql);
		     
		     // Iterate through all records
		     while(rs.next()){
		         
		         // Create new person from RecordSet
		         Person newPerson = new Person(rs);
		         
		         // Add person to return list
		         people.add(newPerson);
		     }
		     
		     //close conn
		     rs.close();
		     conn.close();

		}
		catch(Exception e)
		{
			// Display error message
			System.out.println("Error: " + e.getMessage());
			System.exit(1);
		}
		
		request.setAttribute("people", people);
		request.getRequestDispatcher("/people.jsp").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
