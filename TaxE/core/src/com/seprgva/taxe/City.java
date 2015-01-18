package com.seprgva.taxe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class City extends Tile
{
	String cityName;
	String cityIdentifier;
	Rail nextRail = null;
	Rail prevRail = null;
	Array<Train> trainsInStation;
	OrthographicCamera camera;
	long time = TimeUtils.millis();
	
	public City(int xCoord, int yCoord, String name, String identifier)
	{
		super(xCoord, yCoord, "City");
		cityName = name;
		cityIdentifier = identifier;
		trainsInStation = new Array<Train>();
		//this.camera = camera;
	}
	
	@Override
	public String toString()
	{
		String returnString;
		returnString = "Tile at position " + this.x + ", " + this.y + " is city: " + cityName + ".";
		
		return returnString;
	}
	
	public boolean isPressed(){
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
	}
	
}
