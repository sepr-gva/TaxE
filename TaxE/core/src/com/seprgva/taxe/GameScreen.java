package com.seprgva.taxe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {
	
	final TaxE game;
	
	private SpriteBatch tileBatch;
	
	OrthographicCamera playCamera;
	OrthographicCamera uiCamera;

	GeneralButton nextPhaseButton;
	
	//equivalent of create() method
	public GameScreen(final TaxE gameInstance, int currentPhase) {
		game = gameInstance;
		
		playCamera = new OrthographicCamera();
		playCamera.setToOrtho(false, 1000, 625);
		playCamera.update();
		
		uiCamera = new OrthographicCamera();
		uiCamera.setToOrtho(false, 1000, 625);
		uiCamera.update();
		
		nextPhaseButton = new GeneralButton(game.nextPhase, game.nextPhaseClicked, 900, 25, 48, 48, uiCamera);
		
		tileBatch = new SpriteBatch();
		
		if (currentPhase == 1) {
			createTrain(5, 4, game.player2);
			createTrain(5, 33, game.player1);
		}
	}
	
	private void createTrain(int X, int Y, Player owner)
	{
		//I feel like this is over-complicated. Do we need a trainList and trainsInStation?
		Tile tileForTrain = game.gameMap.mapArray[X][Y];
		if (tileForTrain instanceof City){
			City cityForTrain = (City) tileForTrain;
			Train newTrain = new Train(game.trainID, game.gameMap.mapArray[X][Y], owner);
			game.trainList.add(newTrain);
			cityForTrain.trainsInStation.add(newTrain);
			owner.trains.add(newTrain);
			game.gameMap.mapArray[X][Y] = cityForTrain;
			game.trainID++; //This needs to be implemented properly at some point
		}
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
		
		//Drawing map tiles
		for(int y = 0; y < game.gameMap.ySize; y++){
			for(int x = 0; x < game.gameMap.xSize; x++){
				Tile tile = game.gameMap.mapArray[x][y];
				
				if(tile instanceof Rail){
						//Tile tile = gameMap.mapArray[x][y];
						tileBatch.draw(game.ud, (tile.x)*32, (tile.y)*32, tile.width, tile.height);
				}
				else{
					
					if(tile.tileType == "player1Build"){
						tileBatch.draw(game.emptyRed, (tile.x)*32, (tile.y)*32, tile.width, tile.height);
					}
					else if(tile.tileType == "player2Build"){
						tileBatch.draw(game.emptyBlue, (tile.x)*32, (tile.y)*32, tile.width, tile.height);
					}
					else if(tile instanceof City){
						City cityTile = (City) tile;
						tileBatch.draw(game.brownSquare, (tile.x)*32, (tile.y)*32);
						game.font.draw(tileBatch, cityTile.cityIdentifier, ((tile.x)*32)+1, ((tile.y)*32)+24);
					}
				}
			}
		}
	
		//Drawing trains to the screen
		for(int trains = 0; trains < game.trainList.size(); trains++){
			//The train list is quite a convoluted data structure...but I don't think there's anything wrong with that.
			Train selectedTrain = game.trainList.get(trains);
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
		
		game.batch.end();
		
		//Buttons generate their own batch that projects onto the camera passed as an argument
		nextPhaseButton.draw();
	}
	
    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            playCamera.translate(-5, 0, 0);
            if (playCamera.position.x < 500){
            	playCamera.position.x = 500;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            playCamera.translate(5, 0, 0);
            if (playCamera.position.x > 748){
            	playCamera.position.x = 748;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            playCamera.translate(0, -5, 0);
            if (playCamera.position.y < 312.5){
            	playCamera.position.y = (float)312.5;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            playCamera.translate(0, 5, 0);
            if (playCamera.position.y > 936){
            	playCamera.position.y = 936;
            }
        }
		if (Gdx.input.isKeyPressed(Keys.T))
		{
			game.trainList.get(0).traverse(game.gameMap.mapArray[5][10]);
		}
		
		// Give players up to 3 goals as a test by pressing their corresponding number
		if (Gdx.input.isKeyPressed(Keys.NUM_1)){
			if (game.player1.goals.size() < 3){
				new Goal(game.player1, game);
				for (Goal goal : game.player1.goals){
				System.out.println(goal.description);
				}
				System.out.println("");
			}
		}
		if (Gdx.input.isKeyPressed(Keys.NUM_2)){
			if (game.player2.goals.size() < 3){
				new Goal(game.player2, game);
				for (Goal goal : game.player2.goals){
					System.out.println(goal.description);
				}
				System.out.println("");
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
