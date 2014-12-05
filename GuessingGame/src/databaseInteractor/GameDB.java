package databaseInteractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Answer;
import models.Item;
import models.Location;
import models.Question;
import models.Response;

public class GameDB extends DatabaseInteractor {

	static String query = null;
	static ResultSet rs = null;

	public GameDB() {
		super();
		DatabaseInteractor.selectDatabase("guessing_game");
		DatabaseInteractor.setUser("gameplayer", "play");
		//DatabaseInteractor.setUser(System.getenv("OPENSHIFT_MYSQL_DB_USERNAME"),System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD"));
	}
    
    // Public Methods
	
	/*
	 * I've left functions that execute queries as private functions. These
	 * properties deal with object-logic rather than database logic. This
	 * does create some additional private functions that could be handled
	 * in the getters, but it leaves the public interface independent of
	 * data storage logic 
	 */
	
	public static List<Answer> GetAllAnswers() { return readAnswers(); }
	public static List<Item> GetAllItems() { return readItems(); }
	public static List<Item> GetAllItems(List<Integer> excludedKeys) { return readItems(excludedKeys); }
	public static List<Location> GetAllLocations() { return readLocations(); }
	public static List<Question> GetAllQuestions() { return readQuestions(); }
	public static List<Question> GetAllQuestions(List<Integer> excludedKeys) { return readQuestions(excludedKeys); }
	
	public static Answer GetAnswer(int answerID) { return readAnswer(answerID); }
	public static Item GetItem(int itemID) { return readItem(itemID); }
	public static Question GetQuestion(int questionID) { return readQuestion(questionID); }
	public static Response GetResponse(int responseID) { return readResponse(responseID); }
	public static Response GetResposne(int itemID, int questionID, int answerID) { return readResponseByCriteria(itemID, questionID, answerID); }
	
	/**
	 * Returns the response count for a particular item/question/answer combo
	 * @param itemID Item key
	 * @param questionID Question key
	 * @param answerID Answer key
	 * @return The response count for the specified item/question/answer combo
	 */
	public static int GetResponseCount(int itemID, int questionID, int answerID)
	{
		Response response = readResponseByCriteria(itemID, questionID, answerID);

		if (response != null)				
			return response.getCount();
		// Else
		return 0;
	}
	
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
	
    public static int GetTotalResponsesByQuestionAndItem(int questionID, int itemID) { return readTotalResponsesByQuestionAndItem(questionID, itemID); } 
    
    public static Response InsertResponse(int itemID, int questionID, int answerID, int count)
    {
    	// Inserts, then reads
    	int newID = insertResponse(itemID, questionID, answerID, count);
    	
    	// If successful, immediately read the new Response
    	if (newID != -1)
    	{
    		return GetResponse(newID);
    	}
    	// Otherwise, return null
    	else
    	{
    		return null;
    	}
    }
    
    public static boolean UpdateResponseCount(int responseID, int count) { return updateResponseCount(responseID, count); }
    
	// Private functions
    
