package com.seprgva.taxe;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Tile extends Actor
{
	//Tile is concrete as it represents a blank tile
	//Tile[] neighbours = new Tile[4];	//Should be able to calculate neighbours using tile coordinates as a unique ID.
	//public String tileType;
	
	Texture texture;
	public boolean started = false;
	
	public Tile(int x, int y, Texture sprite)
	{
		texture = sprite;
		setBounds(x, y,texture.getWidth(),texture.getHeight());
	}
	
	@Override
    public void draw(Batch batch, float alpha){
        batch.draw(texture, this.getX(),getY());
    }
	
	@Override
	public String toString()
	{
		String returnString;
		returnString = "Tile at position " + this.getX() + ", " + this.getY() + " is empty.";
	
		return returnString;
	}
}
