package com.seprgva.taxe;

public class Rail extends Tile
{
	Rail prevRail;
	Rail nextRail;
	boolean junction;
	
	public Rail(int xCoord, int yCoord)
	{
		super(xCoord, yCoord, "rail");
	}
	
	@Override
	public String toString()
	{
		String returnString = "";
		returnString = "Tile at position " + xPosition + ", " + yPosition + " is a rail";
		
		return returnString;
	}
}
