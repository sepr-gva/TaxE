package com.seprgva.taxe;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Rail extends Tile
{
	
	boolean inCity;
	
	public Rail(int x, int y, Texture railTexture, boolean inCityTest)
	{
		super(x, y, railTexture, railTexture, false);
		inCity = inCityTest;
	}
	
	@Override
	public String toString()
	{
		String returnString = "";
		returnString = "Tile at position " + xCoord + ", " + yCoord + " is a rail";
		
		return returnString;
	}
}
