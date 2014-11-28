package models;

public class Question
{	
	// Constants and Settings
	private final int DEFAULT_ID = -1;
	private final String DEFAULT_TEXT = null;
	
	// Local Variables
	private int id;
	private String text;
	
	// Constructors
	public Question(int id, String text)
	{
		this.id = id;
		this.text = text;
	}
	
	public Question()
	{
		this.id = DEFAULT_ID;
		this.text = DEFAULT_TEXT;
	}

	// Properties
	public int getID() { return this.id; }
	
	public String getText() { return this.text; }
	public void setText(String text) { this.text = text; }
}