    /**
     * Attempts to insert a new response record
     * @param itemID Item key
     * @param questionID Question key
     * @param answerID Answer key
     * @param count Count
     * @return The ID of the new response record if successful; otherwise, -1.
     */
    private static int insertResponse(int itemID, int questionID, int answerID, int count)
    {
    	String query =
                "INSERT INTO responses (itemID, questionID, answerID, count) " +
                "VALUES (" + itemID + "," + questionID + "," + answerID + ", " + count + ")";
    	
		int newID = -1;

		if (conn == null)
			connectToDatabase();
		try {

			// STEP 4: Execute a query
			stmt = conn.createStatement();
			newID = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}// end try
		finally
		{
			closeConnection();
		}
		
		return newID;    	
    }
    
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
			System.err.println("GameDB.readAnswer(int answerID)");
			System.err.println("answerID: " + answerID);
			System.err.println(e.getMessage());
		}
		finally
		{
			DatabaseInteractor.closeConnection();
		}
		
		return answer;
	}
	
	private static List<Answer> readAnswers()
	{
		ResultSet rs = DatabaseInteractor.readRecords("answers");
		List<Answer> answers = new ArrayList<Answer>();
		try
		{
			answers = resultSetToAnswerList(rs);
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
		finally
		{
			DatabaseInteractor.closeConnection();
		}
		
		return answers;		
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
			System.err.println("GameDB.readItem(int itemID)");
			System.err.println("itemID: " + itemID);			
			System.err.println(e.getMessage());			
		}
		finally
		{
			DatabaseInteractor.closeConnection();
		}
		return item;
	}
	
	/**
	 * Attempts to read all item data from the database
	 * @return A list of Item objects
	 */
	private static List<Item> readItems()
	{
		ResultSet rs = DatabaseInteractor.readRecords("items", "description");
		List<Item> items = new ArrayList<Item>();
		try
		{
			items = resultSetToItemList(rs);
		}
		catch (Exception e)
		{
			System.err.println("GameDB.readItems()");			
			System.err.println(e.getMessage());
		}
		finally
		{
			DatabaseInteractor.closeConnection();
		}
		
		return items;
	}
	
	private static List<Item> readItems(List<Integer> excludedKeys)
	{
		List<Item> items = new ArrayList<Item>();
		
		String query =
				"SELECT * " +
                "FROM	items";
		
		// add clause for excluded keys, if necessary
		if (!excludedKeys.isEmpty())
		{
			query += " WHERE itemID NOT IN (";
			for (int i = 0; i < excludedKeys.size(); i++)
			{
				int key = excludedKeys.get(i);
				query += key;
				
				if ((excludedKeys.size() > 1) &&
					(i < excludedKeys.size() - 1))
					query += ", ";
			}
			query += ")";
		}
		
		// Add order by
		query += " ORDER BY description";
		
		// Execute query
		ResultSet rs = executeQuery(query);
		
		try
		{
			items = resultSetToItemList(rs);
		}
		catch (Exception e)
		{
			System.err.println("GameDB.readItems(List<Integer> excludedKeys)");
			System.err.println("excludedKeys: " + excludedKeys.toString());			
			System.err.println(e.getMessage());
		}
		finally
		{
			closeConnection();
		}
		
		return items;			
	}
	
	private static List<Location> readLocations()
	{
		ResultSet rs = DatabaseInteractor.readRecords("userscores", "userScoreID");
		List<Location> locations = new ArrayList<Location>();
		try
		{
			locations = resultSetToLocationList(rs);
		}
		catch (Exception e)
		{
			System.err.println("GameDB.readLocations()");			
			System.err.println(e.getMessage());
		}
		finally
		{
			DatabaseInteractor.closeConnection();
		}
		
		return locations;		
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
			System.err.println("GameDB.readQuestion(int questionID)");
			System.err.println("questionID: " + questionID);				
			System.err.println(e.getMessage());
		}
		finally
		{
			DatabaseInteractor.closeConnection();
		}
		
		return question;	
	}
	
	private static List<Question> readQuestions()
	{
		ResultSet rs = DatabaseInteractor.readRecords("questions");
		List<Question> questions = new ArrayList<Question>();
		try
		{
			questions = resultSetToQuestionList(rs);
		}
		catch (Exception e)
		{
			System.err.println("GameDB.readQuestions()");			
			System.err.println(e.getMessage());
		}
		finally
		{
			DatabaseInteractor.closeConnection();
		}
		
		return questions;		
	}
	
	/**
	 * Attempts to read a set of questions from the database that do not contain an excluded key
	 * @param excludedKeys A list of integers to exclude from the returned list
	 * @return A list of Questions representing all questions not specifically excluded
	 */
	private static List<Question> readQuestions(List<Integer> excludedKeys)
	{	
		List<Question> questions = new ArrayList<Question>();
		
		String query =
				"SELECT * " +
                "FROM	questions";
		
		// add clause for excluded keys, if necessary
		if (!excludedKeys.isEmpty())
		{
			query += " WHERE questionID NOT IN (";
			for (int i = 0; i < excludedKeys.size(); i++)
			{
				int key = excludedKeys.get(i);
				query += key;
				
				if ((excludedKeys.size() > 1) &&
					(i < excludedKeys.size() - 1))
					query += ", ";
			}
			query += ")";
		}
		
		// Execute query
		ResultSet rs = executeQuery(query);
		
		try
		{
			questions = resultSetToQuestionList(rs);
		}
		catch (Exception e)
		{
			System.err.println("GameDB.readQuestions(List<Integer> excludedKeys)");
			System.err.println("excludedKeys: " + excludedKeys.toString());			
			System.err.println(e.getMessage());
		}
		finally
		{
			closeConnection();
		}
		
		return questions;		
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
			System.err.println("GameDB.readResponse(int responseID)");
			System.err.println("responseID: " + responseID);				
			System.err.println(e.getMessage());
		}
		finally
		{
			DatabaseInteractor.closeConnection();
		}
		
		return response;		
	}
	
	/**
	 * Attempts to read Response data from the database
	 * @param itemID item key
	 * @param questionID question key
	 * @param answerID answer key
	 * @return A Response item if successful; otherwise, null
	 */
	private static Response readResponseByCriteria(int itemID, int questionID, int answerID )
	{
		Response response = null;
		
		String query =
				"SELECT * " +
                "FROM	responses  " +
                "WHERE  itemID = " + itemID + " " +
                "AND    questionID = " + questionID + " " +
                "AND    answerID = " + answerID;
		
		ResultSet rs = executeQuery(query);
		
		try
		{
			rs.first();
			response = resultSetToResponse(rs);
		}
		catch (Exception e)
		{		
			System.out.println(e.getMessage());
		}
		finally
		{
			closeConnection();
		}
		
		return response;
	}
	
	/**
	 * Attempts to read the total number of responses for a particular question/item combo from the database
	 * @param questionID Question key
	 * @param itemID Item key
	 * @return The number of total responses for the specified question/item combo.
	 */
	private static int readTotalResponsesByQuestionAndItem(int questionID, int itemID)
	{
		int total = 0;
				
		String query =
				"SELECT questionID, itemID, SUM( count ) AS total " +
                "FROM	responses  " +
                "WHERE  questionID = " + questionID + " " +
                "AND    itemID = " + itemID + " " +
                "GROUP BY questionID, itemID";
		
		ResultSet rs = executeQuery(query);
		
		try
		{
			rs.first();
			total = rs.getInt("total");
		}
		catch (Exception e)
		{
			// If response combo doesn't exist, total remains 0
		}
		finally
		{
			closeConnection();
		}
		
		return total;		
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
	
	private static List<Answer> resultSetToAnswerList(ResultSet rs)
	{
		List<Answer> answers = new ArrayList<Answer>();
		
		try
		{
			while (rs.next())
			{
				Answer answer = resultSetToAnswer(rs);
				answers.add(answer);
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return answers;		
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
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Attempts to create a list of Items from a ResultSet
	 * @param rs ResultSet containing rows of Item data
	 * @return a List or Item objects
	 */
	private static List<Item> resultSetToItemList(ResultSet rs)
	{
		List<Item> items = new ArrayList<Item>();
		
		try
		{
			while (rs.next())
			{
				Item item = resultSetToItem(rs);
				items.add(item);
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return items;
	}
	
	private static Location resultSetToLocation(ResultSet rs)
	{
		try
		{
			return new Location(
					rs.getDouble("latitude"),
					rs.getDouble("longitude")
					);		
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
			return null;
		}
	}	
	
	private static List<Location> resultSetToLocationList(ResultSet rs)
	{
		List<Location> locations = new ArrayList<Location>();
		
		try
		{
			while (rs.next())
			{
				Location location = resultSetToLocation(rs);
				locations.add(location);
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return locations;		
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
	
	private static List<Question> resultSetToQuestionList(ResultSet rs)
	{
		List<Question> questions = new ArrayList<Question>();
		
		try
		{
			while (rs.next())
			{
				Question question = resultSetToQuestion(rs);
				questions.add(question);
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return questions;
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

	private static boolean updateResponseCount(int responseID, int count)
	{		
        String query =
				"UPDATE responses " +
                "SET	count = " + count + " " +
                "WHERE  responseID = " + responseID;
		
        ResultSet rs = executeQuery(query, true);

        // There's not a way to check for failure at this point, since we don't
        // forward exceptions
		return true;		
	}
}
