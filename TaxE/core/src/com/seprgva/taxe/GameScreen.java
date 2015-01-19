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
	
	private Stage baseStage, cityStage;
	private float origX, origY, maxX, maxY;
	
	//equivalent of create
	public GameScreen(final TaxE gameInstance, int currentPhase) {
		game = gameInstance;
		gamePhase = currentPhase;
		baseStage = new Stage(new ScreenViewport());
		cityStage = new Stage(new ScreenViewport());
		
		for(int i = 0; i < game.gameMap.ySize; i++){
			for(int j = 0; j < game.gameMap.ySize; j++){
				if (game.gameMap.getTile(i, j) instanceof City){
					cityStage.addActor(game.gameMap.getTile(i, j));
				}
				else{
					baseStage.addActor(game.gameMap.getTile(i,j));
				}
			}
		}
	    
		//Gdx.input.setInputProcessor(baseStage);
		Gdx.input.setInputProcessor(cityStage);
	    
	    origX = baseStage.getCamera().position.x;
	    origY = baseStage.getCamera().position.y;
	    maxX = origX + ((game.gameMap.xSize*32)-Gdx.graphics.getWidth());
	    maxY = origY + ((game.gameMap.ySize*32)-Gdx.graphics.getHeight());
	}
	
	private void handleInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			baseStage.getCamera().translate(5,0,0);
			if (baseStage.getCamera().position.x > maxX)
			{
				baseStage.getCamera().position.x = maxX;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			baseStage.getCamera().translate(-5,0,0);
			if (baseStage.getCamera().position.x < origX)
			{
				baseStage.getCamera().position.x = origX;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			baseStage.getCamera().translate(0,-5,0);
			if (baseStage.getCamera().position.y < origY)
			{
				baseStage.getCamera().position.y = origY;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)){
			baseStage.getCamera().translate(0,5,0);
			if (baseStage.getCamera().position.y > maxY)
			{
				baseStage.getCamera().position.y = maxY;
			}
		}
		baseStage.getCamera().update();
		if (Gdx.input.isKeyJustPressed(Keys.NUM_1)){
			if (game.player1.goals.size() < 3){
	        	new Goal(game.player1, game);
	        	for (Goal goal : game.player1.goals){
	        		System.out.println(goal.description);
	        	}
	        	System.out.println();
			}
        }
		else if (Gdx.input.isKeyJustPressed(Keys.NUM_2)){
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
        baseStage.act(delta);
        baseStage.draw();
        cityStage.act(delta);
        cityStage.draw();
        
        handleInput();

	}

	@Override
	public void resize(int width, int height) {
		baseStage.getViewport().update(width, height, true);	
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
		baseStage.dispose();	
		
	}
	

}
