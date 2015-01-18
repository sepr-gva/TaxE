package com.seprgva.taxe;

import com.badlogic.gdx.utils.Array;

public class City extends Tile
{
	String cityName;
	String cityIdentifier;
	Rail nextRail = null;
	Rail prevRail = null;
	Array<Train> trainsInStation;
	
	public City(int xCoord, int yCoord, String name, String identifier)
	{
		super(xCoord, yCoord, "City");
		cityName = name;
		cityIdentifier = identifier;
		trainsInStation = new Array<Train>();
	}
	
	@Override
	public String toString()
	{
		String returnString;
		returnString = "Tile at position " + this.x + ", " + this.y + " is city: " + cityName + ".";
		
		return returnString;
	}
	
}
