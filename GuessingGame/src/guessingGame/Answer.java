package guessingGame;

public class Answer extends dbObject {
	
	// Local variables
	private String text;
	
	// Protected Members
	protected static String tableName = "answers";
	
	// Constructor
	public Answer(int id, String text)
	{
		this.key = id;
		this.text = text;
	}
	
	// Protected Methods
	protected static Object createFromRecord(Object record)
	{
		if (record != null)
		{
			// We need to get the row data for this.keyName and this.text
			//return new Answer(record[KeyName], record[Text]);
			return null;
		}
		else
		{
			return null;
		}
	}

}
