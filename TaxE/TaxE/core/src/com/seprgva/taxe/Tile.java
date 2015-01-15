package com.seprgva.taxe;

import com.badlogic.gdx.math.Rectangle;

public class Tile extends Rectangle
{
	//Tile is concrete as it represents a blank tile
	public int xPosition;
	public int yPosition;
	Tile[] neighbours = new Tile[4];	//Should be able to calculate neighbours using tile coordinates as a unique ID.
	public String tileType;
	
	public Tile(int xCoord, int yCoord, String type)
	{
		xPosition = xCoord;
		yPosition = yCoord;
		tileType = type;
	}
	
	@Override
	public String toString()
	{
		String returnString = "";
		returnString = "Tile at position " + xPosition + ", " + yPosition + " is empty.";
	
		return returnString;
	}
}
