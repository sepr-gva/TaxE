package com.seprgva.taxe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

public class MainMenuScreen implements Screen {
	
	final TaxE game;
	public OrthographicCamera camera;
	Texture menu;
	GameButton but;
	
	public MainMenuScreen(final TaxE gam) {
		game = gam;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1000, 625);
		
		menu = new Texture(Gdx.files.internal("MainMenuScreen.png"));
		menu.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		but = new GameButton(menu, 100, 100, game, camera);
		
		
	}

	@Override
	public void render(float delta) {
		//Clear the screen and set the color
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		//Make sure the coordinate systems match
		game.batch.setProjectionMatrix(camera.combined);
		
		//Draw
		game.batch.begin();
		game.batch.draw(menu, 0, 0, 1000, 625);
		game.batch.end();
		
		but.draw();
		
//		if (Gdx.input.isTouched()) {
//			game.setScreen(new CustomisationScreen(game));
//			dispose();
//		}
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
