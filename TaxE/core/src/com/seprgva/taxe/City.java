package com.seprgva.taxe;

public class City extends Tile
{
	String cityName;
	
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
