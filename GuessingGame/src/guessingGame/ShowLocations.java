package guessingGame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Location;
import databaseInteractor.GameDB;


/**
 * Servlet implementation class ShowLocations
 */
@WebServlet("/ShowLocations")
public class ShowLocations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowLocations() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get locations from DB
		List<Location> locations = GameDB.GetAllLocations();

		// Convert array of locations to an array of formatted strings
		List<String> locationStrings = new ArrayList<String>();
		for (Location location : locations)
			locationStrings.add(location.toString());
		
		// Pass location strings to view
		request.setAttribute("locations", locationStrings);
		
		// Show showMap view
        request.getRequestDispatcher("/views/showMap.jsp").forward(request,response); }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
