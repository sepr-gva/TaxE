package com.seprgva.taxe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
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
				if (highlighted){
					for (City city : game.cityList){
						city.highlighted = false;
						city.currentTexture = city.defaultTexture;
					}
					currentTexture = game.cityred;
					if (game.phaseNo == 3){
						Train train = game.player1.trains.get(0);
						train.route = game.gameMap.getRoute(train.currentLocation, game.gameMap.mapArray[xCoord/32][yCoord/32]);
						for (Tile tile : train.route){
							System.out.println("" + tile);
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

	
	@Override
	public String toString()
	{
		String returnString;
		returnString = "Tile at position " + this.getX() + ", " + this.getY() + " is city: " + cityName + ".";
		
		return returnString;
	}
	
}
