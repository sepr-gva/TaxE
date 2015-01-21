package com.seprgva.taxe;

import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Tile extends Actor
{
	//Tile is concrete as it represents a blank tile
	//Tile[] neighbours = new Tile[4];	//Should be able to calculate neighbours using tile coordinates as a unique ID.
	//public String tileType;
	
	public Texture defaultTexture, hoverTexture, currentTexture;
	public boolean started = false;
	public boolean blank = false;
	public int xCoord, yCoord;
	Tile[] neighbours;
	Map gameMap;
	Player owner;
	boolean junction = false, highlighted = false;
	TaxE game;
	
	public Tile(int x, int y, Texture sprite, Texture hoverSprite, boolean isblank, TaxE gameInstance)
	{
		game = gameInstance;
		xCoord = x;
		yCoord = y;
		defaultTexture = sprite;
		hoverTexture = hoverSprite;
		currentTexture = defaultTexture;
		//setBounds(x, y, currentTexture.getWidth(), currentTexture.getHeight());
		blank = isblank;
		neighbours = new Tile[4];
	}
	
	protected void inputHandler()
	{
		if(blank == true){
			addListener(new InputListener() {
				public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
					currentTexture = hoverTexture;
				}
				public void exit (InputEvent event, float x, float y, int pointer, Actor fromActor) {
					currentTexture = defaultTexture;
				}
				
				public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
					if(blank == true){
					
						Player player = null;
						if(game.phaseNo == 1){
							player = game.player1;
						}
						else{
							player = game.player2;
						}
					
						if(player.money>249){
					
			        	
			        		Texture texture = game.turretBlue;
			        
			        		if(game.phaseNo == 2){
			        			player = game.player2;
			        			texture = game.turretRed;
			        		}
			        
			        		Tower tower = new Tower(player, xCoord, yCoord, texture);
			        		game.gameScreen.trainStage.addActor(tower);
			        
			        		player.money = player.money-250;
			        		blank = false;
			        	}
					}
			        return true;
			        
			    }

			    public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
			        System.out.println("up");
			    }
				
			});
		}
	
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
	
	public void changeTexture(Texture newTexture){
		this.currentTexture = newTexture;
	}
	
	@Override
	public String toString()
	{
		String returnString;
		returnString = "Tile at position " + xCoord + ", " + yCoord + " is empty.";
	
		return returnString;
	}
}
