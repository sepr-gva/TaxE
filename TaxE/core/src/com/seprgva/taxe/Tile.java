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
	
	Texture defaultTexture, hoverTexture;
	public boolean started = false;
	public boolean blank = false;
	
	public Tile(int x, int y, Texture sprite, Texture hoverSprite, boolean isblank)
	{
		defaultTexture = sprite;
		hoverTexture = hoverSprite; 
		setBounds(x, y,defaultTexture.getWidth(),defaultTexture.getHeight());
		blank = isblank;
		hoverHandler();
	}
	
	private void hoverHandler()
	{
		if(blank == true){
			
			addListener(new InputListener() {
				public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
					defaultTexture = new Texture(Gdx.files.internal("gameGraphics/redSquare.png"));
				}

				public void exit (InputEvent event, float x, float y, int pointer, Actor fromActor) {
					defaultTexture = new Texture(Gdx.files.internal("gameGraphics/greenSquare.png"));
				}
			});
		}
	}
	
	@Override
    public void draw(Batch batch, float alpha){
        batch.draw(defaultTexture, this.getX(),getY());
    }
	
	@Override
	public String toString()
	{
		String returnString;
		returnString = "Tile at position " + this.getX() + ", " + this.getY() + " is empty.";
	
		return returnString;
	}
}
