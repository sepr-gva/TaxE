package com.seprgva.taxe;

public class BuildTile extends Tile{
	
	public int owner;
	
	public BuildTile(int xCoord, int yCoord, int player){
		
		super(xCoord, yCoord, "Build"+(Integer.toString(player)));
		
		owner = player;
	}
	
	@Override
	public String toString()
	{
		String returnString = "";
		returnString = "Tile at position " + this.x + ", " + this.y + " is a Build"+(Integer.toString(owner));
		
		return returnString;
	}

}
