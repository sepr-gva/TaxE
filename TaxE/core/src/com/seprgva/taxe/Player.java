package com.seprgva.taxe;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class Player {
	
	// Can't remember all the attributes for player, add the rest
	//(This is just to implement customisation)
	String companyName;
	Texture avatar;
	Array<Train> trains;
	int playerNumber, money, reputation;
	
	public Player(int num){
		this.playerNumber = num;
	}

}
