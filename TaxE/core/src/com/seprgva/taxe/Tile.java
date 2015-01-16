package com.seprgva.taxe;

import com.badlogic.gdx.math.Rectangle;

public class Tile extends Rectangle
{
	//Tile is concrete as it represents a blank tile
	Tile[] neighbours = new Tile[4];	//Should be able to calculate neighbours using tile coordinates as a unique ID.
	public String tileType;
	
	public Tile(int xCoord, int yCoord, String type)
	{
		tileType = type;
		
		this.x = xCoord;
		this.y = yCoord;
	}
	
	@Override
	public String toString()
	{
		String returnString = "";
		returnString = "Tile at position " + this.x + ", " + this.y + " is empty.";
	
		return returnString;
	}
}
