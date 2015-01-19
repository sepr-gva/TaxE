package com.seprgva.taxe;

import java.util.ArrayList;
import com.badlogic.gdx.math.MathUtils;

public class Goal {
	
	int turnsLeft, reward, passengers;
	Player player;
	String description;
	City start, end;
	ArrayList<Train> trains;
	TaxE game;
	
	//Simple random goal generated, no check for whether it is achievable
	//Will be made smarter when map fully implemented
	public Goal(Player player, TaxE gameInstance){
			this.game = gameInstance;
			this.turnsLeft = MathUtils.random(4, 7);
			int v = MathUtils.random(0,2);
			boolean via = false;
			if (v == 0){
				via = true;
			}
			int s = MathUtils.random(0, game.cityList.size() - 1);
			int e = MathUtils.random(0, game.cityList.size() - 1);
			while (s == e){
				e = MathUtils.random(0, game.cityList.size() - 1);
			}
			this.start = game.cityList.get(s);
			this.end = game.cityList.get(e);
			this.passengers = 100;
			if (via == true){
				while (v == e || v == s){
					v = MathUtils.random(0, game.cityList.size() - 1);
				}
				this.description = "Take " + this.passengers + " passengers from " + this.start.cityName + 
						" to " + this.end.cityName + " via " + game.cityList.get(v).cityName + " in " + this.turnsLeft + " turns.      " + player.companyName;
			}
			else{
				this.description = "Take " + this.passengers + " passengers from " + this.start.cityName + 
						" to " + this.end.cityName + " in " + this.turnsLeft + " turns.      " + player.companyName;
			}
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
			player.goals.remove(this);
		}
	}
	
	public void isComplete(){
		if (turnsLeft > 0){
			for (Train train : this.trains){
				if (end.trainsInStation.contains(train, true)){
					player.money += this.reward;
					train.passengers -= this.passengers;
					player.score += this.reward / 100 * this.passengers;
					player.goals.remove(this);
				}
			}
		}
		else{
			player.goals.remove(this);
		}
	}

}
