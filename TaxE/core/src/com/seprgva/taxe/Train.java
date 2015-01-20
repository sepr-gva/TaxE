package com.seprgva.taxe;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Train extends Actor
{
	int identifier;
	Tile currentLocation;
	Texture trainTexture;
	Player owner;
	int passengers = 0;
	int xCoord, yCoord;
	
	public Train(int ID, Tile startingLocation, Player player, int x, int y, Texture sprite)
	{
		trainTexture = sprite;
		xCoord = x;
		yCoord = y;
		identifier = ID;
		currentLocation = startingLocation;
		owner = player;
	}
	
	public void traverse(Map map, Tile destination)
	{
		if (destination instanceof City){
			currentLocation = destination;
		}
	}
	
	@Override
    public void draw(Batch batch, float alpha){
		setBounds(xCoord, yCoord, trainTexture.getWidth(), trainTexture.getHeight());
        batch.draw(trainTexture, getX(), getY());
    }
	
	@Override
	public String toString()
	{
		String returnString = "Train with ID " + identifier + " is at location " + currentLocation.getX() + ", " + currentLocation.getY();
		return returnString;
	}
}
