package com.seprgva.taxe;

public class City extends Tile
{
	String cityName;
	Rail nextRail = null;
	Rail prevRail = null;  //method needed to decided multi case
	Train[] trainsInStation;
	
	public City(int xCoord, int yCoord, String name)
	{
		super(xCoord, yCoord, "city");
		cityName = name;
	}
	
	@Override
	public String toString()
	{
		String returnString;
		returnString = "Tile at position " + xPosition + ", " + yPosition + " is city: " + cityName + ".";
		
		return returnString;
	}
}
