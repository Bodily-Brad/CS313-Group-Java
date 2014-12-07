package guessingGame;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databaseInteractor.GameDB;
import models.Game;
import models.Item;

/**
 * Servlet implementation class ProvideCorrectItem
 */
@WebServlet("/ProvideCorrectItem")
public class ProvideCorrectItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProvideCorrectItem() {
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
		// Get Session
		HttpSession session = request.getSession();

		// Check for player input
		int itemID = -1;
		
		String iID = request.getParameter("itemID");

		if (iID != null)
			itemID = Integer.parseInt(iID);
		
		// Record responses
		Game.RecordPlayerResponses(session, itemID);
		Game.EndGame(session);
		
		// Immediately Start new game
		Game.StartNewGame(session);
		
		// Get all items for newGame view
		List<Item> items = GameDB.GetAllItems();
		
		// Show currentLocation view
		//request.setAttribute("items", items);
		request.getRequestDispatcher("/views/currentLocation.jsp").forward(request, response);
	}

}
