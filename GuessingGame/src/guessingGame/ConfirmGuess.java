package guessingGame;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Game;

/**
 * Servlet implementation class ConfirmGuess
 */
@WebServlet("/ConfirmGuess")
public class ConfirmGuess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmGuess() {
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
		Game.FinishGameCorrect(session, itemID);
		
		// Set Message
		String message = "Wow... I can't believe I guessed right.";
		request.setAttribute("message", message);
		
		// Show finishGame view
		request.getRequestDispatcher("/views/finishGame.jsp").forward(request, response);
		
	}

}
