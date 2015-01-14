package com.seprgva.taxe;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TaxE extends Game {
	public SpriteBatch batch;
	public Texture gva, how, play, quit, title, greenSquare;
	public BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		
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
		greenSquare = new Texture(Gdx.files.internal("gameGraphics/greenSquare.png"), true);
		greenSquare.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
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
