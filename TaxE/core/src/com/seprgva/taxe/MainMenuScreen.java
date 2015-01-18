package com.seprgva.taxe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainMenuScreen implements Screen {
	
	final TaxE game;
	public OrthographicCamera camera;
	GeneralButton play, how, quit;
	
	public MainMenuScreen(final TaxE gameInstance) {
		game = gameInstance;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1000, 625);
		play = new GeneralButton(game.play, 435, 300, 129, 80, camera);
		how = new GeneralButton(game.how, 319, 200, 362, 80, camera);
		quit = new GeneralButton(game.quit, 437, 100, 126, 80, camera);
	}
	
	public void update(){
		//Should the main menu be disposed on setting a new screen? We can always make a new one on a back button press
		if (play.isPressed()){
			game.setScreen(new CustomisationScreen(game, game.player1));
			this.dispose();
		}
		if (how.isPressed()){
			game.setScreen(new HowToScreen(game));
			this.dispose();
		}
		if (quit.isPressed()){
			Gdx.app.exit();
		}
	}

	@Override
	public void render(float delta) {
		//Clear the screen and set the color
		Gdx.gl.glClearColor(0.063f,0.043f,0.459f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		//Make sure the coordinate systems match
		game.batch.setProjectionMatrix(camera.combined);
		
		//Draw
		game.batch.begin();
		//I think the menus should have a background image where all the static stuff is drawn on the background.
		game.batch.draw(game.title, 300, 400, 400, 221);
		game.batch.end();
		
		play.draw();
		how.draw();
		quit.draw();
		
		update();
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
