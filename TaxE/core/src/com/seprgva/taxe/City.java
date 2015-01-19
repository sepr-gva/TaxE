package com.seprgva.taxe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
	
	public City(int x, int y, String name, String identifier, Texture cityTexture)
	{
		super(x, y, cityTexture, cityTexture, false);
		cityName = name;
		cityIdentifier = identifier;
		trainsInStation = new Array<Train>();
	}
	
	@Override
	public String toString()
	{
		String returnString;
		returnString = "Tile at position " + this.getX() + ", " + this.getY() + " is city: " + cityName + ".";
		
		return returnString;
	}
	
	public boolean isPressed(){
		/*
		if (Gdx.input.isTouched() && TimeUtils.millis() - time > 300){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			if (this.contains(touchPos.x, touchPos.y)){
				time = TimeUtils.millis();
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
		*/
		return false;
	}
	
}
