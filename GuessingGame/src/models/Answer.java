package models;

/*
 * Represents a single answer, with an accompanying ID
 */
public class Answer {
	// Constants and Settings
	private final int DEFAULT_ID = -1;
	private final String DEFAULT_TEXT = null;
	
	// Local variables
	private int id;
	private String text;
		
	// Constructor
	public Answer(int id, String text)
	{
		this.id = id;
		this.text = text;
	}
	
	public Answer()
	{
		this.id = DEFAULT_ID;
		this.text = DEFAULT_TEXT;
	}
	
	// Properties
	public int getID() { return this.id; }
	
	// Even though key and ID are basically one and the same here,
	// this is specifically the database key - which should probably
	// go away before the end
	public int getKey() { return this.id; }
	
	public String getText() { return this.text; }
	public void setText(String text) { this.text = text; }
}
