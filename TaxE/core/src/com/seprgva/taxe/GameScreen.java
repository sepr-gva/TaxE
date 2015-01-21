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
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen implements Screen {
	
	TaxE game;
	int turnNo;
	
	private Stage baseStage, cityStage;
	private float origX, origY, maxX, maxY;
	OrthographicCamera uiCamera;
	GeneralButton nextPhaseButton;
	boolean routeDone = false;
	
	//equivalent of create
	public GameScreen(final TaxE gameInstance, int currentPhase, int turnNumber) {
		game = gameInstance;
		game.phaseNo = currentPhase;
		turnNo = turnNumber;
		baseStage = new Stage(new ScreenViewport());
		cityStage = new Stage(new ScreenViewport());
		uiCamera = new OrthographicCamera();
		uiCamera.setToOrtho(false, 1000, 625);
		uiCamera.update();
		
	    origX = baseStage.getCamera().position.x;
	    origY = baseStage.getCamera().position.y;
	    maxX = origX + ((game.gameMap.xSize*32)-Gdx.graphics.getWidth());
	    maxY = origY + ((game.gameMap.ySize*32)-Gdx.graphics.getHeight());
	    
	    nextPhaseButton = new GeneralButton(game.nextPhase, 942, 10, 48, 48, uiCamera);
	    
	    System.out.println("Turn no: " + turnNo + ". Phase: " + game.phaseNo);
		
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
			Gdx.input.setInputProcessor(baseStage);
		}
		else{
			//Deployment phase
			Gdx.input.setInputProcessor(cityStage);
			Player player;
			if (game.phaseNo == 3){
				player = game.player1;
			}
			else{
				player = game.player2;
			}
			for (City city : game.cityList){
				city.highlighted = false;
				city.currentTexture = city.defaultTexture;
			}
			for (Train train : player.trains){
				train.possibleRoutes = isReachable(train.currentLocation);
			}
			
		}
		
		if ((turnNo == 1) && (game.phaseNo == 1)){ 
			createTrain(2,2,game.player1);
			createTrain(2,10,game.player2);
		}
	    
	    for (Train train: game.trainList){
	    	baseStage.addActor(train);
	    }
	    
	    //ArrayList<Rail> testRoute = game.gameMap.getRoute(game.gameMap.mapArray[2][2],game.gameMap.mapArray[2][10]);
	    //for (Rail rail: testRoute){
	    //	System.out.println(rail.xCoord + ", " + rail.yCoord);
	    //}
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
		Tile currentTile, prevTile, nextTile;
		if (city.neighbours[0] instanceof Rail){
			currentTile = city.neighbours[0];
			prevTile = city;
			nextTile = null;
			while (currentTile instanceof Rail && currentTile.junction == false){
				route1.add(currentTile);
				for (Tile tile : currentTile.neighbours){
					if (tile instanceof Rail && tile != prevTile || tile instanceof City && tile != prevTile){
						nextTile = tile;
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
			while (currentTile instanceof Rail && currentTile.junction == false){
				route2.add(currentTile);
				for (Tile tile : currentTile.neighbours){
					if (tile instanceof Rail && tile != prevTile || tile instanceof City && tile != prevTile){
						nextTile = tile;
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
			while (currentTile instanceof Rail && currentTile.junction == false){
				route3.add(currentTile);
				for (Tile tile : currentTile.neighbours){
					if (tile instanceof Rail && tile != prevTile || tile instanceof City && tile != prevTile){
						nextTile = tile;
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
			while (currentTile instanceof Rail && currentTile.junction == false){
				route4.add(currentTile);
				for (Tile tile : currentTile.neighbours){
					if (tile instanceof Rail && tile != prevTile || tile instanceof City && tile != prevTile){
						nextTile = tile;
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
			if (baseStage.getCamera().position.x > maxX)
			{
				cityStage.getCamera().position.x = maxX;
				baseStage.getCamera().position.x = maxX;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			baseStage.getCamera().translate(-5,0,0);
			cityStage.getCamera().translate(-5,0,0);
			if (baseStage.getCamera().position.x < origX)
			{
				cityStage.getCamera().position.x = origX;
				baseStage.getCamera().position.x = origX;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			baseStage.getCamera().translate(0,-5,0);
			cityStage.getCamera().translate(0,-5,0);
			if (baseStage.getCamera().position.y < origY)
			{
				cityStage.getCamera().position.y = origY;
				baseStage.getCamera().position.y = origY;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)){
			cityStage.getCamera().translate(0,5,0);
			baseStage.getCamera().translate(0,5,0);
			if (baseStage.getCamera().position.y > maxY)
			{
				cityStage.getCamera().position.y = maxY;
				baseStage.getCamera().position.y = maxY;
			}
		}
		baseStage.getCamera().update();
		cityStage.getCamera().update();
		
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
			if (game.phaseNo < 4){
				game.setScreen(new GameScreen(game, game.phaseNo+1, turnNo));
			}
			else{
				game.setScreen(new GameScreen(game, 1, turnNo+1));
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
        
        game.batch.setProjectionMatrix(uiCamera.combined);
		
		game.batch.begin();
		
		//Draw player1's avatar top left and player2's top right
		game.batch.draw(game.player1.avatar, 900, 525, 100, 100);
		game.batch.draw(game.player2.avatar, 0, 525, 100, 100);
		
		//Draw player1's company name, gold and passengers delivered
		game.font.draw(game.batch, game.player1.companyName, 10, 515);
		game.font.draw(game.batch, "Gold: " + game.player1.money, 10, 500);
		game.font.draw(game.batch, "Passengers delivered: " + game.player1.safePass, 10, 485);
		
		//Draw player2's company name, gold and passengers delivered
		//uses font.getBounds() to align from the right
		game.font.draw(game.batch, game.player2.companyName, 990 - ((game.font.getBounds(game.player2.companyName).width)), 515);
		String string = "Gold: " + game.player1.money;
		game.font.draw(game.batch, string, 990 - (game.font.getBounds(string).width), 500);
		string = "Passengers delivered: " + game.player2.safePass;
		game.font.draw(game.batch, string, 990 - (game.font.getBounds(string).width), 485);
		
		nextPhaseButton.draw();
		
		game.batch.end();
        
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
