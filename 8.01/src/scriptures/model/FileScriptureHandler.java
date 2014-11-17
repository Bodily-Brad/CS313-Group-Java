package scriptures.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileScriptureHandler implements ScriptureDataHandler {

	// Local Variables
	private String fileName;
	
	// Constructor
	public FileScriptureHandler(String fileName)
	{
		this.fileName = fileName;
	}
	
	// Properties
	public String getFileName() { return fileName; }
	public void setFileName(String fileName) { this.fileName = fileName; }
	
	// Class Methods
	@Override
	public List<Scripture> getFavoriteScriptures()
	{
		List<Scripture> list = new ArrayList<Scripture>();
		
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(getFileName()));
			
			String line;
			
			while ((line = reader.readLine()) != null )
			{
				Scripture scripture = new Scripture();
				scripture.loadFromFileString(line);
				list.add(scripture);
			}
		}
		catch(IOException e)
		{
			// Maybe file doesn't exist - try to create one
			try
			{
				Scripture scripture = new Scripture("Test",1,1);
				BufferedWriter writer = new BufferedWriter(new FileWriter(getFileName(), true));
				
				writer.write(scripture.toFileString() + "\n");
				writer.close();				
			}
			catch(IOException e2)
			{
				e.printStackTrace();
				e2.printStackTrace();
			}
			
		}
		
		return list;
	}
	
	// Methods
	public void addScripture(Scripture scripture)
	{
		try
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(getFileName(), true));
			
			writer.write(scripture.toFileString() + "\n");
			writer.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
}
