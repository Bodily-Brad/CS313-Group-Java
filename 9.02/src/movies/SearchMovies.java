package movies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

		String view = "search";									// will be set to 'detail' if we need to get details for a specific movie
		
        if (request.getParameterMap().containsKey("view")) {
            view = request.getParameter("view");
        }
        
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map;
		
		String urlString;
		
		switch (view)
		{
			// Get specific movie details
			case "detail":
				// Get Parameters from form
				String imdbID = request.getParameter("imdbID");
				urlString = "http://www.omdbapi.com/?plot=full&r=json";
				urlString += "&i=" + imdbID;
				

				map = mapper.readValue(new URL(urlString), Map.class);
				
				// Pass map to details page
				request.setAttribute("results", map);				
				
				// Show Detail view
				request.getRequestDispatcher("/detail.jsp").forward(request, response);				
				break;

			// Search for movies based off title string
			default:
				// Get parameters from form
				String title = request.getParameter("title");
				if (title.equals("")) title = "The Princess Bride";
				title = title.replace(" ", "+");
				
				// Create URL string
				urlString = "http://www.omdbapi.com/?plot=short&r=json";
				urlString += "&s=" + title;
				
				Boolean useJackson = true;
				
				if (useJackson)
				{
					// Get JSON from OMDb (this DOES use Jackson libs)
					map = mapper.readValue(new URL(urlString), Map.class);
					List list = (List)map.get("Search");	
					
					// Convert Jackson mapped list to 'regular' Java List
					List<Object> results = new ArrayList<Object>();
					for (Object item : list)
					{
						results.add(item);
					}
					
					request.setAttribute("results", results);
				}
				else
				{
					// Get JSON from OMDb (this does not use the Jackson libs)
					URL jsonReq = new URL(urlString);
					URLConnection urlConnection = jsonReq.openConnection();
					BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

					String recv;
					String recvbuff = "";
				   
					while ((recv = reader.readLine()) != null)
						recvbuff += recv;
				   
					reader.close();

					System.out.println(recvbuff);					
				}
				
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
