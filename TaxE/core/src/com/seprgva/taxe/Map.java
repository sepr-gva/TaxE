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
		mapArray[5][ySize-6] = new City(5,ySize-6, "City 1", "Ci1");
		mapArray[34][ySize-6] = new City(34,ySize-6, "City 2", "Ci2");
		mapArray[12][ySize-13] = new City(12,ySize-13, "City 3", "Ci3");
		mapArray[19][ySize-20] = new City(19,ySize-20, "City 4", "Ci4");
		mapArray[27][ySize-28] = new City(27,ySize-28, "City 5", "Ci5");
		mapArray[5][ySize-35] = new City(5,ySize-35, "City 6", "Ci6");
		mapArray[34][ySize-35] = new City(34,ySize-35, "City 7", "Ci7");
		
		//Add the rail, this can be replaced with a procedural generation method
		for(int i=0; i<3; i++){
			//starting at the top left city
			//move from cities location up one tile for three tiles
			mapArray[5][ySize-(5-i)] = new Rail(5, ySize-(5-i));
		}
		for(int i=0; i<11; i++){
			//starting from the right of where we left off
			//move from that rail one tile right for 11 tiles
			mapArray[6+i][ySize-3] = new Rail(6+i, ySize-3);
		}
		for(int i=0; i<3; i++){
			//don't get confused by y-size, this just makes sure that mapArray matches
			//what libgdx draws.
			mapArray[16][ySize-(4+i)] = new Rail(16, ySize-(4+i));
		}
		
		
		
		
		
	}
}
