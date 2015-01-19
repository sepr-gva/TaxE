package com.seprgva.taxe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen implements Screen {
	
	TaxE game;
	int gamePhase;
	
	private Stage stage;
	private float origX, origY, maxX, maxY;
	
	Texture texture = new Texture(Gdx.files.internal("gameGraphics/greenSquare.png"));
	Tile tile;
	
	//equivalent of create
	public GameScreen(final TaxE gameInstance, int currentPhase) {
		game = gameInstance;
		gamePhase = currentPhase;
		stage = new Stage(new ScreenViewport());
		
		for(int i = 0; i < game.gameMap.ySize; i++){
			for(int j = 0; j < game.gameMap.ySize; j++){
				stage.addActor(game.gameMap.getTile(i,j));
			}
		}
	    Gdx.input.setInputProcessor(stage);
	    
	    origX = stage.getCamera().position.x;
	    origY = stage.getCamera().position.y;
	    maxX = origX + ((game.gameMap.xSize*32)-Gdx.graphics.getWidth());
	    maxY = origY + ((game.gameMap.ySize*32)-Gdx.graphics.getHeight());
	}
	
	private void handleInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			stage.getCamera().translate(5,0,0);
			if (stage.getCamera().position.x > maxX)
			{
				stage.getCamera().position.x = maxX;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			stage.getCamera().translate(-5,0,0);
			if (stage.getCamera().position.x < origX)
			{
				stage.getCamera().position.x = origX;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			stage.getCamera().translate(0,-5,0);
			if (stage.getCamera().position.y < origY)
			{
				stage.getCamera().position.y = origY;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)){
			stage.getCamera().translate(0,5,0);
			if (stage.getCamera().position.y > maxY)
			{
				stage.getCamera().position.y = maxY;
			}
		}
		System.out.println(stage.getCamera().position.x + ", " + stage.getCamera().position.y);
		stage.getCamera().update();
		if (Gdx.input.isKeyPressed(Keys.NUM_1)){
			if (game.player1.goals.size() < 3){
	        	new Goal(game.player1, game);
	        	for (Goal goal : game.player1.goals){
	        		System.out.println(goal.description);
	        	}
	        	System.out.println();
			}
        }
		else if (Gdx.input.isKeyPressed(Keys.NUM_2)){
			if (game.player2.goals.size() < 3){
	        	new Goal(game.player2, game);
	        	for (Goal goal : game.player2.goals){
	        		System.out.println(goal.description);
	        	}
	        	System.out.println();
			}
        }
	}

	@Override
	public void render(float delta) {
		handleInput();
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
        
        handleInput();
        

	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);	
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
		stage.dispose();	
		
	}
	

}
