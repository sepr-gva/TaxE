package com.seprgva.taxe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Rail extends Tile
{
	Rail prevRail = null;
	Rail nextRail = null;
	
	static Texture texture = new Texture(Gdx.files.internal("gameGraphics/railUD.png"));
	
	public Rail(int x, int y)
	{
		super(x, y, texture, false);
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
