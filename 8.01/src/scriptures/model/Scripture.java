package scriptures.model;

public class Scripture {
	private String book;
	private int chapter;
	private int verse;
	
	// Constructors
	public Scripture()
	{
		setBook("");
		setChapter(1);
		setVerse(1);
	}
	
	public Scripture(String book, int chapter, int verse)
	{
		this.setBook(book);
		this.setChapter(chapter);
		this.setVerse(verse);
	}
	
	// Properties
	public String getBook() { return book; }
	public void setBook(String book) { this.book = book; }
	
	public int getChapter() { return chapter; } 
	public void setChapter(int chapter) { this.chapter = chapter; }
	
	public int getVerse() { return verse; }
	public void setVerse(int verse) { this.verse = verse; }
	
	// Methods
	@Override
	public String toString()
	{
		return book + " " + chapter + ":" + verse;
	}

}
