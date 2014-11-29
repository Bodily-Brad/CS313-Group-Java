package models;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game {

	// Class Variables
	protected static String GAMESTATE_KEY = "gamestate";
	protected static String QUESTIONS_LEFT_KEY = "questionsLeft";
	protected static String QUESTIONS_LIST_KEY = "questionsAnswered";
	protected static int STARTING_QUESTIONS = 20;
	
	// These may become an Enum instead
	public static String GAMESTATE_STARTED = "GAMESTATE_STARTED";
	public static String GAMESTATE_SOLVING = "GAMESTATE_SOLVING";
	public static String GAMESTATE_FINISH_CORRECT = "GAMESTATE_FINISH_CORRECT";
	public static String GAMESTATE_FINISH_INCORRECT = "GAMESTATE_FINISH_INCORRECT";
	
	// "Properties"
	public static GameState getGameState(HttpSession session)
	{
		GameState state = (GameState)session.getAttribute(GAMESTATE_KEY);
		return state;
	}
	public static void setGameState(HttpSession session, GameState gameState)
	{
		session.setAttribute(GAMESTATE_KEY, gameState);
	}
	
	public static int getQuestionsLeft(HttpSession session)
	{
		return (int)session.getAttribute(QUESTIONS_LEFT_KEY);
	}
	public static void setQuestionsLeft(HttpSession session, int value)
	{
		session.setAttribute(QUESTIONS_LEFT_KEY, value);
	}
	
	public static Map<Integer, Integer> getQuestionsAnswered(HttpSession session)
	{
		return (Map<Integer, Integer>)session.getAttribute(QUESTIONS_LIST_KEY);
	}
	public static void setQuestionsAnswered(HttpSession session, Map<Integer, Integer> questionsList)
	{
		session.setAttribute(QUESTIONS_LIST_KEY, questionsList);
	}
	
	// Methods
	public static int AttemptSolve(HttpSession session)
	{
		setGameState(session, GameState.Solving);
		
		// Brute Method
		List<Object> items = Item.LoadAllFromDatabase();
		
		ArrayList<Float> confidences = new ArrayList<Float>();
		
		for (Object obj : items)
		{
			Item item = (Item)obj;	// Cast Object to Item
			
			confidences.set(item.getID(), 0.0f);
			
			// Iterate through questions in answered questions array
			Map<Integer, Integer> questionsAnswered = Game.getQuestionsAnswered(session);
			for (int questionID : questionsAnswered.keySet())
			{
				int answerID = questionsAnswered.get(questionID);
				
				float count = Response.GetResponseCount(item.getID(), questionID, answerID);
				float totalCount = Response.GetTotalResponsesByQuestionAndItem(questionID, item.getID());
				float averageCount = totalCount / 4.0f;	// Hard set to 4 responses
				
				float increase = 0.0f;
				if (averageCount > 0)
					increase = (count / averageCount - 1.0f);
				
				float newConf = confidences.get(item.getID());
				newConf += increase;
				
				confidences.set(item.getID(), newConf);
			}
		}
		
		// Find max confidence
		int maxConfID = 0;
		float maxConf = 0.0f;
		
		for (int i=0; i < confidences.size(); i++)
		{
			if (confidences.get(i) > maxConf)
			{
				maxConf = confidences.get(i);
				maxConfID = i;
			}
		}
		
		return maxConfID;		
	}
	
	public static void EndGame(HttpSession session)
	{
		setGameState(session, null);
		setQuestionsLeft(session, -1);
		setQuestionsAnswered(session, null);
	}
	
	public static void FinishGameCorrect(HttpSession session, int itemID)
	{
		Game.setGameState(session, GameState.FinishCorrect);
		Game.RecordPlayerResponses(session, itemID);
	}
	
	public static void FinishGameInCorrect(HttpSession session)
	{
		Game.setGameState(session, GameState.FinishIncorrect);
	}
	
	public static int GetRandomQuestionID(HttpSession session)
	{
		Map<Integer, Integer> askedQuestions = Game.getQuestionsAnswered(session);
		// TODO: Implement 'get random question' logic
		
		return -1;
	}
	
	public static void RecordAnswer(HttpSession session, int questionID, int answerID)
	{
		Map<Integer, Integer> questionsAnswered = Game.getQuestionsAnswered(session);
		questionsAnswered.put(questionID, answerID);
		
		Game.setQuestionsAnswered(session, questionsAnswered);
		Game.setQuestionsLeft(session, Game.getQuestionsLeft(session)-1);
	}
	
	public static void RecordPlayerResponses(HttpSession session, int itemID)
	{
		Map<Integer, Integer> questionsAnswered = Game.getQuestionsAnswered(session);
		
		for (int questionID : questionsAnswered.keySet())
		{
			int answerID = questionsAnswered.get(questionID);
			Response.IncrementCount(itemID, questionID, answerID);
		}
		
	}
	
	public static void StartNewGame(HttpSession session)
	{
		Game.EndGame(session);
		Game.setQuestionsLeft(session, STARTING_QUESTIONS);
	}
	
}
