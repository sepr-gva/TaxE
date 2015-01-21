package com.seprgva.taxe;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;

public class Player {
	
	/* Attributes for player include: 
	 * -Customisation fields: companyName and avatar 
	 * -Resources: money, reputation, score and safe passengers
	 * -List of trains owned by the player
	 * -Goals/objectives given to the player
	 * -Player Number 
	*/
	String companyName = "";
	Texture avatar;
	ArrayList<Train> trains;
	int playerNumber, money = 1000, reputation = 0, score = 0, safePass = 0;
	ArrayList<Goal> goals;
	
	public Player(int num){
		this.playerNumber = num;
		this.goals = new ArrayList<Goal>();
		this.trains = new ArrayList<Train>();
	}

}
