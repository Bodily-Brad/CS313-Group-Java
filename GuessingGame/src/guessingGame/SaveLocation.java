package guessingGame;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Location;


/**
 * Servlet implementation class SaveLocation
 */
@WebServlet("/SaveLocation")
public class SaveLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		// Get Location Data 
		//String location = request.getParameter("locationData");
		String latitudeS = request.getParameter("latitude");
		String longitudeS = request.getParameter("longitude");

		double latitude = 0.0;
		double longitude = 0.0;
		
		if (latitudeS != null)
			latitude = Double.parseDouble(latitudeS);
		
		if (longitudeS != null)
			longitude = Double.parseDouble(longitudeS);
		
		// Make sure we're good (no nulls) before we try
		// to save
		if ((latitudeS != null) && (longitudeS != null))
		{
			Location location = new Location(latitude, longitude);
			
			// TODO: Add DB Save
		}

		// Call ShowLocations Servlet
		response.sendRedirect("ShowLocations");

	}

}
