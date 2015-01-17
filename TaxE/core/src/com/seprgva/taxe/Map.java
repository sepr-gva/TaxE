package com.seprgva.taxe;

public class Map 
{
	public int xSize;
	public int ySize;
	public Tile[][] mapArray;
	
	public Map()
	{
		xSize = 40;
		ySize = 40;
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
		//Set up the map to have alternate player 1 and 2 buildable tiles
		int count = 0;
		for (int i=0; i<xSize; i++)
		{
			for (int j=0; j<ySize; j++)
			{
				String type;
				
				if ((count & 1) == 0){
					type = "player1Build";			
				}
				else{
					type = "player2Build";
				}
				
				Tile tempTile = new Tile(i, j, type);
				tempTile.width = 32;
				tempTile.height = 32;
				
				mapArray[i][j] = tempTile;
			}
		}
	}
}
