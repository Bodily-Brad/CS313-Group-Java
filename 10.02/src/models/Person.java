package models;

import java.util.ArrayList;
import java.util.List;

public class Person {
	
	// Local Variables
	int id;
	String first;
	String last;
	String birthday;
	Person father;
	Person mother;
	List<Person> children;
	
	public Person(int pid, String pfirst, String plast, String pbirthday)
	{
		id = pid;
		first = pfirst;
		last = plast;
		birthday = pbirthday;
		father = null;
		mother = null;
		children = new ArrayList<Person>();
	}
	
	// Properties
	
	// Children
	public List<Person> getChildren() { return this.children; }
	public void setChildren(List<Person> children) { this.children = children; }
	
	// Father
	public Person getFather() { return this.father; }
	public void setFather(Person father) { this.father = father; }
	
	// Mother
	public Person getMother() { return this.mother; }
	public void setMother(Person mother) { this.mother = mother; }
	
	// Id
	public int getId()	{ return id;	}	
	public void setId(int pid)	{ id = pid;	}
	
	// First name
	public String getFirst()
	{
		return first;
	};	
	public void setFirst(String pfirst)
	{
		first = pfirst;
	};
	
	// Last name
	public String getLast()
	{
		return last;
	};	
	public void setLast(String plast)
	{
		last = plast;
	};
	
	// Birth date
	public String getBirth()
	{
		return birthday;
	};	
	public void setBirth(String pbirth)
	{
		birthday = pbirth;
	};
	
	
}
