package com.seprgva.taxe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class GameScreen implements Screen {
	
	final TaxE game;
	private Texture greenSquare;
	private Texture greySquare;
	//private Rectangle tile;
	
	private SpriteBatch tileBatch;
	
	OrthographicCamera camera;
	Map gameMap;
	int trainID = 0;
	ArrayList<Train> trainList = new ArrayList<Train>();
	
	private void createTrain(int X, int Y, Player player)
	{
		Train newTrain = new Train(trainID, gameMap.mapArray[X][Y], player);
		trainList.add(newTrain);
		player.trains.add(newTrain);
		trainID++;
	}

	//equivalent of create() method
	public GameScreen(final TaxE gameInstance) {
		game = gameInstance;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1000, 625);
		camera.update();
		
		tileBatch = new SpriteBatch();
		
		//load some textures
		greenSquare = new Texture(Gdx.files.internal("gameGraphics/greenSquare.png"));
		greySquare = new Texture(Gdx.files.internal("gameGraphics/greySquare.png"));
		
		//Map is currently set to 40x40
		gameMap = new Map();
		
		//Placing two cities on the map
		//For now, this has to be done by replacing the appropriate blank tiles with city tiles
		gameMap.mapArray[5][5] = new City(5,5, "London");
		gameMap.mapArray[5][10] = new City(5,10, "York");
		for (int i = 6; i <= 9; i++)
		{
			gameMap.mapArray[5][i] = new Rail(5, i);
		}
<<<<<<< HEAD
		createTrain(5,5);
=======
		createTrain(5,4,game.player1);
		createTrain(12,4,game.player2);
		createTrain(15, 10, game.player2);
		
>>>>>>> a50d48ccac2e1add827359fcd34a8c71a2323096
	}

	@Override
	public void render(float delta) {
		//Clear the screen and set its colour
		Gdx.gl.glClearColor(0,0,0.2f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		handleInput();
		camera.update();

		tileBatch.setProjectionMatrix(camera.combined);
		tileBatch.begin();

		for(int y = 0; y < gameMap.ySize; y++){
			for(int x = 0; x <gameMap.xSize; x++){
				Tile tile = gameMap.mapArray[x][y];
				
				if(tile.tileType == "rail"){
						//Tile tile = gameMap.mapArray[x][y];
						tileBatch.draw(greySquare, (tile.x)*32, (tile.y)*32);
				}
				else{
					tileBatch.draw(greenSquare, (tile.x)*32, (tile.y)*32);
				}
			}
		}

		tileBatch.end();
				
		game.batch.setProjectionMatrix(camera.combined);
		
		//Draw
		game.batch.begin();
		game.batch.draw(game.player1.avatar, 500, 500, 100, 100);
		game.batch.draw(game.player2.avatar, 600, 500, 100, 100);
		game.font.draw(game.batch, "Player 1 name:" + game.player1.companyName + ", Player 2 naem:" + game.player2.companyName, 450, 400);
		game.font.draw(game.batch, "Implement game here", 150, 200);
		
		//Only for testing purposes
		game.font.draw(game.batch, gameMap.toString(), 150, 180);
		game.font.draw(game.batch, gameMap.mapArray[5][5].toString(), 150, 160);
		game.font.draw(game.batch, gameMap.mapArray[5][10].toString(), 150, 140);
		game.font.draw(game.batch, trainList.get(0).toString(), 150, 120);
		
		//draw player trains
		for (Train train : game.player1.trains){
			game.batch.draw(game.redSquare, train.currentLocation.x, train.currentLocation.y, train.currentLocation.width, train.currentLocation.height);
		}
		for (Train train : game.player2.trains){
			game.batch.draw(game.blueSquare, train.currentLocation.x, train.currentLocation.y, train.currentLocation.width, train.currentLocation.height);
		}
		
		if (Gdx.input.isKeyPressed(Keys.T))
		{
			trainList.get(0).traverse(gameMap.mapArray[5][15]);
		}
		
		game.batch.end();
		
	}
	
    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.translate(-3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.translate(3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.translate(0, -3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.translate(0, 3, 0);
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
