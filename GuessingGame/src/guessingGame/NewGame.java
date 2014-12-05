package guessingGame;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Game;
import models.Item;
import databaseInteractor.GameDB;

/**
 * Servlet implementation class NewGame
 */
@WebServlet("/NewGame")
public class NewGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final GameDB gameDB = new GameDB();  // Need to initialize static object, but will call with GameDB
	private static final Game game = new Game();		// Need to initialize static object, but will call with Game
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewGame() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {				
		HttpSession session = request.getSession();
		
		// Start new Game
		// this is a departure from the 'php method', where the
		// Game wasn't actually 'started' until the user clicked
		// the first link.
		Game.StartNewGame(session);
		
		// Get all items for newGame view
		List<Item> items = GameDB.GetAllItems();
		
		// Show newGame view
		request.setAttribute("items", items);
		request.getRequestDispatcher("/views/newGame.jsp").forward(request, response);			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
