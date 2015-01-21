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
	
	public City(int x, int y, String name, String identifier, Texture cityTexture, Texture cityHoverTexture, Map map)
	{
		super(x, y, cityTexture, cityHoverTexture, false, null, map);
		cityName = name;
		cityIdentifier = identifier;
		trainsInStation = new Array<Train>();
		
		addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("down");
				selected = true;
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
