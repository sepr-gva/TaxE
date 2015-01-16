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
		//Sets up mapArray with empty tiles.
		for (int i=0; i<xSize; i++)
		{
			for (int j=0; j<ySize; j++)
			{
				Tile tempTile = new Tile(i, j, "blank");
				tempTile.x = i*32;
				tempTile.y = j*32;
				tempTile.width = 32;
				tempTile.height = 32;
				
				mapArray[i][j] = tempTile;
			}
		}
	}
}
