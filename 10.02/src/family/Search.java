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
		List<String> list = new ArrayList<String>();
		
		//Load Driver
		try {
			   Class.forName("com.mysql.jdbc.Driver");
			}
			catch(ClassNotFoundException ex) {
			   System.out.println("Error: unable to load driver class!");
			   System.out.println("Error: " + ex.getMessage());
			   System.exit(1);
			}
		
		//Connect to Database
		String URL = "jdbc:mysql://localhost/family_db";
		String USER = "team";
		String PASS = "pass";
		
		try{
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			
			//Get data set
			 Statement stmt = conn.createStatement();

		     String sql = "SELECT * FROM Person";
		     ResultSet rs = stmt.executeQuery(sql);
		     
		     while(rs.next()){
		         //Retrieve by column name
		         String first = rs.getString("first_name");
		         String last = rs.getString("last_name");
		         String birth = rs.getString("birthday");
		         
		         list.add(first + " " + last);
		     }
		     
		     //close conn
		     rs.close();

		}
		catch(Exception e)
		{
			System.out.println("Error: " + e.getMessage());
			System.exit(1);
		}
		
		request.setAttribute("list", list);
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
