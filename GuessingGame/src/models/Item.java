package models;

import guessingGame.dbObject;

public class Item extends dbObject {

	// Local Variables
	private int itemID;
	private String description;
	
	// Protected Members
	protected static String tableName = "items";
	protected static String keyName = "itemID";
	protected static String defaultSearchField = "description";
	protected static String defaultSortField = "description";
	
	// Constructors
	public Item(int itemID, String description)
	{
		this.itemID = itemID;
		this.description = description;
	}
	
	public Item()
	{
		this.itemID = -1;
		this.description = null;
	}
	
	// Properties
	public int getItemID() { return this.itemID; }
	
	public String getDescription() { return this.description; }
	public void setDescription(String description) { this.description = description; }
	
	// Class Methods
	protected static Object createFromRecord(Object record)
	{
		// TODO: Need to be able to get field data from a record object
		return null;
	}
	
	// Methods
	public static Boolean GetItemExistsByDescription(String description)
	{
		// TODO: Database Search
		
		String query =
				"SELECT * " +
				"FROM " + tableName +
				"WHERE description LIKE :description";
		
		// TODO: Prepare statement if possible
		
		return false;		
	}
	
	public static int Insert(String description)
	{
		// TODO: Finish this function
		return -1;
	}
	
}
