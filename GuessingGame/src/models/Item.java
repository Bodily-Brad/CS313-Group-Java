package models;

public class Item {

	// Constants and Settings
	private final int DEFAULT_ID = -1;
	private final String DEFAULT_DESCRIPTION = null;
	
	// Local Variables
	private int id;
	private String description;
	
	// Constructors
	public Item(int id, String description)
	{
		this.id = id;
		this.description = description;
	}
	
	public Item()
	{
		this.id = DEFAULT_ID;
		this.description = DEFAULT_DESCRIPTION;
	}
	
	// Properties
	public int getID() { return this.id; }
	
	public String getDescription() { return this.description; }
	public void setDescription(String description) { this.description = description; }	
}
