package com.seprgva.taxe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class CustomisationScreen implements Screen {
	
	final TaxE game;
	OrthographicCamera camera;
	MenuButton male, female, black, done, name;
	boolean b = false, m = false, f = false;
	String companyName = "";
	Player player;
	
	//Things will need to be moved and rescaled to make it look better
	//Also might add pop up if people forget to choose avatar/name
	
	public void update(){
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
						this.dispose();
					}
					else{
						game.setScreen(new GameScreen(game, 1));
						this.dispose();
					}
				}
			}
			else if (f == true){
				player.avatar = game.femaleAvatar;
				if (companyName != ""){
					player.companyName = companyName;
					if (player.playerNumber == 1){
						game.setScreen(new CustomisationScreen(game, game.player2));
						this.dispose();
					}
					else{
						game.setScreen(new GameScreen(game, 1));
						this.dispose();
					}
				}
			}
			else if (b == true){
				player.avatar = game.blackAvatar;
				if (companyName != ""){
					player.companyName = companyName;
					if (player.playerNumber == 1){
						game.setScreen(new CustomisationScreen(game, game.player2));
						this.dispose();
					}
					else{
						game.setScreen(new GameScreen(game, 1));
						this.dispose();
					}
				}
			}
		}
		
		if (Gdx.input.isKeyPressed(Keys.ENTER)){
			game.player1.companyName = "Bob's trains";
			game.player2.companyName = "Sally's trains";
			game.player1.avatar = game.blackAvatar;
			game.player2.avatar = game.femaleAvatar;
			game.setScreen(new GameScreen(game, 1));
			this.dispose();
		}
	}
	
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
		
		//Draw
		game.batch.begin();
		game.font.draw(game.batch, "Press Enter to skip customisation", 100, 100);
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
