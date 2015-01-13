package com.seprgva.taxe;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TaxE extends Game {
	public SpriteBatch batch;
	Texture gva, how, play, quit, title;
	BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		
		gva = new Texture(Gdx.files.internal("Text/GroupName.png"));
		gva.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		how = new Texture(Gdx.files.internal("Text/How To.png"));
		how.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		play = new Texture(Gdx.files.internal("Text/Play.png"));
		play.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		quit = new Texture(Gdx.files.internal("Text/Quit.png"));
		quit.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		title = new Texture(Gdx.files.internal("Text/TaxE.png"));
		title.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		
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
