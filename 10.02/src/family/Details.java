package family;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Person;

/**
 * Servlet implementation class Details
 */
@WebServlet("/Details")
public class Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Get id
		int id = Integer.parseInt(request.getParameter("id"));
		
		// Retrieve person from database
		Person person = getPerson(id);
		
		// Pass person to details page
		request.setAttribute("person", person);
		request.getRequestDispatcher("/details.jsp").forward(request, response);			
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private List<Person> getChildren(int parentID)
	{
		ArrayList<Person> children = new ArrayList<Person>();
		
		//Load Driver
		try {
			   Class.forName("com.mysql.jdbc.Driver");
			}
			catch(ClassNotFoundException ex) {
			   System.out.println("Error: " + ex.getMessage());
			   System.exit(1);
			   return null;
			}
		
		//Connect to Database
		String URL = "jdbc:mysql://localhost/familydb";
		String USER = "team";
		String PASS = "pass";
		
		try{
			//Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Connection conn = DriverManager.getConnection(URL);
			
			//Get data set
			 Statement stmt = conn.createStatement();

		     String sql = "SELECT * FROM Person WHERE father = " + parentID + " OR mother =" + parentID;
		     ResultSet rs = stmt.executeQuery(sql);
		     
		     // Iterate through all records
		     while(rs.next()){
		         
		         // Add person to child list
		         children.add(new Person(rs));
		     }
		     
		     //close conn
		     rs.close();
		     conn.close();
		     
		     return children;

		}
		catch(Exception e)
		{
			// Display error message
			System.out.println("Error: " + e.getMessage());
			System.exit(1);
			return null;
		}		

	}
	
	protected Person getPerson(int id)
	{
		//Load Driver
		try {
			   Class.forName("com.mysql.jdbc.Driver");
			}
			catch(ClassNotFoundException ex) {
			   System.out.println("Error: " + ex.getMessage());
			   System.exit(1);
			   return null;
			}
		
		//Connect to Database
		String URL = "jdbc:mysql://localhost/familydb";
		String USER = "team";
		String PASS = "pass";
		
		try{
			//Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Connection conn = DriverManager.getConnection(URL);
			
			//Get data set
			 Statement stmt = conn.createStatement();

		     String sql = "SELECT * FROM Person WHERE person_id = " + id;
		     ResultSet rs = stmt.executeQuery(sql);
		     rs.next();
		     
		     Person person = new Person(rs);
		     
	         // Get Father (yes, this will cascade, but it's okay for now)
	         if (rs.getString("father") != null)
	         {
		         int fatherID = Integer.parseInt(rs.getString("father"));
	        	 Person father = getPerson(fatherID);
	        	 person.setFather(father);
	         }
	         
	         // Get Mother
	         if (rs.getString("mother") != null)
	         {
		         int motherID = Integer.parseInt(rs.getString("mother"));
	        	 Person mother = getPerson(motherID);
	        	 person.setMother(mother);
	         }
	         
	         // Get Children
	         person.setChildren(getChildren(person.getId()));
		     
		     //close conn
		     rs.close();
		     conn.close();
		     
		     return person;

		}
		catch(Exception e)
		{
			// Display error message
			System.out.println("Error: " + e.getMessage());
			System.exit(1);
			return null;
		}
	}

}
