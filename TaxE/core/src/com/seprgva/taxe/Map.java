package com.seprgva.taxe;

public class Map 
{
	public int xSize;
	public int ySize;
	public Tile[][] mapArray;
	
	public Map()
	{
		xSize = 20;
		ySize = 20;
		mapArray = new Tile[xSize][ySize];
		this.initialise();
	}
	
	@Override
	public String toString()
	{
		String returnString = "";
		returnString += "Game map details...";
		returnString += " Map height: " + this.xSize;
		returnString += " Map width: " + this.ySize;
		return returnString;
	}
	
	private void initialise()
	{
		for (int i=0; i<xSize; i++)
		{
			for (int j=0; j<ySize; j++)
			{
				Tile tempTile = new Tile(i,j);
				mapArray[i][j] = tempTile;
			}
		}
	}

	//Nested class of Tile. Will be used for Map initialisation. 
	public class Tile
	{
		public int xPosition;
		public int yPosition;
		Tile[] neighbours = new Tile[4];	//Should be able to calculate neighbours using tile coordinates as a unique ID.
		public String occupiedBy;
		
		public Tile(int xCoord, int yCoord)
		{
			xPosition = xCoord;
			yPosition = yCoord;
			occupiedBy = null;
		}
		
		@Override
		public String toString()
		{
			String returnString = "";
			if (occupiedBy == null)
			{
				returnString = "Tile at position " + xPosition + ", " + yPosition + " is empty.";
			}
			else
			{
				returnString = "Tile at position " + xPosition + ", " + yPosition + " contains a " + occupiedBy + ".";
			}
			return returnString;
		}
	}
}
