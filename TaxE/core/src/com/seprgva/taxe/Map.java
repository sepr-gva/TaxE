package com.seprgva.taxe;

public class Map 
{
	public int xSize;
	public int ySize;
	public Tile[][] mapArray;
	
	public Map()
	{
		xSize = 39;
		ySize = 39;
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
				
				count++;
			}
		}
		//Add the cities
		mapArray[5][5] = new City(5,5, "City 1", "Ci1");
		mapArray[34][5] = new City(34,5, "City 2", "Ci2");
		mapArray[12][12] = new City(12,12, "City 3", "Ci3");
		mapArray[19][19] = new City(19,19, "City 4", "Ci4");
		mapArray[27][27] = new City(27,27, "City 5", "Ci5");
		mapArray[5][34] = new City(5,34, "City 6", "Ci6");
		mapArray[34][34] = new City(34,34, "City 7", "Ci7");
		
		
		
		
		
		
	}
}
