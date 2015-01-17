package com.seprgva.taxe;

public class Rail extends Tile
{
	Rail prevRail = null;
	Rail nextRail = null;
	
	public Rail(int xCoord, int yCoord)
	{
		super(xCoord, yCoord, "Rail");
	}
	
	@Override
	public String toString()
	{
		String returnString = "";
		returnString = "Tile at position " + this.x + ", " + this.y + " is a rail";
		
		return returnString;
	}
	
	public Rail getPrevRail()
	{
		return prevRail;
	}
	
	public Rail getNextRail()
	{
		return nextRail;
	}
}