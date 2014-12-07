package guessingGame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Game;
import models.Location;
import databaseInteractor.GameDB;


/**
 * Servlet implementation class ShowLocations
 */
@WebServlet("/ShowLocations")
public class ShowLocations extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final GameDB gameDB = new GameDB();  // Need to initialize static object, but will call with GameDB
	private static final Game game = new Game();		// Need to initialize static object, but will call with Game	
       
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
		
		// Pass location strings to view
		request.setAttribute("locations", locations);
		
		// Pass message to view
		String message = "See where games have been played from.";
		request.setAttribute("message", message);
		
		// Show showMap view
        request.getRequestDispatcher("/views/showMap.jsp").forward(request,response); }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
