package com.seprgva.taxe;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TaxE extends Game {
	public SpriteBatch batch;
	public Texture gva, how, play, quit, title, blackAvatar, maleAvatar, femaleAvatar, 
	selected, done, chooseAv, chooseName, player1Custom, player2Custom, greenSquare, redSquare, greySquare, tempTrain;
	public BitmapFont font;
	public Player player1, player2;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		
		//Texts
		gva = new Texture(Gdx.files.internal("Text/GroupName.png"), true);
		gva.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		how = new Texture(Gdx.files.internal("Text/How To.png"), true);
		how.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		play = new Texture(Gdx.files.internal("Text/Play.png"), true);
		play.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		quit = new Texture(Gdx.files.internal("Text/Quit.png"), true);
		quit.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		title = new Texture(Gdx.files.internal("Text/TaxE.png"), true);
		title.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		done = new Texture(Gdx.files.internal("Text/Done.png"), true);
		done.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		player1Custom = new Texture(Gdx.files.internal("Text/Player1Custom.png"), true);
		player1Custom.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		player2Custom = new Texture(Gdx.files.internal("Text/Player2Custom.png"), true);
		player2Custom.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		chooseAv = new Texture(Gdx.files.internal("Text/ChooseAvatar.png"), true);
		chooseAv.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		chooseName = new Texture(Gdx.files.internal("Text/ChooseName.png"), true);
		chooseName.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		
		//Images
		blackAvatar = new Texture(Gdx.files.internal("Images/BlackAvatar.png"), true);
		blackAvatar.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		femaleAvatar = new Texture(Gdx.files.internal("Images/FemaleAvatar.png"), true);
		femaleAvatar.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		maleAvatar = new Texture(Gdx.files.internal("Images/MaleAvatar.png"), true);
		maleAvatar.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		selected = new Texture(Gdx.files.internal("Images/Selected.png"), true);
		selected.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		
		player1 = new Player(1);
		player2 = new Player(2);
		
		//in-game graphics
		greenSquare = new Texture(Gdx.files.internal("gameGraphics/greenSquare.png"), true);
		greenSquare.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		redSquare = new Texture(Gdx.files.internal("gameGraphics/redSquare.png"), true);
		redSquare.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		greySquare = new Texture(Gdx.files.internal("gameGraphics/greySquare.png"), true);
		greySquare.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		tempTrain = new Texture(Gdx.files.internal("gameGraphics/tempTrain.png"), true);
		tempTrain.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}
