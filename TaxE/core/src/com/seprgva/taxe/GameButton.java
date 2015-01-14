package com.seprgva.taxe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

public class GameButton extends Rectangle{
	
	Texture defaultTexture, clickedTexture;
	TaxE game;
	OrthographicCamera camera;
	long time = TimeUtils.millis();
	
	GameButton(Texture defaultTexture, Texture clickedTexture, int x, int y, TaxE game, OrthographicCamera camera){
		this.camera = camera;
		this.defaultTexture = defaultTexture;
		this.clickedTexture = clickedTexture;
		this.x = x;
		this.y = y;
		this.width = 16;
		this.height = 16;
		this.game = game;
	}
	
	public void changeX(int x){
		this.x = x;
	}
	
	public void changeY(int y){
		this.y = y;
	}
	
	public float getX(){
		return this.x;
	}
	
	public float getY(){
		return this.y;
	}
	
	public boolean isPressed(){
		if (Gdx.input.isTouched() && TimeUtils.millis() - time > 300){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			if (this.contains(touchPos.x, touchPos.y)){
				time = TimeUtils.millis();
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
		
	}
	
	public void draw(){
		//game.batch.begin();
		if (isPressed()){
			game.batch.draw(this.clickedTexture, this.x, this.y, this.width, this.height);
		}
		else{
			game.batch.draw(this.defaultTexture, this.x, this.y, this.width, this.height);
		}
		//game.batch.end();
		
	}

}
