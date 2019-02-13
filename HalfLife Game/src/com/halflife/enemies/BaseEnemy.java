package com.halflife.enemies;

import com.halflife.entities.RectObject;

import javafx.scene.paint.Color;

public class BaseEnemy extends RectObject {
	
	private boolean startMoving;

	public BaseEnemy(int x, int y, int width, int height, String type, Color col) {
		
		super(x, y, width, height, type, col);		
		// TODO Auto-generated constructor stub
		startMoving = false;
	}
	
	public void PlayerNear() {
		// detection for if the player comes within x pixels
		
		startMoving = true;
		while(startMoving && !dead) {
			//moveLeft(5);
		}
	}

}
