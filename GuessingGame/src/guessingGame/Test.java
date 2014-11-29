package guessingGame;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Answer;
import models.Item;
import models.Question;
import models.Response;
import databaseInteractor.GameDB;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		GameDB game = new GameDB();
		Response test = game.GetResponse(1);
		
		if (test == null)
		{
			response.getWriter().write("URL: " + GameDB.DB_URL + "\n");
			response.getWriter().write("DB:  " + GameDB.database + "\n");
		}
		else
		{
		
		// pass item
		request.setAttribute("response", test);
		
		// show test view
		request.getRequestDispatcher("/testViewResponse.jsp").forward(request, response);
		
//		static final String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
//		static final String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
		

		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
