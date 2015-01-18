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
	//private Texture greenSquare, greySquare, 
	private Texture player1Build, player2Build;
	
	//private Rectangle tile;
	
	private SpriteBatch tileBatch;
	
	OrthographicCamera playCamera;
	OrthographicCamera uiCamera;
	Map gameMap;
	int trainID = 0;
	ArrayList<Train> trainList = new ArrayList<Train>();
	
	private void createTrain(int X, int Y, Player owner)
	{
		//I feel like this is over-complicated. Do we need a trainList and trainsInStation?
		Tile tileForTrain = gameMap.mapArray[X][Y];
		if (tileForTrain instanceof City){
			City cityForTrain = (City) tileForTrain;
			Train newTrain = new Train(trainID, gameMap.mapArray[X][Y], owner);
			trainList.add(newTrain);
			cityForTrain.trainsInStation.add(newTrain);
			owner.trains.add(newTrain);
			gameMap.mapArray[X][Y] = cityForTrain;
			trainID++; //This needs to be implemented properly at some point
		}
	}

	//equivalent of create() method
	public GameScreen(final TaxE gameInstance) {
		game = gameInstance;
		
		playCamera = new OrthographicCamera();
		playCamera.setToOrtho(false, 1000, 625);
		playCamera.update();
		
		uiCamera = new OrthographicCamera();
		uiCamera.setToOrtho(false, 1000, 625);
		uiCamera.update();
		
		tileBatch = new SpriteBatch();
		
		//load some textures - should we do this here or when opening the game?
		
		//Jordan: We should probably do it here but it clutters space, doing it in
		//TaxE is fine
		
		//greenSquare = new Texture(Gdx.files.internal("gameGraphics/greenSquare.png"));
		//greySquare = new Texture(Gdx.files.internal("gameGraphics/greySquare.png"));
		player1Build = new Texture(Gdx.files.internal("gameGraphics/player1build.png"));
		player2Build = new Texture(Gdx.files.internal("gameGraphics/player2build.png"));
		
		//Map is currently set to 40x40
		gameMap = new Map();
		
		//Placing two cities on the map
		//For now, this has to be done by replacing the appropriate blank tiles with city tiles
		gameMap.mapArray[5][5] = new City(5,5, "London", "LON");
		gameMap.mapArray[5][10] = new City(5,10, "York", "YRK");
		for (int i = 6; i <= 9; i++)
		{
			gameMap.mapArray[5][i] = new Rail(5, i);
		}

		createTrain(5, 5, game.player2);
	}

	@Override
	public void render(float delta) {
		//Clear the screen and set its colour
		Gdx.gl.glClearColor(0,0,0.2f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		handleInput();
		playCamera.update();
		
		//Sprite batch for map projected onto playCamera
		tileBatch.setProjectionMatrix(playCamera.combined);
		tileBatch.begin();

		for(int y = 0; y < gameMap.ySize; y++){
			for(int x = 0; x < gameMap.xSize; x++){
				Tile tile = gameMap.mapArray[x][y];
				
				if(tile instanceof Rail){
						//Tile tile = gameMap.mapArray[x][y];
						tileBatch.draw(game.greySquare, (tile.x)*32, (tile.y)*32);
				}
				else{
					
					if(tile.tileType == "player1Build"){
						tileBatch.draw(player1Build, (tile.x)*32, (tile.y)*32);
					}
					else if(tile.tileType == "player2Build"){
						tileBatch.draw(player2Build, (tile.x)*32, (tile.y)*32);
					}
					else if(tile instanceof City){
						City cityTile = (City) tile;
						tileBatch.draw(game.brownSquare, (tile.x)*32, (tile.y)*32);
						game.font.draw(tileBatch, cityTile.cityIdentifier, ((tile.x)*32)+1, ((tile.y)*32)+24);
					}
				}
			}
		}
		
		for(int trains = 0; trains < trainList.size(); trains++){
			//The train list is quite a convoluted data structure...but I don't think there's anything wrong with that.
			Train selectedTrain = trainList.get(trains);
			if (selectedTrain.owner.playerNumber == 1){
				tileBatch.draw(game.tempTrain1, (selectedTrain.currentLocation.x)*32,(selectedTrain.currentLocation.y)*32);
			}
			else {
				tileBatch.draw(game.tempTrain2, (selectedTrain.currentLocation.x)*32,(selectedTrain.currentLocation.y)*32);
			}
			
		}
		
		tileBatch.end();
		
		//Sprite batch for everything else projected onto uiCamera
		game.batch.setProjectionMatrix(uiCamera.combined);
		
		game.batch.begin();
		game.batch.draw(game.player1.avatar, 900, 575, 50, 50);
		game.batch.draw(game.player2.avatar, 950, 575, 50, 50);
		
		//Implementing this properly will require a maximum length for company name
		game.font.draw(game.batch, "Player 1 name: " + game.player1.companyName, 700, 615);
		game.font.draw(game.batch, "Player 2 name: " + game.player2.companyName, 700, 595);

		
		//Only for testing purposes
		game.font.draw(game.batch, trainList.get(0).toString(), 150, 120);
		
		if (Gdx.input.isKeyPressed(Keys.T))
		{
			trainList.get(0).traverse(gameMap.mapArray[5][10]);
		}
		
		game.batch.end();	
	}
	
    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            playCamera.translate(-5, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            playCamera.translate(5, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            playCamera.translate(0, -5, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            playCamera.translate(0, 5, 0);
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
