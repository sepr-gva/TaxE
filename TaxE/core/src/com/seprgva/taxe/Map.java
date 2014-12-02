package com.seprgva.taxe;

public class Map 
{
	public int xSize;
	public int ySize;
	public int[][] mapArray;
	
	private Map()
	{
		xSize = 20;
		ySize = 20;
		this.initialise();
	}
	
	private void initialise()
	{
		Tile tempTile = new Tile(0,0);
		for (int i=0; i<xSize; i++)
		{
			tempTile.xPosition = i;
			for (int j=0; j<ySize; j++)
			{
				tempTile.yPosition = j;
			}
		}
	}

	//Nested class of Tile. Will be used for Map initialisation. 
	public class Tile
	{
		public int xPosition;
		public int yPosition;
		Tile[] neighbours = new Tile[4];	//Should be able to calculate neighbours using tile coordinates as a unique ID.
		public boolean isOccupied;
		
		public Tile(int xCoord, int yCoord)
		{
			xPosition = xCoord;
			yPosition = yCoord;
			isOccupied = false;
		}
	}
}
