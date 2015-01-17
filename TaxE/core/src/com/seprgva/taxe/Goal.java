package com.seprgva.taxe;

import com.badlogic.gdx.utils.Array;

public class Goal {
	
	int turnsLeft, reward, passengers, points;
	Player player;
	String description;
	City start, end;
	Array<Train> trains;
	
	public Goal(String des, City start, City end, int pas, int turns, int reward, int points, Player player){
		this.description = des;
		this.start = start;
		this.end = end;
		this.passengers = pas;
		this.turnsLeft = turns;
		this.reward = reward;
		this.player = player;
		this.points = points;
		trains = new Array<Train>();
		player.goals.add(this);
	}
	
	
	//Execute both these commands for every player goal at the end of each turn
	//And take one turn away from every goals turnsLeft (maybe reduce reward)
	
	public void addTrain(){
		for (Train train : player.trains){
			if (start.trainsInStation.contains(train, true)){
				this.trains.add(train);
				train.passengers += this.passengers;
			}
		}
	}
	
	public void isComplete(){
		if (turnsLeft > 0){
			for (Train train : this.trains){
				if (end.trainsInStation.contains(train, true)){
					player.money += this.reward;
					train.passengers -= this.passengers;
					player.score += this.points;
					player.goals.removeValue(this, true);
				}
			}
		}
		else{
			player.goals.removeValue(this, true);
		}
	}

}
