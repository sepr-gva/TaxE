package com.seprgva.taxe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.ArrayList;

public class GameScreen implements Screen {
	
	final TaxE game;
	
	OrthographicCamera camera;
	Map gameMap;
	int trainID = 0;
	ArrayList<Train> trainList = new ArrayList<Train>();
	
	private void createTrain(int X, int Y)
	{
		Train newTrain = new Train(trainID, gameMap.mapArray[X][Y]);
		trainList.add(newTrain);
		trainID++;
	}
	
	public GameScreen(final TaxE gameInstance) {
		game = gameInstance;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1000, 625);
		
		//Map is currently set to 20x20
		gameMap = new Map();
		
		//Placing two cities on the map
		//For now, this has to be done by replacing the appropriate blank tiles with city tiles
		gameMap.mapArray[5][5] = new City(5,5, "London");
		gameMap.mapArray[5][10] = new City(5,10, "York");
		for (int i = 6; i <= 9; i++)
		{
			gameMap.mapArray[5][i] = new Rail(5, i);
		}
		createTrain(5,5);
	}

	@Override
	public void render(float delta) {
		//Clear the screen and set its colour
		Gdx.gl.glClearColor(0,0,0.2f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		//Make sure the coordinate systems match
		game.batch.setProjectionMatrix(camera.combined);
		
		//Draw
		game.batch.begin();
		game.font.draw(game.batch, "Implement game here", 150, 200);
		
		//Only for testing purposes
		game.font.draw(game.batch, gameMap.toString(), 150, 180);
		game.font.draw(game.batch, gameMap.mapArray[5][5].toString(), 150, 160);
		game.font.draw(game.batch, gameMap.mapArray[5][10].toString(), 150, 140);
		game.font.draw(game.batch, trainList.get(0).toString(), 150, 120);
		
		if (Gdx.input.isKeyPressed(Keys.T))
		{
			trainList.get(0).traverse(gameMap.mapArray[5][10]);
		}
		
		game.batch.end();
		
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
