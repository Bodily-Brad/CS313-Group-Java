package guessingGame;

public class Response extends dbObject
{
	// Local Variables
	private int itemID;
	private int questionID;
	private int answerID;
	private int count;
	
	// Protected Member
	protected static String tableName = "responses";
	protected static String keyName = "responseID";
	protected static String defaultSearchField = "responseID";
	protected static String defaultSortField = "responseID";
	
	// Constructors
	public Response(int id, int itemID, int questionID, int answerID, int count)
	{
		this.key = id;
		this.itemID = itemID;
		this.questionID = questionID;
		this.answerID = answerID;
		this.count = count;
	}
	
	public Response()
	{
		this.key = -1;
		this.itemID = -1;
		this.questionID = -1;
		this.answerID = -1;
		this.count = -1;
	}
	
	// Properties
	public int getAnswerID() { return this.answerID; }
	public int getCount() { return this.count; }
	public int getItemID() { return this.itemID; }
	public int getQuestionID() { return this.questionID; }
	
	// Methods
	public static int GetItemIDWithLowestResponseCount()
	{
		// TODO: Implement Database search
		return -1;
	}
	
	public static int GetQuestionIDWithLowestResponseCountForItem(int itemID)
	{
		// TODO: Implement Database search
		return -1;
	}
	
	public static Response GetResponseByCriteria(int itemID, int questionID, int answerID)
	{
		// TODO: Implement Database Search
		return null;
	}
	
	public static int GetResponseCount(int itemID, int questionID, int answerID)
	{
		Response response = GetResponseByCriteria(itemID, questionID, answerID);
		if (response != null)
			return response.getCount();
		// Else
		return 0;
	}
	
	public static Boolean GetResponseExistsByCriteria(int itemID, int questionID, int answerID)
	{
		Response response = GetResponseByCriteria(itemID, questionID, answerID);
		if (response != null)
			return true;
		// Else
		return false;		
	}
	
	public static int GetTotalResponsesByQuestionAndItem(int questionID, int itemID)
	{
		// TODO: Implement Database functions
		return -1;
	}
	
	public static int IncrementCount(int itemID, int questionID, int answerID)
	{
		// Check for existing response
		Response response = GetResponseByCriteria(itemID, questionID, answerID);
		if (response != null)
		{
			int count = response.getCount();
			count++;
			return Update(response.getKey(), count);
		}
		else
		{
			int count = 1;
			return Insert(itemID, questionID, answerID, count);
		}
	}
	
	public static int Insert(int itemID, int questionID, int answerID, int count)
	{
		// TODO: Implement INSERT functionality
		return -1;
	}
	
	public static int Update(int id, int count)
	{
		// TODO: Implement UPDATE functionality
		return -1;
	}
	
	// Class Methods
	protected static Response createFromRecord(Object record)
	{
		// TODO: Implement Record to Response
		return null;
	}
	
	

}
