package com.seprgva.taxe;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Tower extends Actor{
	Texture trainTexture;
	Player owner;
	int xCoord, yCoord;
	
	public Tower(Player player, int x, int y, Texture sprite)
	{
		trainTexture = sprite;
		xCoord = x;
		yCoord = y;
		owner = player;
		
		setBounds(xCoord, yCoord, 128, 128);
	}
	
	@Override
    public void draw(Batch batch, float alpha){
        batch.draw(trainTexture, getX(), getY());
    }
}
