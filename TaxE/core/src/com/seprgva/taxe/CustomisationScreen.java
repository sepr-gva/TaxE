package com.seprgva.taxe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class CustomisationScreen implements Screen {
	
	final TaxE game;
	OrthographicCamera camera;
	MenuButton male, female, black, done, name;
	boolean b = false, m = false, f = false;
	String companyName = "";
	Player player;
	
	//Should pass player as parameter as well, so we can have separate customisation
	//screen for each player (once player class implemented)
	public CustomisationScreen(final TaxE gameInstance, Player player) {
		this.player = player;
		game = gameInstance;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1000, 625);
		
		male = new MenuButton(game.maleAvatar, 250, 300, 100, 100, game, camera);
		female = new MenuButton(game.femaleAvatar, 450, 300, 100, 100, game, camera);
		black = new MenuButton(game.blackAvatar, 650, 300, 100, 100, game, camera);
		name = new MenuButton(game.chooseName, 200, 150, 600, 130, game, camera);
		done = new MenuButton(game.done, 425, 50, 150, 80, game, camera);
		
		
	}

	@Override
	public void render(float delta) {
		//Clear the screen and set the colour
		Gdx.gl.glClearColor(0,0,0.2f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		//Make sure the coordinate systems match
		game.batch.setProjectionMatrix(camera.combined);
		
		male.draw();
		female.draw();
		black.draw();
		name.draw();
		done.draw();
		
		//Draw (Aspect ratios will need fixing)
		game.batch.begin();
		if (player.playerNumber == 1){
			game.batch.draw(game.player1Custom, 200, 500, 600, 80);
		}
		else if (player.playerNumber == 2){
			game.batch.draw(game.player2Custom, 200, 500, 600, 80);
		}
		game.batch.draw(game.chooseAv, 300, 420, 400, 80);
		if (m == true){
			game.batch.draw(game.selected, male.x, male.y, male.width, male.height);
		}
		else if (f == true){
			game.batch.draw(game.selected, female.x, female.y, female.width, female.height);
		}
		else if (b == true){
			game.batch.draw(game.selected, black.x, black.y, black.width, black.height);
		}
		game.batch.end();
		
		if (male.isPressed()){
			m = true;
			f = false;
			b = false;
		}
		if (female.isPressed()){
			m = false;
			f = true;
			b = false;
		}
		if (black.isPressed()){
			m = false;
			f = false;
			b = true;
		}
		if (name.isPressed()){
			Gdx.input.getTextInput(new TextInputListener() {

				@Override
				public void input(String text) {
					companyName = text;
					
				}

				@Override
				public void canceled() {
					
				}
				
			}, "What would you like your Company Name to be?", "<Insert Name Here>");
		}
		
		if (done.isPressed()){
			if (m == true){
				player.avatar = game.maleAvatar;
				if (companyName != ""){
					player.companyName = companyName;
					if (player.playerNumber == 1){
						game.setScreen(new CustomisationScreen(game, game.player2));
					}
					else{
						game.setScreen(new GameScreen(game));
					}
				}
			}
			else if (f == true){
				player.avatar = game.femaleAvatar;
				if (companyName != ""){
					player.companyName = companyName;
					if (player.playerNumber == 1){
						game.setScreen(new CustomisationScreen(game, game.player2));
					}
					else{
						game.setScreen(new GameScreen(game));
					}
				}
			}
			else if (b == true){
				player.avatar = game.blackAvatar;
				if (companyName != ""){
					player.companyName = companyName;
					if (player.playerNumber == 1){
						game.setScreen(new CustomisationScreen(game, game.player2));
					}
					else{
						game.setScreen(new GameScreen(game));
					}
				}
			}
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
