package com.seprgva.taxe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Rail extends Tile
{
	Rail prevRail = null;
	Rail nextRail = null;
	
	public Rail(int x, int y, Texture railTexture)
	{
		super(x, y, railTexture, railTexture, false);
	}
	
	@Override
	public String toString()
	{
		String returnString = "";
		returnString = "Tile at position " + this.getX() + ", " + this.getY() + " is a rail";
		
		return returnString;
	}
	
	public Rail getPrevRail()
	{
		return prevRail;
	}
	
	public Rail getNextRail()
	{
		return nextRail;
	}
}
