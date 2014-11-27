package com.seprgva.taxe;

public class Map 
{
	public int xSize;
	public int ySize;
	
	private Map()
	{
		xSize = 20;
		ySize = 20;
		this.initialise();
	}
	
	private void initialise()
	{
		// TODO Auto-generated method stub
		
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
			
		}
	}
}
