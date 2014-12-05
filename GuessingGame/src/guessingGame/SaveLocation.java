package MapAutomator;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MapAutomator.model.FileLocationHandler;


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
	String dataDirectory = System.getenv("OPENSHIFT_DATA_DIR");
		
		// Get Location Data 
		String location = request.getParameter("locationData");


		FileLocationHandler handler = new FileLocationHandler(dataDirectory + "locations.txt");
		handler.addLocation(location);
		
		response.sendRedirect("ShowLocations");

	}

}
