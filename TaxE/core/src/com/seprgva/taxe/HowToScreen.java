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
		game.batch.draw(game.menuBackground, 0, 0, 1005, 630);
		game.batch.draw(game.controls, 150, 100, 700, 500);
		game.font.draw(game.batch, "Read full user manual (on the github) for more detail, touch to go back!", 500 - (game.font.getBounds("Read full user manual (on the github) for more detail, touch to go back!").width)/2, 50);
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
