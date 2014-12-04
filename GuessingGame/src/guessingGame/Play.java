package guessingGame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Answer;
import models.Game;
import models.Item;
import models.Question;
import databaseInteractor.GameDB;

/**
 * Servlet implementation class Play
 */
@WebServlet("/Play")
public class Play extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final GameDB gameDB = new GameDB();  // Need to initialize static object, but will call with GameDB
	private static final Game game = new Game();		// Need to initialize static object, but will call with Game
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Play() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		// Check for player input
		int answerID = -1;
		int questionID = -1;
		
		String qID = request.getParameter("questionID");
		String aID = request.getParameter("answerID");

		if (qID != null)
			questionID = Integer.parseInt(qID);
		if (aID != null)
			answerID = Integer.parseInt(aID);
		
		if ((answerID > 0 ) && (questionID > 0))
			Game.RecordAnswer(session, questionID, answerID);
		
		// This code is duplicated in Start...
		// (Start shouldn't be needed any longer)
		int questionsRemaining = Game.getQuestionsLeft(session);
		questionID = -1;
		
		// If we still have questions remaining
		// get the next question ID
		if (questionsRemaining > 0)
		{
			questionID = Game.GetRandomQuestionID(session);
		}
		
		if ((questionID != -1) && (questionsRemaining > 0))
		{
			Question question = GameDB.GetQuestion(questionID);
			List<Answer> answers = GameDB.GetAllAnswers();
			
			request.setAttribute("question", question);
			request.setAttribute("answers", answers);
			request.setAttribute("NumQuestionsLeft", questionsRemaining);
			
			// Debug list asked questions
			List<Question> askedQuestions = new ArrayList<Question>();
			Map<Integer, Integer> askedMap = Game.getQuestionsAnswered(session);
			for (Map.Entry<Integer, Integer> entry : askedMap.entrySet())
			{
				askedQuestions.add(GameDB.GetQuestion(entry.getKey()));
			}
			request.setAttribute("askedMap", askedMap);
			request.setAttribute("askedQuestions", askedQuestions);
			
			// Debug list all questions
			List<Question> allQuestions = GameDB.GetAllQuestions();
			request.setAttribute("allQuestions", allQuestions);
			
			request.getRequestDispatcher("/views/askQuestion.jsp").forward(request, response);			
		}
		else
		{
			// We're out of questions now
			int guessItemID = Game.AttemptSolve(session);
			//int guessItemID = 1;
			Item guessItem = GameDB.GetItem(guessItemID);
			//String message = "I'm out of questions, sorry.";
			
			request.setAttribute("guessItem", guessItem);
			request.getRequestDispatcher("/views/endGame.jsp").forward(request, response);				
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// This is cheating for now
		doGet(request, response);
	}

}
