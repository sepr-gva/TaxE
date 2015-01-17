package com.seprgva.taxe;

import com.badlogic.gdx.utils.Array;

public class City extends Tile
{
	String cityName;
	Rail nextRail = null;
	Rail prevRail = null;
	Array<Train> trainsInStation;
	
	public City(int xCoord, int yCoord, String name)
	{
		super(xCoord, yCoord, "City");
		cityName = name;
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
