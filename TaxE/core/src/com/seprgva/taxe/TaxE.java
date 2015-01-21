package com.seprgva.taxe;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TaxE extends Game {
	public SpriteBatch batch;
	
	public Texture gva, how, play, quit, title, blackAvatar, maleAvatar, femaleAvatar, 
	selected, done, chooseAv, chooseName, player1Custom, player2Custom, greenSquare,
	redSquare, greySquare, tempTrain1, tempTrain2, player1Build, player2Build, brownSquare,
	emptyTile, emptyTileHover, ld, lu, rd, ru, junction, junctionred, junctionblue, udl,
	udlred, udlblue, ulr, ulrred, ulrblue, udr, udrred, udrblue, dlr, dlrred, dlrblue,
	lr, ud, nextPhase, 
	city, cityred, cityblue, turret, menuBackground;

	public BitmapFont font;
	public Player player1, player2;
	Map gameMap;
	int trainID;
	ArrayList<Train> trainList;
	ArrayList<City> cityList;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		trainList = new ArrayList<Train>();
		cityList = new ArrayList<City>();
		trainID = 0;
		
		//Initialise players
		player1 = new Player(1);
		player2 = new Player(2);
		
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
		menuBackground = new Texture(Gdx.files.internal("Images/menuBackground.png"), true);
		menuBackground.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
				
		//In-game graphics
		greenSquare = new Texture(Gdx.files.internal("gameGraphics/greenSquare.png"), true);
		redSquare = new Texture(Gdx.files.internal("gameGraphics/redSquare.png"), true);
		greySquare = new Texture(Gdx.files.internal("gameGraphics/greySquare.png"), true);
		tempTrain1 = new Texture(Gdx.files.internal("gameGraphics/tempTrain1.png"), true);
		tempTrain2 = new Texture(Gdx.files.internal("gameGraphics/tempTrain2.png"), true);
		player1Build = new Texture(Gdx.files.internal("gameGraphics/player1build.png"), true);
		player2Build = new Texture(Gdx.files.internal("gameGraphics/player2build.png"), true);
		brownSquare = new Texture(Gdx.files.internal("gameGraphics/brownSquare.png"), true);
		nextPhase = new Texture(Gdx.files.internal("gameGraphics/nextPhase.png"), true);
		
		//Tile Textures
		emptyTile = new Texture(Gdx.files.internal("gameGraphics/emptyTile.png"), true);
		emptyTile.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		emptyTileHover = new Texture(Gdx.files.internal("gameGraphics/emptyTileHover.png"), true);
		ld = new Texture(Gdx.files.internal("gameGraphics/railLD.png"), true);
		ld.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		lu = new Texture(Gdx.files.internal("gameGraphics/railLU.png"), true);
		lu.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		rd = new Texture(Gdx.files.internal("gameGraphics/railRD.png"), true);
		rd.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		ru = new Texture(Gdx.files.internal("gameGraphics/railRU.png"), true);
		ru.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		junction = new Texture(Gdx.files.internal("gameGraphics/rail4Way.png"), true);
		junction.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		junctionred = new Texture(Gdx.files.internal("gameGraphics/rail4WayRed.png"), true);
		junctionred.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		junctionblue = new Texture(Gdx.files.internal("gameGraphics/rail4WayBlue.png"), true);
		junctionblue.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		udl = new Texture(Gdx.files.internal("gameGraphics/railUDL.png"), true);
		udl.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		udlred = new Texture(Gdx.files.internal("gameGraphics/railUDLRed.png"), true);
		udlred.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		udlblue = new Texture(Gdx.files.internal("gameGraphics/railUDLBlue.png"), true);
		udlblue.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		udr = new Texture(Gdx.files.internal("gameGraphics/railUDR.png"), true);
		udr.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		udrred = new Texture(Gdx.files.internal("gameGraphics/railUDRRed.png"), true);
		udrred.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		udrblue = new Texture(Gdx.files.internal("gameGraphics/railUDRBlue.png"), true);
		udrblue.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		ulr = new Texture(Gdx.files.internal("gameGraphics/railULR.png"), true);
		ulr.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		ulrred = new Texture(Gdx.files.internal("gameGraphics/railULRRed.png"), true);
		ulrred.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		ulrblue = new Texture(Gdx.files.internal("gameGraphics/railULRBlue.png"), true);
		ulrblue.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		dlr = new Texture(Gdx.files.internal("gameGraphics/railDLR.png"), true);
		dlr.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		dlrred = new Texture(Gdx.files.internal("gameGraphics/railDLRRed.png"), true);
		dlrred.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		dlrblue = new Texture(Gdx.files.internal("gameGraphics/railDLRBlue.png"), true);
		dlrblue.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		lr = new Texture(Gdx.files.internal("gameGraphics/railLR.png"), true);
		lr.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		ud = new Texture(Gdx.files.internal("gameGraphics/railUD.png"), true);
		ud.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		city = new Texture(Gdx.files.internal("gameGraphics/city96.png"), true);
		city.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		cityblue = new Texture(Gdx.files.internal("gameGraphics/citySelected.png"), true);
		cityblue.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		cityred = new Texture(Gdx.files.internal("gameGraphics/cityHighlighted.png"), true);
		cityred.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		turret = new Texture(Gdx.files.internal("gameGraphics/turretTile.png"), true);
		turret.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		
		//Map generation
		//Map is currently set to 40x40
		gameMap = new Map(this);
		
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
