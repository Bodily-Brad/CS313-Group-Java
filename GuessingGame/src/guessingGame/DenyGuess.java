package guessingGame;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class DenyGuess
 */
@WebServlet("/DenyGuess")
public class DenyGuess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DenyGuess() {
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
		
		// Finish the game
		Game.FinishGameInCorrect(session);
		
		// Get valid possible items
		List<Integer> excludedKeys = new ArrayList<Integer>();
		excludedKeys.add(itemID);
		List<Item> items = GameDB.GetAllItems(excludedKeys);
		request.setAttribute("items", items);
		
		// Set message
		String message = "So, you beat me. I can't say I'm surprised, but let me know what you were thinking of, and I'll do better next time.";
		request.setAttribute("message", message);
		
		// Show pickItem view
		request.getRequestDispatcher("/views/pickItem.jsp").forward(request, response);
		
	}

}
