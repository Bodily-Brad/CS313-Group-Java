package models;

public class Response
{
	// Constants and Settings
	private final int DEFAULT_ID = -1;
	private final int DEFAULT_ANSWERID = -1;
	private final int DEFAULT_COUNT = -1;			
	private final int DEFAULT_ITEMID = -1;
	private final int DEFAULT_QUESTIONID = -1;
	
	// Local Variables
	private int id;
	private int itemID;
	private int questionID;
	private int answerID;
	private int count;
		
	// Constructors
	public Response(int id, int itemID, int questionID, int answerID, int count)
	{
		this.id = id;
		this.itemID = itemID;
		this.questionID = questionID;
		this.answerID = answerID;
		this.count = count;
	}
	
	public Response()
	{
		this.id = DEFAULT_ID;
		this.itemID = DEFAULT_ITEMID;
		this.questionID = DEFAULT_QUESTIONID;
		this.answerID = DEFAULT_ANSWERID;
		this.count = DEFAULT_COUNT;
	}
	
	// Properties
	public int getID() { return this.id; }
	public void setID(int id) { this.id = id; }
	
	public int getAnswerID() { return this.answerID; }
	public void setAnswerID(int id) { this.answerID = id; }
	
	public int getCount() { return this.count; }
	public void setCount(int count) { this.count = count; }
	
	public int getItemID() { return this.itemID; }
	public void setItemID(int id) { this.itemID = id; }
	
	public int getQuestionID() { return this.questionID; }
	public void setQuestionID(int id) { this.questionID = id; }
}
