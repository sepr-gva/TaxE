package com.seprgva.taxe;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen implements Screen {
	
	TaxE game;
	
	private Stage baseStage, cityStage, trainStage;
	private float origX, origY, maxX, maxY;
	OrthographicCamera uiCamera;
	GeneralButton nextPhaseButton;
	boolean routeDone = false;
	
	//equivalent of create
	public GameScreen(final TaxE gameInstance, int currentPhase, int turnNumber) {
		game = gameInstance;
		game.phaseNo = currentPhase;
		game.turnNo = turnNumber;
		baseStage = new Stage(new ScreenViewport());
		cityStage = new Stage(new ScreenViewport());
		trainStage = new Stage(new ScreenViewport());
		uiCamera = new OrthographicCamera();
		uiCamera.setToOrtho(false, 1000, 625);
		uiCamera.update();
		
	    origX = baseStage.getCamera().position.x;
	    origY = baseStage.getCamera().position.y;
	    maxX = origX + ((game.gameMap.xSize*32)-Gdx.graphics.getWidth());
	    maxY = origY + ((game.gameMap.ySize*32)-Gdx.graphics.getHeight());
	    
	    nextPhaseButton = new GeneralButton(game.nextPhase, 942, 10, 48, 48, uiCamera);
	    
	    System.out.println("Turn no: " + game.turnNo + ". Phase: " + game.phaseNo);
		
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
	    
		if ((game.phaseNo == 1) || (game.phaseNo == 2)){
			//Set-up phase
			if (game.turnNo == 1){
				Gdx.input.setInputProcessor(cityStage);
			}
			else{
				Gdx.input.setInputProcessor(baseStage);
			}
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
				train.possibleRoutes = isReachable(train.currentLocation);
			}
		}
		else{
			//Implement train movement here
		}
			
		if ((game.turnNo == 1) && (game.phaseNo == 1)){ 
			createTrain(3,4,game.player1);
			createTrain(35,34,game.player2);
		}
	    
	    for (Train train: game.trainList){
	    	trainStage.addActor(train);
	    	
	    }
	}
	
	private void createTrain(int X, int Y, Player owner)
	{
		Texture trainTexture;
		if (owner.playerNumber == 1){
			trainTexture = game.tempTrain1;
		}
		else{
			trainTexture = game.tempTrain2;
		}
		//I feel like this is over-complicated. Do we need a trainList and trainsInStation?
		Tile tileForTrain = game.gameMap.mapArray[X][Y];
		if (tileForTrain instanceof City){
			City cityForTrain = (City) tileForTrain;
			Train newTrain = new Train(game.trainID, game.gameMap.mapArray[X][Y], owner, X*32, Y*32, trainTexture);
			game.trainList.add(newTrain);
			cityForTrain.trainsInStation.add(newTrain);
			owner.trains.add(newTrain);
			game.gameMap.mapArray[X][Y] = cityForTrain;
			game.trainID++; //This needs to be implemented properly at some point
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
			for (Goal goal : game.player1.goals){
				goal.addTrain();
			}
			for (Goal goal : game.player2.goals){
				goal.addTrain();
			}
			if (game.phaseNo < 5){
				for (City city : game.cityList){
					city.highlighted = false;
					city.currentTexture = city.defaultTexture;
				}
				game.setScreen(new GameScreen(game, game.phaseNo+1, game.turnNo));
			}
			else{
				if (game.player1.goals.size() < 3){
					new Goal(game.player1, game);
				}
				if (game.player2.goals.size() < 3){
					new Goal(game.player2, game);
				}
				game.setScreen(new GameScreen(game, 1, game.turnNo+1));
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
        if(game.phaseNo == 4){
        	System.out.println("tested for game phase");
        	for(Train train : game.trainList){
        		System.out.println("running for each train");
        		System.out.println(train.route);
        		for(int[] location : train.route){
        			System.out.println("creating actions");
        			MoveToAction moveAction = new MoveToAction();
        			moveAction.setPosition(location[0], location[1]);
        			System.out.println(location[0]);
        			System.out.println(location[1]);
        			moveAction.setDuration(1f);
        			train.addAction(moveAction);
        		}
        	}
        }
        trainStage.act(delta);
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
