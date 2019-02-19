package com.halflife.enemies;

import com.halflife.entities.RectObject;

import javafx.scene.paint.Color;

public class SpikePlatform extends RectObject {
	
	private Spike spike;
	
	public SpikePlatform(int x, int y, int width, int height, String type, Color col) {
		super(x,y,width,height,type,col);
		
		spike = new Spike(new double[] {x+(width/2), y-height, x, y, x+width, y});
	}

	public Spike getSpike() {
		return spike;
	}	
}
