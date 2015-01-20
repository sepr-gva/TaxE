package com.seprgva.taxe;

import com.badlogic.gdx.graphics.Texture;

public class Rail extends Tile
{
	
	boolean inCity;
	
	public Rail(int x, int y, Texture railTexture, boolean inCityTest, Map map)
	{
		super(x, y, railTexture, railTexture, false, null, map);
		inCity = inCityTest;
	}
	
	@Override
	public String toString()
	{
		String returnString = "";
		returnString = "Tile at position " + xCoord + ", " + yCoord + " is a rail";
		
		return returnString;
	}
}
