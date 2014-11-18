package movies;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class SearchMovies
 */
@WebServlet("/SearchMovies")
public class SearchMovies extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMovies() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get Results from OMDb API
		
		// Get parameters from form
		String title = request.getParameter("title");
		String view = "search";									// will be set to 'detail' if we need to get details for a specific movie
		
        if (request.getParameterMap().containsKey("view")) {
            view = request.getParameter("view");
        }
		
		switch (view)
		{
			// Get specific movie details
			case "detail":
				// TODO: Get movie details from API
				
				// Show Detail view
				request.getRequestDispatcher("/detail.jsp").forward(request, response);				
				break;

			// Search for movies based off title string
			default:
				// TODO: Get search results from API
				

				// TODO: Uncomment the line below to pass 'results'
				// to the search form as a parameter named "results"
				// that it will use to display the search results
				// (assuming we store our search results in a 'results'
				// variable.

				// request.setAttribute("results", results);
				
				// Show Search view
				request.getRequestDispatcher("/search.jsp").forward(request, response);
				break;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
