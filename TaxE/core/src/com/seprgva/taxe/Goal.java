package com.seprgva.taxe;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class Goal {
	
	int turnsLeft, reward, passengers;
	Player player;
	String description;
	City start, end;
	ArrayList<Train> trains;
	TaxE game;
	
	public Goal(Player player, TaxE gam){
		this.game = gam;
		this.turnsLeft = MathUtils.random(4, 7);
		int s = MathUtils.random(0, game.gameMap.cityList.size() - 1);
		int e = MathUtils.random(0, game.gameMap.cityList.size() - 1);
		while (s == e){
			e = MathUtils.random(0, game.gameMap.cityList.size() - 1);
		}
		this.start = game.gameMap.cityList.get(s);
		this.end = game.gameMap.cityList.get(e);
		this.passengers = MathUtils.random(100, 200);
		this.description = "Take " + this.passengers + " passengers from " + this.start.cityName + 
				" to " + this.end.cityName + " in " + this.turnsLeft + " turns.      " + player.companyName;
		this.reward = this.passengers * 10;
		this.player = player;
		trains = new ArrayList<Train>();
		player.goals.add(this);
	}
	
	
	//Execute both these commands for every player goal at the end of each turn
	//And take one turn away from every goals turnsLeft (maybe reduce reward)
	
	public void addTrain(){
		if (turnsLeft > 0){
			for (Train train : player.trains){
				if (start.trainsInStation.contains(train, true)){
					this.trains.add(train);
					train.passengers += this.passengers;
				}
			}
		}
		else{
			player.goals.removeValue(this, true);
		}
	}
	
	public void isComplete(){
		if (turnsLeft > 0){
			for (Train train : this.trains){
				if (end.trainsInStation.contains(train, true)){
					player.money += this.reward;
					train.passengers -= this.passengers;
					player.score += this.reward / 100 * this.passengers;
					player.goals.removeValue(this, true);
				}
			}
		}
		else{
			player.goals.removeValue(this, true);
		}
	}

}
