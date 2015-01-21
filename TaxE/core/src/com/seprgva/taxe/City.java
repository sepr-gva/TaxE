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
	String cityName;
	String cityIdentifier;
	Rail nextRail = null;
	Rail prevRail = null;
	Array<Train> trainsInStation;
	
	long time = TimeUtils.millis();
	
	public City(final int xCoord, final int yCoord, String name, String identifier, Texture cityTexture, Texture cityHoverTexture, TaxE gameInstance)
	{
		super(xCoord, yCoord, cityTexture, cityHoverTexture, false, gameInstance);
		cityName = name;
		cityIdentifier = identifier;
		trainsInStation = new Array<Train>();
		
		addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("down");
				if (game.phaseNo == 1 && game.turnNo == 1){
					createTrain(xCoord/32+1, yCoord/32+1, game.player1);
					game.gameMap.mapArray[(xCoord/32)+1][(yCoord/32)+1].currentTexture = game.cityred;
					
				}
				else if (game.phaseNo == 2 && game.turnNo == 1){
					createTrain(xCoord/32+1, yCoord/32+1, game.player2);
					game.gameMap.mapArray[(xCoord/32)+1][(yCoord/32)+1].currentTexture = game.cityred;
				}
				else{
					if (highlighted){
						for (City city : game.cityList){
							city.highlighted = false;
							city.currentTexture = city.defaultTexture;
						}
						currentTexture = game.cityred;
						if (game.phaseNo == 3){
							Train train = game.player1.trains.get(0);
							for (ArrayList<Tile> route : train.possibleRoutes){
								if (route.size() > 0){
									if (route.get(route.size() - 1) == game.gameMap.mapArray[(xCoord/32)+1][(yCoord/32)+1]){
										for (Tile tile : route){
											if (tile instanceof City){
												int[] coords = {tile.xCoord, tile.yCoord};
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
	
	private void createTrain(int X, int Y, Player owner)
	{
		Texture trainTexture;
		if (owner.playerNumber == 1){
			trainTexture = game.train1;
		}
		else{
			trainTexture = game.train2;
		}
		//I feel like this is over-complicated. Do we need a trainList and trainsInStation?
		Tile tileForTrain = game.gameMap.mapArray[X][Y];
		if (tileForTrain instanceof City){
			City cityForTrain = (City) tileForTrain;
			Train newTrain = new Train(game.trainID, game.gameMap.mapArray[X][Y], owner, X*32, Y*32, trainTexture);
			game.trainList.add(newTrain);
			cityForTrain.trainsInStation.add(newTrain);
			owner.trains.add(newTrain);
			game.gameMap.mapArray[X][Y] = cityForTrain;
			game.trainID++; //This needs to be implemented properly at some point
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
