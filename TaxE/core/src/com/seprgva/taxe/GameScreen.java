package com.seprgva.taxe;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen implements Screen {
	
	TaxE game;
	
	public Stage baseStage, cityStage, trainStage;	//define the three different stages that actors will be drawn to
	private float origX, origY, maxX, maxY;			//variables used to bind the camera to the game region
	OrthographicCamera uiCamera;
	GeneralButton nextPhaseButton;					//used to transition between game phases and turns
	boolean routeDone = false;
	
	//equivalent of create
	public GameScreen(final TaxE gameInstance, int currentPhase, int turnNumber) {
		game = gameInstance;								//allow the gameScreen

		game.phaseNo = currentPhase;
		game.turnNo = turnNumber;
		baseStage = new Stage(new ScreenViewport());		//set up the viewports for each Stage
		cityStage = new Stage(new ScreenViewport());		//set up the viewports for each Stage
		trainStage = new Stage(new ScreenViewport());		//set up the viewports for each Stage
		uiCamera = new OrthographicCamera();
		uiCamera.setToOrtho(false, 1000, 625);
		uiCamera.update();
		
	    origX = baseStage.getCamera().position.x;								//find appropriate bounds for the camera
	    origY = baseStage.getCamera().position.y;
	    maxX = origX + ((game.gameMap.xSize*32)-Gdx.graphics.getWidth());
	    maxY = origY + ((game.gameMap.ySize*32)-Gdx.graphics.getHeight());
	    
	    nextPhaseButton = new GeneralButton(game.nextPhase, 942, 10, 48, 48, uiCamera);
	    
	    System.out.println("Turn no: " + game.turnNo + ". Phase: " + game.phaseNo);
		
		for(int i = 0; i < game.gameMap.ySize; i++){
			for(int j = 0; j < game.gameMap.ySize; j++){
				if (game.gameMap.getTile(i, j) instanceof City){
					cityStage.addActor(game.gameMap.getTile(i, j));		//add the cities to their own stage as they need a
				}														//separate listener to the blank tiles
				else{
					baseStage.addActor(game.gameMap.getTile(i,j));		//add all of the other tiles to the base layer
				}
			}
		}
	    
		if ((game.phaseNo == 1) || (game.phaseNo == 2)){
			//Set-up phase
			if (game.turnNo == 1){
				Gdx.input.setInputProcessor(cityStage);		//phases one and two are a special case as the player needs to
			}												//decide the city that they start on
			else{
				Gdx.input.setInputProcessor(baseStage);		//otherwise we want to listen to input on the base layer
			}												//ie. building of towers
		}
		else if ((game.phaseNo == 3) || (game.phaseNo == 4)){
			//Deployment phase
			Gdx.input.setInputProcessor(cityStage);
			Player player;
			if (game.phaseNo == 3){
				player = game.player1;
			}
			else{
				player = game.player2;
			}
			for (Train train : player.trains){
				train.possibleRoutes = isReachable(train.currentLocation);	//find the possible routes that a train can
			}																//take to its neighbouring cities
		}
	    
	    for (Train train: game.trainList){
	    	trainStage.addActor(train);				//add each train to the train Stage
	    	
	    }
	}
	
	//Function to highlight tiles which can be selected using select(tile). Highlight variant of tile textures has blue highlight.
	public void highlight(Tile tile){
		if (tile.currentTexture == game.city){
			tile.changeTexture(game.cityblue);
			tile.highlighted = true;
		}
		else if (tile.currentTexture == game.dlr){
			tile.changeTexture(game.dlrblue);
			tile.highlighted = true;
		}
		else if (tile.currentTexture == game.junction){
			tile.changeTexture(game.junctionblue);
			tile.highlighted = true;
		}
		else if (tile.currentTexture == game.udl){
			tile.changeTexture(game.udlblue);
			tile.highlighted = true;
		}
		else if (tile.currentTexture == game.udr){
			tile.changeTexture(game.udrblue);
			tile.highlighted = true;
		}
		else if (tile.currentTexture == game.ulr){
			tile.changeTexture(game.ulrblue);
			tile.highlighted = true;
		}
	}
	
	//Function to set city and junction tiles to their selected tile variants textures (red highlight).
	public void select(Tile tile){
		if (tile.currentTexture == game.city){
			tile.currentTexture = game.cityred;
			tile.highlighted = false;
		}
		else if (tile.currentTexture == game.dlr){
			tile.currentTexture = game.dlrred;
			tile.highlighted = false;
		}
		else if (tile.currentTexture == game.junction){
			tile.currentTexture = game.junctionred;
			tile.highlighted = false;
		}
		else if (tile.currentTexture == game.udl){
			tile.currentTexture = game.udlred;
			tile.highlighted = false;
		}
		else if (tile.currentTexture == game.udr){
			tile.currentTexture = game.udrred;
			tile.highlighted = false;
		}
		else if (tile.currentTexture == game.ulr){
			tile.currentTexture = game.ulrred;
			tile.highlighted = false;
		}
	}
	
	public ArrayList<ArrayList<Tile>> isReachable(Tile city){
		ArrayList<ArrayList<Tile>> possibleRoutes = new ArrayList<ArrayList<Tile>>();
		ArrayList<Tile> route1 = new ArrayList<Tile>();
		ArrayList<Tile> route2 = new ArrayList<Tile>();
		ArrayList<Tile> route3 = new ArrayList<Tile>();
		ArrayList<Tile> route4 = new ArrayList<Tile>();
		route1.add(city);
		route2.add(city);
		route3.add(city);
		route4.add(city);
		Tile currentTile, prevTile, nextTile;
		if (city.neighbours[0] instanceof Rail){
			currentTile = city.neighbours[0];
			prevTile = city;
			nextTile = null;
			while (currentTile instanceof Rail){
				route1.add(currentTile);
				if (currentTile.junction == false){
					for (Tile tile : currentTile.neighbours){
						if (tile instanceof Rail && tile != prevTile || tile instanceof City && tile != prevTile){
							nextTile = tile;
						}
					}
				}
				else{
					if (prevTile == currentTile.neighbours[0]){
						nextTile = currentTile.neighbours[2];
					}
					else if (prevTile == currentTile.neighbours[1]){
						nextTile = currentTile.neighbours[3];
					}
					else if (prevTile == currentTile.neighbours[2]){
						nextTile = currentTile.neighbours[0];
					}
					else if (prevTile == currentTile.neighbours[3]){
						nextTile = currentTile.neighbours[1];
					}
				}
				prevTile = currentTile;
				currentTile = nextTile;
			}
			route1.add(currentTile);
			highlight(currentTile);
		}
		if (city.neighbours[1] instanceof Rail){
			currentTile = city.neighbours[1];
			prevTile = city;
			nextTile = null;
			while (currentTile instanceof Rail){
				route2.add(currentTile);
				if (currentTile.junction == false){
					for (Tile tile : currentTile.neighbours){
						if (tile instanceof Rail && tile != prevTile || tile instanceof City && tile != prevTile){
							nextTile = tile;
						}
					}
				}
				else{
					if (prevTile == currentTile.neighbours[0]){
						nextTile = currentTile.neighbours[2];
					}
					else if (prevTile == currentTile.neighbours[1]){
						nextTile = currentTile.neighbours[3];
					}
					else if (prevTile == currentTile.neighbours[2]){
						nextTile = currentTile.neighbours[0];
					}
					else if (prevTile == currentTile.neighbours[3]){
						nextTile = currentTile.neighbours[1];
					}
				}
				prevTile = currentTile;
				currentTile = nextTile;
			}
			route2.add(currentTile);
			highlight(currentTile);
		}
		if (city.neighbours[2] instanceof Rail){
			currentTile = city.neighbours[2];
			prevTile = city;
			nextTile = null;
			while (currentTile instanceof Rail){
				route3.add(currentTile);
				if (currentTile.junction == false){
					for (Tile tile : currentTile.neighbours){
						if (tile instanceof Rail && tile != prevTile || tile instanceof City && tile != prevTile){
							nextTile = tile;
						}
					}
				}
				else{
					if (prevTile == currentTile.neighbours[0]){
						nextTile = currentTile.neighbours[2];
					}
					else if (prevTile == currentTile.neighbours[1]){
						nextTile = currentTile.neighbours[3];
					}
					else if (prevTile == currentTile.neighbours[2]){
						nextTile = currentTile.neighbours[0];
					}
					else if (prevTile == currentTile.neighbours[3]){
						nextTile = currentTile.neighbours[1];
					}
				}
				prevTile = currentTile;
				currentTile = nextTile;
			}
			route3.add(currentTile);
			highlight(currentTile);
		}
		if (city.neighbours[3] instanceof Rail){
			currentTile = city.neighbours[3];
			prevTile = city;
			nextTile = null;
			while (currentTile instanceof Rail){
				route4.add(currentTile);
				if (currentTile.junction == false){
					for (Tile tile : currentTile.neighbours){
						if (tile instanceof Rail && tile != prevTile || tile instanceof City && tile != prevTile){
							nextTile = tile;
						}
					}
				}
				else{
					if (prevTile == currentTile.neighbours[0]){
						nextTile = currentTile.neighbours[2];
					}
					else if (prevTile == currentTile.neighbours[1]){
						nextTile = currentTile.neighbours[3];
					}
					else if (prevTile == currentTile.neighbours[2]){
						nextTile = currentTile.neighbours[0];
					}
					else if (prevTile == currentTile.neighbours[3]){
						nextTile = currentTile.neighbours[1];
					}
				}
				prevTile = currentTile;
				currentTile = nextTile;
			}
			route4.add(currentTile);
			highlight(currentTile);
		}
		possibleRoutes.add(route1);
		possibleRoutes.add(route2);
		possibleRoutes.add(route3);
		possibleRoutes.add(route4);
		return possibleRoutes;
	}
	
	private void handleInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			baseStage.getCamera().translate(5,0,0);
			cityStage.getCamera().translate(5,0,0);
			trainStage.getCamera().translate(5,0,0);
			if (baseStage.getCamera().position.x > maxX)
			{
				cityStage.getCamera().position.x = maxX;
				baseStage.getCamera().position.x = maxX;
				trainStage.getCamera().position.x = maxX;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			baseStage.getCamera().translate(-5,0,0);
			cityStage.getCamera().translate(-5,0,0);
			trainStage.getCamera().translate(-5,0,0);
			if (baseStage.getCamera().position.x < origX)
			{
				cityStage.getCamera().position.x = origX;
				baseStage.getCamera().position.x = origX;
				trainStage.getCamera().position.x = origX;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			baseStage.getCamera().translate(0,-5,0);
			cityStage.getCamera().translate(0,-5,0);
			trainStage.getCamera().translate(0,-5,0);
			if (baseStage.getCamera().position.y < origY)
			{
				cityStage.getCamera().position.y = origY;
				baseStage.getCamera().position.y = origY;
				trainStage.getCamera().position.y = origY;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)){
			cityStage.getCamera().translate(0,5,0);
			baseStage.getCamera().translate(0,5,0);
			trainStage.getCamera().translate(0,5,0);
			if (baseStage.getCamera().position.y > maxY)
			{
				cityStage.getCamera().position.y = maxY;
				baseStage.getCamera().position.y = maxY;
				trainStage.getCamera().position.y = maxY;
			}
		}
		baseStage.getCamera().update();
		cityStage.getCamera().update();
		trainStage.getCamera().update();
		
		//Test goals
		if (Gdx.input.isKeyJustPressed(Keys.NUM_1)){
			if (game.player1.goals.size() < 3){
	        	new Goal(game.player1, game);
	        	for (Goal goal : game.player1.goals){
	        		System.out.println(goal.description);
	        	}
	        	System.out.println();
			}
        }
		if (Gdx.input.isKeyJustPressed(Keys.NUM_2)){
        }
		
		//Test phase progression
		if (nextPhaseButton.isPressed()){
			if (game.phaseNo == 2){
				if (game.player1.goals.size() < 3){
					new Goal(game.player1, game);
				}
				if (game.player2.goals.size() < 3){
					new Goal(game.player2, game);
				}
			}
			if (game.phaseNo < 5){
				for (City city : game.cityList){
					city.highlighted = false;
					city.currentTexture = city.defaultTexture;
				}
				game.gameScreen = new GameScreen(game, game.phaseNo+1, game.turnNo);
				game.setScreen(game.gameScreen);
			}
			else{
				for (Goal goal : game.player1.goals){
					goal.addTrain();
					goal.isComplete();
				}
				if (game.player1.goalsCompleted >= 5){
					game.setScreen(new GameOverScreen(game.player1, game));
				}
				for (int i = 0; i < game.player2.goals.size() - 1; i++){
					Goal goal = game.player2.goals.get(i);
					goal.addTrain();
					goal.isComplete();
				}
				if (game.player1.goalsCompleted >= 5){
					game.setScreen(new GameOverScreen(game.player2, game));
				}
				game.gameScreen = new GameScreen(game, 1, game.turnNo+1);
				game.setScreen(game.gameScreen);
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
        if(game.phaseNo == 5){
        	trainStage.act(delta);
        }
        trainStage.draw();
        
        game.batch.setProjectionMatrix(uiCamera.combined);
		
		game.batch.begin();
		
		//Draw player1's avatar top left and player2's top right
		game.batch.draw(game.player1.avatar, 900, 525, 100, 100);
		game.batch.draw(game.player2.avatar, 0, 525, 100, 100);
		
		String string = "Phase number: " + game.phaseNo;
		game.font.draw(game.batch, string, 500 - ((game.font.getBounds(string).width)/2), 600);
		
		if (game.turnNo == 1){
			if (game.phaseNo == 1){
				string = "Player 1, choose a starting city for your train!";
				game.font.draw(game.batch, string, 500 - (game.font.getBounds(string).width)/2, 300);
			}
			else if (game.phaseNo == 2){
				string = "Player 2, choose a starting city for your train!";
				game.font.draw(game.batch, string, 500 - (game.font.getBounds(string).width)/2, 300);
			}
		}
		
		//Draw player1's company name, gold and passengers delivered
		game.font.draw(game.batch, game.player1.companyName, 10, 515);
		game.font.draw(game.batch, "Gold: " + game.player1.money, 10, 500);
		game.font.draw(game.batch, "Passengers delivered: " + game.player1.safePass, 10, 485);
		game.font.draw(game.batch, "Goals:", 10, 470);
		int textY = 455;
		for (Goal goal : game.player1.goals){
			game.font.draw(game.batch, goal.description, 10, textY);
			textY -= 15;
		}
		
		//Draw player2's company name, gold and passengers delivered
		//uses font.getBounds() to align from the right
		game.font.draw(game.batch, game.player2.companyName, 990 - ((game.font.getBounds(game.player2.companyName).width)), 515);
		string = "Gold: " + game.player1.money;
		game.font.draw(game.batch, string, 990 - (game.font.getBounds(string).width), 500);
		string = "Passengers delivered: " + game.player2.safePass;
		game.font.draw(game.batch, string, 990 - (game.font.getBounds(string).width), 485);
		game.font.draw(game.batch, "Goals:", 990 - (game.font.getBounds("Goals:        ").width), 470);
		textY = 455;
		for(Goal goal : game.player2.goals){
			game.font.draw(game.batch, goal.description, 990 - (game.font.getBounds(goal.description).width), textY);
			textY -= 15;
		}
		
		game.batch.end();
		
		nextPhaseButton.draw();
        
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
