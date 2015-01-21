package com.seprgva.taxe;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Map 
{
	public int xSize;
	public int ySize;
	public Tile[][] mapArray;
	TaxE game;
	ArrayList<ArrayList<Tile>> routeList = new ArrayList<ArrayList<Tile>>();
	
	Texture player1Tower;
	
	public Map(final TaxE gameInstance)
	{
		game = gameInstance;
		xSize = 39;
		ySize = 39;
		mapArray = new Tile[xSize][ySize];
		player1Tower = new Texture(Gdx.files.internal("gameGraphics/turretTile.png"));
		player1Tower.setFilter(TextureFilter.MipMapNearestLinear, TextureFilter.Linear);
		this.initialise();
		//routeList = new ArrayList<ArrayList<Rail>>();
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
	
	private void createRoute(int[][] listOfTiles){
		int x, y;
		ArrayList<Tile> route = new ArrayList<Tile>(); 
		int[] prevTile = {0,0};
		for (int[] tiles : listOfTiles){
			x = tiles[0];
			y = tiles[1];
			if (mapArray[x][y] instanceof City){
			}
			else{
				Rail newRail = new Rail(x*32, y*32, game.ud, false, game);
				newRail.neighbours[0] = mapArray[x][y-1];
				mapArray[x][y-1].neighbours[2] = newRail;;
				newRail.neighbours[1] = mapArray[x-1][y];
				mapArray[x-1][y].neighbours[3] = newRail;
				newRail.neighbours[2] = mapArray[x][y+1];
				mapArray[x][y+1].neighbours[0] = newRail;
				newRail.neighbours[3] = mapArray[x+1][y];
				mapArray[x+1][y].neighbours[1] = newRail;
				route.add(newRail);
				mapArray[x][y] = newRail;			
				}
			prevTile = tiles;
		}
		//Adds an ArrayList containing a route to an ArrayList of routes.
		routeList.add(route);
	}
	
	private void chooseRailTextures(){
		for (int i = 0; i < routeList.size(); i++){
			for (Tile rail : routeList.get(i)){
				if (rail.neighbours[0] instanceof Rail || rail.neighbours[0] instanceof City){
					if (rail.neighbours[1] instanceof Rail || rail.neighbours[1] instanceof City){
						rail.defaultTexture = game.ld;
						rail.hoverTexture = game.ld;
						rail.currentTexture = game.ld;
						if (rail.neighbours[2] instanceof Rail || rail.neighbours[2] instanceof City){
							rail.defaultTexture = game.udl;
							rail.hoverTexture = game.udl;
							rail.currentTexture = game.udl;
							rail.junction = true;
							if (rail.neighbours[3] instanceof Rail || rail.neighbours[3] instanceof City){
								rail.defaultTexture = game.junction;
								rail.hoverTexture = game.junction;
								rail.currentTexture = game.junction;
								rail.junction = true;
							}
						}
						else if (rail.neighbours[3] instanceof Rail || rail.neighbours[3] instanceof City){
							rail.defaultTexture = game.dlr;
							rail.hoverTexture = game.dlr;
							rail.currentTexture = game.dlr;
							rail.junction = true;
						}
					}
					else if (rail.neighbours[2] instanceof Rail || rail.neighbours[2] instanceof City){
						rail.defaultTexture = game.ud;
						rail.hoverTexture = game.ud;
						rail.currentTexture = game.ud;
						if (rail.neighbours[3] instanceof Rail || rail.neighbours[3] instanceof City){
							rail.defaultTexture = game.udr;
							rail.hoverTexture = game.udr;
							rail.currentTexture = game.udr;
							rail.junction = true;
						}
					}
					else if (rail.neighbours[3] instanceof Rail || rail.neighbours[3] instanceof City){
						rail.defaultTexture = game.rd;
						rail.hoverTexture = game.rd;
						rail.currentTexture = game.rd;
					}
					else{
						rail.defaultTexture = game.emptyTile;
						rail.hoverTexture = game.emptyTile;
						rail.currentTexture = game.emptyTile;
					}
				}
				else if (rail.neighbours[1] instanceof Rail || rail.neighbours[1] instanceof City){
					if (rail.neighbours[2] instanceof Rail || rail.neighbours[2] instanceof City){
						rail.defaultTexture = game.lu;
						rail.hoverTexture = game.lu;
						rail.currentTexture = game.lu;
						if (rail.neighbours[3] instanceof Rail || rail.neighbours[3] instanceof City){
							rail.defaultTexture = game.ulr;
							rail.hoverTexture = game.ulr;
							rail.currentTexture = game.ulr;
							rail.junction = true;
						}
					}
					else if(rail.neighbours[3] instanceof Rail || rail.neighbours[3] instanceof City){
						rail.defaultTexture = game.lr;
						rail.hoverTexture = game.lr;
						rail.currentTexture = game.lr;
					}
					else{
						rail.defaultTexture = game.emptyTile;
						rail.hoverTexture = game.emptyTile;
						rail.currentTexture = game.emptyTile;
					}
				}
				else if (rail.neighbours[2] instanceof Rail || rail.neighbours[2] instanceof City){
					if (rail.neighbours[3] instanceof Rail || rail.neighbours[3] instanceof City){
						rail.defaultTexture = game.ru;
						rail.hoverTexture = game.ru;
						rail.currentTexture = game.ru;
					}
					else{
						rail.defaultTexture = game.emptyTile;
						rail.hoverTexture = game.emptyTile;
						rail.currentTexture = game.emptyTile;
					}
				}
				else{
					rail.defaultTexture = game.emptyTile;
					rail.hoverTexture = game.emptyTile;
					rail.currentTexture = game.emptyTile;
				}
			}
		}
	}
	
	
	
	public ArrayList<Tile> getRoute(Tile source, Tile destination){
		ArrayList<Tile> selectedRoute = new ArrayList<Tile>();
		
		if ((source instanceof City) && (destination instanceof City)){
			for (ArrayList<Tile> route : routeList){
				if ((route.get(0).getCoords() == source.getCoords()) && (route.get(route.size()-1).getCoords() == destination.getCoords())){
					selectedRoute = route;
				}
			}
		}
		
		return selectedRoute;
	}
	
	public Tile getTile(int x, int y){
		return mapArray[x][y];
	}
	
	private void createCity(int i, int j, String cityName, String cityID){
		City city = new City((i*32)-32, (j*32)-32, cityName, cityID, game.city, game.cityblue, game);
		mapArray[i][j] = city;
		game.cityList.add(city);
	}
	
	private void initialise()
	{
		//Set up the map to have alternate player 1 and 2 buildable tiles
		for (int i=0; i<xSize; i++){
			
			for (int j=0; j<ySize; j++){
				Tile tempTile = new Tile(i*32, j*32, game.emptyTile, game.emptyTileHover, true, game);	
				mapArray[i][j] = tempTile;			
			}
		}
		
		createCity(2,2, "City 1", "CY1");
		createCity(2,10, "City 2", "CY2");
		createCity(6,6, "City 3", "CY3");
		
		//Test rail - iterating was easier than writing loads of code.
		//Also, at the moment, rails MUST be placed after cities.
		int[][] testRoute = {{2,2},{2,3},{2,4},{2,5},{2,6},{2,7},{2,8},{2,9},{2,10}};
		createRoute(testRoute);
		int [][] testRoute2 = {{2,2},{3,2},{4,2},{5,2},{6,2},{6,3},{6,4},{6,5},{6,6}};
		createRoute(testRoute2);
		chooseRailTextures();
		
		System.out.println(mapArray[7][2]);
		
		/*
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
		*/
		
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
