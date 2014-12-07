package guessingGame;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseInteractor.GameDB;
import models.Game;
import models.Location;


/**
 * Servlet implementation class SaveLocation
 */
@WebServlet("/SaveLocation")
public class SaveLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final GameDB gameDB = new GameDB();  // Need to initialize static object, but will call with GameDB
	private static final Game game = new Game();		// Need to initialize static object, but will call with Game	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveLocation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String dataDirectory = System.getenv("OPENSHIFT_DATA_DIR");
		
		// Get Location Data 
		//String location = request.getParameter("locationData");
		
		String sLat = request.getParameter("latitude");
		String sLon = request.getParameter("longitude");
		
		double latitude = 0.0;
		double longitude = 0.0;
		
		if (sLat != null)
			latitude = Double.parseDouble(sLat);
		
		if (sLon != null)
			longitude = Double.parseDouble(sLon);
		
		if ((sLat != null) && (sLon != null))
		{
			Location newLocation = new Location(latitude, longitude);
			GameDB.InsertLocation(newLocation);
		}
		
		// FileLocationHandler handler = new FileLocationHandler(dataDirectory + "locations.txt");
		// handler.addLocation(location);
		
		response.sendRedirect("ShowLocations");

	}

}
