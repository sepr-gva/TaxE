package com.seprgva.taxe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

public class MenuButton extends Rectangle {
	
	Texture texture;
	TaxE game;
	OrthographicCamera camera;
	long time = TimeUtils.millis();
	
	public MenuButton(Texture texture, int x, int y, int width, int height, TaxE game, OrthographicCamera camera){
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.game = game;
		this.camera = camera;
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
	
	public float getWidth(){
		return this.width;
	}
	
	public float getHeight(){
		return this.height;
	}
	
	public void draw(){
		game.batch.begin();
		game.batch.draw(texture, x, y, width, height);
		game.batch.end();
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

}
