package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Person {
	
	// Local Variables
	int id;
	String first;
	String last;
	Date birthday;
	Person father;
	Person mother;
	List<Person> children;
	
	// Constructors
	public Person(int id, String first, String last, Date birthday)
	{
		this.id = id;
		this.first = first;
		this.last = last;
		this.birthday = birthday;
		this.father = null;
		this.mother = null;
		this.children = new ArrayList<Person>();
	}
	
	// Preliminary work to create a Person from a ResultSet
	public Person(ResultSet rs)
	{
		try
		{
		   	 this.id = rs.getInt("person_id");
		     this.first = rs.getString("first_name");
		     this.last = rs.getString("last_name");
		     this.birthday = rs.getDate("birthday");	
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
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
	public Date getBirth()
	{
		return birthday;
	};	
	public void setBirth(Date pbirth)
	{
		birthday = pbirth;
	};
	
	
}
