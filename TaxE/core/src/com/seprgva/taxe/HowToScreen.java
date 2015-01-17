package com.seprgva.taxe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.TimeUtils;

public class HowToScreen implements Screen {

	final TaxE game;
	OrthographicCamera camera;
	long time;
	
	public HowToScreen(final TaxE gam){
		game = gam;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1000, 625);
		time = TimeUtils.millis();
	}
	
	public void update(){
		if (Gdx.input.isTouched()){
			game.setScreen(new MainMenuScreen(game));
			this.dispose();
		}
	}
	
	@Override
	public void render(float delta) {
		//Clear the screen and set the color
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				
		camera.update();
		//Make sure the coordinate systems match
		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		game.font.draw(game.batch, "Implement game manual here \n touch to go back", 100, 100);
		game.batch.end();
		
		if (TimeUtils.millis() - time > 300){
			update();
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}