package com.seprgva.taxe;

public class Tile
{
	//Tile is concrete as it represents a blank tile
	public int xPosition;
	public int yPosition;
	Tile[] neighbours = new Tile[4];	//Should be able to calculate neighbours using tile coordinates as a unique ID.
	public String tileType;
	
	public Tile(int xCoord, int yCoord)
	{
		xPosition = xCoord;
		yPosition = yCoord;
		tileType = null;
	}
	
	@Override
	public String toString()
	{
		String returnString = "";
		if (tileType == null)
		{
			returnString = "Tile at position " + xPosition + ", " + yPosition + " is empty.";
		}
		else
		{
			returnString = "Tile at position " + xPosition + ", " + yPosition + " contains a " + tileType + ".";
		}
		return returnString;
	}
}
