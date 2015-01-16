package com.seprgva.taxe;

public class Train
{
	int identifier;
	Tile currentLocation;
	//Player owner;
	
	public Train(int ID, Tile startingLocation)
	{
		identifier = ID;
		currentLocation = startingLocation;
	}
	
	public void traverse(Tile destination)
	{
		currentLocation = destination;
	}
	
	@Override
	public String toString()
	{
		String returnString = "Train with ID " + identifier + " is at location " + currentLocation.x + ", " + currentLocation.y;
		return returnString;
	}
}
