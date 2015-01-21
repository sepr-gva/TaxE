package com.seprgva.taxe;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Rail extends Tile
{
	
	boolean inCity;
	
	public Rail(int x, int y, Texture railTexture, boolean inCityTest, Map map)
	{
		super(x, y, railTexture, railTexture, false, null, map);
		inCity = inCityTest;
	}
	
	@Override
	public String toString()
	{
		String returnString = "";
		returnString = "Tile at position " + xCoord + ", " + yCoord + " is a rail";
		
		return returnString;
	}
	
	public void clickHandler(){
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
}
