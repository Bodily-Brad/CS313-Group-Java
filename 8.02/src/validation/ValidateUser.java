package validation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ValidateUser
 */
@WebServlet("/ValidateUser")
public class ValidateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Get Username & Password from form
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// Do bad hard-coded validation
		if (
				(username.equalsIgnoreCase("student")) &&
				(password.equals("pass"))
			)
		{
			// Set Session Variable
			request.getSession().setAttribute("loggedInUser", username);
			
			// Show Welcome view
			request.getRequestDispatcher("/welcome.jsp").forward(request, response);
		}
		else
		{
			// Create error message
			request.setAttribute("message", "Incorrect username or password.");
			
			// Show Login view
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
