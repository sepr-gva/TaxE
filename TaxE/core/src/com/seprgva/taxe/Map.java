package com.seprgva.taxe;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Map 
{
	public int xSize;
	public int ySize;
	public Tile[][] mapArray;
	TaxE game;
	
	public Map(final TaxE gameInstance)
	{
		game = gameInstance;
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
	
	public Tile getTile(int x, int y){
		return mapArray[x][y];
	}
	
	private void initialise()
	{
		//Set up the map to have alternate player 1 and 2 buildable tiles
		for (int i=0; i<xSize; i++){
			
			for (int j=0; j<ySize; j++){
				Tile tempTile = new Tile(i*32, j*32, game.greenSquare, game.redSquare, true);	
				mapArray[i][j] = tempTile;			
			}
		}
		City city = new City(32,32, "City 1", "Ci1", game.brownSquare);
		mapArray[32][32] = city;
		game.cityList.add(city);
		
		//Add the cities
		city = new City(5,ySize-6, "City 2", "Ci1", game.brownSquare);
		game.cityList.add(city);
		mapArray[5][ySize-6] = city;
		city = new City(34,ySize-6, "City 3", "Ci2", game.brownSquare);
		game.cityList.add(city);
		mapArray[34][ySize-6] = city;
		/*
		city = new City(12,ySize-13, "City 3", "Ci3");
		cityList.add(city);
		mapArray[12][ySize-13] = city;
		city = new City(19,ySize-20, "City 4", "Ci4");
		cityList.add(city);
		mapArray[19][ySize-20] = city;
		city = new City(27,ySize-28, "City 5", "Ci5");
		cityList.add(city);
		mapArray[27][ySize-28] = city;
		city = new City(5,ySize-35, "City 6", "Ci6");
		cityList.add(city);
		mapArray[5][ySize-35] = city;
		city = new City(34,ySize-35, "City 7", "Ci7");
		cityList.add(city);
		mapArray[34][ySize-35] = city;
		
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
		
		
		*/
		
		
	}
}
