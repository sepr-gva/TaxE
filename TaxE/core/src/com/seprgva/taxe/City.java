package com.seprgva.taxe;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class City extends Tile
{
	String cityName;					//full name of the city
	String cityIdentifier;				//the cities 3 letter identifier
	Array<Train> trainsInStation;		//list of trains in the city
										//this implementation will only have 1 train in a city at once
	
	long time = TimeUtils.millis();
	
	public City(final int xCoord, final int yCoord, String name, String identifier, Texture cityTexture, Texture cityHoverTexture, TaxE gameInstance)
	{
		super(xCoord, yCoord, cityTexture, cityHoverTexture, false, gameInstance);	//set attributes of tile
		cityName = name;						//set unique attributes of city
		cityIdentifier = identifier;
		trainsInStation = new Array<Train>();
		
		addListener(new InputListener(){		//tell the city to listen out for a mouse click
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("down");
				if (game.phaseNo == 1 && game.turnNo == 1){					//allow player 1 to set their start location on turn 1
					createTrain(xCoord/32+1, yCoord/32+1, game.player1);
					game.gameMap.mapArray[(xCoord/32)+1][(yCoord/32)+1].currentTexture = game.cityred;
					
				}
				else if (game.phaseNo == 2 && game.turnNo == 1){			//allow player 2 to set their start location on turn 1
					createTrain(xCoord/32+1, yCoord/32+1, game.player2);
					game.gameMap.mapArray[(xCoord/32)+1][(yCoord/32)+1].currentTexture = game.cityred;
				}
				else{												//cities will be highlighted if they neighbour
					if (highlighted){								//the city that the player's train is currently at
						for (City city : game.cityList){			//set all the highlighted cities back to...
							city.highlighted = false;				//default and...
							city.currentTexture = city.defaultTexture;
						}
						currentTexture = game.cityred;				//... give the clicked city a red border
						if (game.phaseNo == 3){
							Train train = game.player1.trains.get(0);				//get the player's train
							for (ArrayList<Tile> route : train.possibleRoutes){		//for each route in the train's possible routes
								if (route.size() > 0){
									if (route.get(route.size() - 1) == game.gameMap.mapArray[(xCoord/32)+1][(yCoord/32)+1]){
										for (Tile tile : route){
											if (tile instanceof City){						//set appropriate marker coordinates for the...
												int[] coords = {tile.xCoord, tile.yCoord};	//train to move towards
												train.route.add(coords);
											}
											else if (tile.currentTexture == game.ld){
												int[] coords = {tile.xCoord, tile.yCoord};
												train.route.add(coords);
											}
											else if (tile.currentTexture == game.lu){
												int[] coords = {tile.xCoord, tile.yCoord + 16};
												train.route.add(coords);
											}
											else if (tile.currentTexture == game.ru){
												int[] coords = {tile.xCoord, tile.yCoord};
												train.route.add(coords);
											}
											else if (tile.currentTexture == game.rd){
												int[] coords = {tile.xCoord, tile.yCoord + 16};
												train.route.add(coords);
											}
											else if (tile.currentTexture == game.ud){
												int[] coords = {tile.xCoord, tile.yCoord};
												train.route.add(coords);
											}
											else if (tile.currentTexture == game.lr){
												int[] coords = {tile.xCoord, tile.yCoord + 17};
												train.route.add(coords);
											}
										}
										train.clear();
										SequenceAction sequenceAction = new SequenceAction();
										for (int[] array : train.route){							//for each set of coordinates in the train's final route
											System.out.println("x coordinate: " + array[0]);
											System.out.println("y coordinate: " + array[1]);
											
											MoveToAction moveAction = new MoveToAction();
										    moveAction.setPosition(array[0], array[1]);			//create a move action for the train that sends it to these coordinates
										    moveAction.setDuration(1f);						//over the course of one second
										    sequenceAction.addAction(moveAction);		//add it to a sequence action so that the train doesn't move towards all markers at once
										}
										train.addAction(sequenceAction);		//give the train the list of actions to perform
										train.route.clear();				//now that we don't need it anymore wipe the route for safety
										train.currentLocation = game.gameMap.mapArray[(xCoord/32)+1][(yCoord/32)+1];	//set the trains location to its destination
									}
								}
							}
						}
						else if (game.phaseNo == 4){
							Train train = game.player2.trains.get(0);
							for (ArrayList<Tile> route : train.possibleRoutes){
								if (route.size() > 0){
									if (route.get(route.size() - 1) == game.gameMap.mapArray[(xCoord/32)+1][(yCoord/32)+1]){
										for (Tile tile : route){
											if (tile instanceof City){
												int[] coords = {tile.xCoord + 17, tile.yCoord};
												train.route.add(coords);
											}
											else if (tile.currentTexture == game.ld){
												int[] coords = {tile.xCoord + 17, tile.yCoord + 16};
												train.route.add(coords);
											}
											else if (tile.currentTexture == game.lu){
												int[] coords = {tile.xCoord + 17, tile.yCoord};
												train.route.add(coords);
											}
											else if (tile.currentTexture == game.ru){
												int[] coords = {tile.xCoord + 17, tile.yCoord + 16};
												train.route.add(coords);
											}
											else if (tile.currentTexture == game.rd){
												int[] coords = {tile.xCoord + 17, tile.yCoord};
												train.route.add(coords);
											}
											else if (tile.currentTexture == game.ud){
												int[] coords = {tile.xCoord + 17, tile.yCoord};
												train.route.add(coords);
											}
											else if (tile.currentTexture == game.lr){
												int[] coords = {tile.xCoord, tile.yCoord};
												train.route.add(coords);
											}
										}
										train.clear();
										SequenceAction sequenceAction = new SequenceAction();
										for (int[] array : train.route){
											System.out.println("x coordinate: " + array[0]);
											System.out.println("y coordinate: " + array[1]);
											
											MoveToAction moveAction = new MoveToAction();
										    moveAction.setPosition(array[0], array[1]);
										    moveAction.setDuration(1f);
										    sequenceAction.addAction(moveAction);
										}
										train.addAction(sequenceAction);
										train.route.clear();
										train.currentLocation = game.gameMap.mapArray[(xCoord/32)+1][(yCoord/32)+1];
									}
								}
							}
						}
					}
				}
				System.out.println("got to here");
				return true;
			}
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("up");
		    }
		});
	}
	//all trains begin inside of cities so the method to create them is within the city class
	private void createTrain(int X, int Y, Player owner)
	{
		Texture trainTexture;
		if (owner.playerNumber == 1){
			trainTexture = game.train1;		//set the appropriate graphic for the train
		}
		else{
			trainTexture = game.train2;
		}
		Tile tileForTrain = game.gameMap.mapArray[X][Y];
		if (tileForTrain instanceof City){
			City cityForTrain = (City) tileForTrain;	
			Train newTrain = new Train(game.trainID, game.gameMap.mapArray[X][Y], owner, X*32, Y*32, trainTexture);	//create the train and give it its appropriate characteristics
			game.trainList.add(newTrain);
			cityForTrain.trainsInStation.add(newTrain);
			owner.trains.add(newTrain);
			game.gameMap.mapArray[X][Y] = cityForTrain;
			game.trainID++;
		}
	}

	
	@Override
	public String toString()
	{
		String returnString;
		returnString = "Tile at position " + this.getX() + ", " + this.getY() + " is city: " + cityName + ".";
		return returnString;
	}
	
}
