package com.seprgva.taxe;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;

public class Player {
	
	// Can't remember all the attributes for player, add the rest
	//(This is just to implement customisation)
	String companyName = "";
	Texture avatar;
	ArrayList<Train> trains;
	int playerNumber, money, reputation, score;
	ArrayList<Goal> goals;
	
	public Player(int num){
		this.playerNumber = num;
		this.goals = new ArrayList<Goal>();
		this.trains = new ArrayList<Train>();
	}

}
