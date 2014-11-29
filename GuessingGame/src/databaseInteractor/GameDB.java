package databaseInteractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Answer;
import models.Item;
import models.Question;
import models.Response;

public class GameDB extends DatabaseInteractor {

	static String query = null;
	static ResultSet rs = null;

	public GameDB() {
		super();
		DatabaseInteractor.selectDatabase("guessing_game");
		DatabaseInteractor.setUser("gameplayer", "play");
	}
    
    // Public Methods
	public Answer GetAnswer(int answerID) { return readAnswer(answerID); }
	public Item GetItem(int itemID) { return readItem(itemID); }
	public Question GetQuestion(int questionID) { return readQuestion(questionID); }
	public Response GetResponse(int responseID) { return readResponse(responseID); }
	
    public static int GetItemIDWithLowestResponseCount()
    {
        // Query String
        String query =
    		"SELECT itemID, SUM(count) AS total " +
            "FROM   responses " +
            "GROUP BY itemID " +
            "ORDER BY total" +
            "LIMIT 1";
        
        ResultSet rs = DatabaseInteractor.executeQuery(query);
        DatabaseInteractor.closeConnection();
        
        try
        {
        	return rs.getInt("itemID");
        }
        catch (Exception e)
        {
			System.err.println(e.getMessage());
			return -1;
        }
    }
	
	// Private functions
    /**
     * Reads an Answer record from the database and returns
     * a corresponding Answer object
     * @param answerID ID of the record to read
     * @return Answer object corresponding to the specified
     * answer ID
     */
	private static Answer readAnswer(int answerID)
	{
		Answer answer = null;
		
		ResultSet rs = DatabaseInteractor.readRecord("answers", "answerID", answerID);
		try
		{
			rs.first();
			answer = resultSetToAnswer(rs);
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
		finally
		{
			DatabaseInteractor.closeConnection();
		}
		
		return answer;
	}
	
	/**
     * Reads an Item record from the database and returns
     * a corresponding Item object
	 * @param itemID ID of the record to read
	 * @return Item object corresponding to the specified
	 * item ID
	 */
	private static Item readItem(int itemID)
	{
		Item item = null;
		ResultSet rs = DatabaseInteractor.readRecord("items", "itemID", itemID);
		try
		{
			rs.first();
			item = resultSetToItem(rs);
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
		finally
		{
			DatabaseInteractor.closeConnection();
		}
		return item;
	}
	
	private static Question readQuestion(int questionID)
	{
		Question question = null;
		
		ResultSet rs = DatabaseInteractor.readRecord("questions", "questionID", questionID);
		try
		{
			rs.first();
			question = resultSetToQuestion(rs);
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
		finally
		{
			DatabaseInteractor.closeConnection();
		}
		
		return question;	
	}
	
	private static Response readResponse(int responseID)
	{
		Response response = null;
		
		ResultSet rs = DatabaseInteractor.readRecord("responses", "responseID", responseID);
		try
		{
			rs.first();
			response = resultSetToResponse(rs);
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
		finally
		{
			DatabaseInteractor.closeConnection();
		}
		
		return response;		
	}
	
	// Attempts to create an Answer object from a ResultSet
	private static Answer resultSetToAnswer(ResultSet rs)
	{
		try
		{
			return new Answer(
					rs.getInt("answerID"),
					rs.getString("text")
					);		
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
			return null;
		}		
	}
	
	// Attempts to create an Item object from a ResultSet
	private static Item resultSetToItem(ResultSet rs)
	{
		try
		{
			return new Item(
					rs.getInt("itemID"),
					rs.getString("description")
					);		
		}
		catch (Exception e)
		{
			System.err.println("here");
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	// Attempts to create a Question object from a ResultSet
	private static Question resultSetToQuestion(ResultSet rs)
	{
		try
		{
			return new Question(
					rs.getInt("questionID"),
					rs.getString("text")
					);		
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	// Attempts to create a Response object from a ResultSet
	private static Response resultSetToResponse(ResultSet rs)
	{        
		try
		{
			return new Response(
					rs.getInt("responseID"),
					rs.getInt("itemID"),
					rs.getInt("questionID"),
					rs.getInt("answerID"),
					rs.getInt("count"));
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
			return null;
		}
	}	
}
