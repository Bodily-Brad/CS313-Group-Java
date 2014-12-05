package models;

public class Location {
	
	// Local Variables
	double latitude;
	double longitude;
	
	// Constructor
	public Location(double latitude, double longitude)
	{
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Location()
	{
		this.latitude = 0.0;
		this.longitude = 0.0;
	}
	
	// Properties
	public double getLatitude() { return latitude; }
	public void setLatitude(double latitude) { this.latitude = latitude; }
	
	public double getLongitude() { return longitude; }
	public void setLongitude(double longitude) { this.longitude = longitude; }
	
	// Methods
	@Override
	public String toString()
	{
		return "(" + latitude + "," + longitude + ")";
	}

}
