package com.halflife.enemies;

import javafx.scene.shape.Polygon;

public class Spike extends Polygon{
	
	private final String type;
	
	public Spike(double[] points) {
		super(points);
		type = "spike";
	}
	
	void PlayerCollision() {
		
	}
	
	public String getType(){
		return type;
	}

}
