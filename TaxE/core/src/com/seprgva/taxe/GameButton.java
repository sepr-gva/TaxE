package com.seprgva.taxe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

public class GameButton extends GeneralButton {
	
	Texture clickedTexture;
	
	GameButton(Texture defaultTexture, Texture clickedTexture, int x, int y, OrthographicCamera camera){
		super(defaultTexture, x, y, 16, 16, camera);
		this.clickedTexture = clickedTexture;
	}
	
	@Override
	public void draw(){
		buttonBatch.begin();
		if (isPressed()){
			buttonBatch.draw(this.clickedTexture, this.x, this.y, this.width, this.height);
		}
		else{
			buttonBatch.draw(this.texture, this.x, this.y, this.width, this.height);
		}
		buttonBatch.end();
		
	}

}
