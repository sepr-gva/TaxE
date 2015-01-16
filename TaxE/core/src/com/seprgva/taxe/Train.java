package com.seprgva.taxe;

public class Train
{
	int identifier;
	Tile currentLocation;
	Player owner;
	//Player owner;
	
	public Train(int ID, Tile startingLocation, Player player)
	{
		identifier = ID;
		currentLocation = startingLocation;
		owner = player;
	}
	
	public void traverse(Tile destination)
	{
		currentLocation = destination;
	}
	
	@Override
	public String toString()
	{
		String returnString = "Train with ID " + identifier + " is at location " + currentLocation.xPosition + ", " + currentLocation.yPosition;
		return returnString;
	}
}
