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
	
	Texture defaultTexture, hoverTexture, currentTexture;
	public boolean started = false;
	public boolean blank = false;
	int xCoord, yCoord;
	Tile[] neighbours;
	Map gameMap;
	Player owner;
	
	public Tile(int x, int y, Texture sprite, Texture hoverSprite, boolean isblank, Player player, Map map)
	{
		xCoord = x;
		yCoord = y;
		gameMap = map;
		owner = player;
		defaultTexture = sprite;
		hoverTexture = hoverSprite;
		currentTexture = defaultTexture;
		//setBounds(x, y, currentTexture.getWidth(), currentTexture.getHeight());
		blank = isblank;
		neighbours = new Tile[4];
	}
	
	protected void inputHandler()
	{
		addListener(new InputListener() {
			public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
				currentTexture = hoverTexture;
			}
			public void exit (InputEvent event, float x, float y, int pointer, Actor fromActor) {
				currentTexture = defaultTexture;
			}
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("down");
				currentTexture = gameMap.player1Tower;
				return true;
			}
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("up");
		    }
		});
	}
	
	public float[] getCoords(){
		float[] tileCoords = {xCoord,yCoord};
		return tileCoords;
	}
	
	@Override
    public void draw(Batch batch, float alpha){
		inputHandler();
		setBounds(xCoord, yCoord, currentTexture.getWidth(), currentTexture.getHeight());
        batch.draw(currentTexture, getX(), getY());
    }
	
	@Override
	public String toString()
	{
		String returnString;
		returnString = "Tile at position " + xCoord + ", " + yCoord + " is empty.";
	
		return returnString;
	}
}
