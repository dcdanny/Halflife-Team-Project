package com.halflife.enemies;

import com.halflife.entities.Player;

import javafx.scene.shape.Polygon;
import main.GameConstants;

public class Spike extends Polygon{
	
	private final String type;
	
	public Spike(double[] points) {
		super(points);
		type = GameConstants.TYPE_SPIKE;
	}
	
	
	public void tick(Player player) {
		if(playerCollision(player)) {
			player.loseLife();
		}
	}
	
	
	private boolean playerCollision(Player player) {
		if(this.getBoundsInParent().intersects(player.getBoundsInParent()))
			return true;
		return false;
	}
	
	public String getType(){
		return type;
	}

}
