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
	OrthographicCamera camera;
	
	long time = TimeUtils.millis();
	static Texture texture = new Texture(Gdx.files.internal("gameGraphics/cityTile.png"));
	
	public City(int x, int y, String name, String identifier)
	{
		super(x, y, texture);
		
		cityName = name;
		cityIdentifier = identifier;
		trainsInStation = new Array<Train>();
		//this.camera = camera;
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
