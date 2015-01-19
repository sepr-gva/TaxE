package com.seprgva.taxe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Tile extends Actor
{
	//Tile is concrete as it represents a blank tile
	//Tile[] neighbours = new Tile[4];	//Should be able to calculate neighbours using tile coordinates as a unique ID.
	//public String tileType;
	
	Texture texture;
	public boolean started = false;
	public boolean blank = false;
	
	public Tile(int x, int y, Texture sprite, boolean isblank)
	{
		texture = sprite;
		setBounds(x, y,texture.getWidth(),texture.getHeight());
		blank = isblank;
		
		if(blank == true){
			
			addListener(new InputListener() {
				public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
					texture = new Texture(Gdx.files.internal("gameGraphics/redSquare.png"));
				}

				public void exit (InputEvent event, float x, float y, int pointer, Actor fromActor) {
					texture = new Texture(Gdx.files.internal("gameGraphics/greenSquare.png"));
				}
			});
		}
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
