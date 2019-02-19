package com.halflife.entities;

import com.sun.org.apache.bcel.internal.Constants;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import main.GameConstants;

public class Bullet extends RectObject{

	public Bullet(double x, double y, int width, int height, String type, Color col) {
		super(x+25, y + 5, width, height, type, col);
		
		startTimer(System.currentTimeMillis());
	}
	
	public void startTimer(long startTime) {
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				long secondsElapsed = (System.currentTimeMillis()-startTime)/1000;
				moveX(GameConstants.BULLET_SPEED);
				if (secondsElapsed >= 3){
					setDead(true);
					stop();
				}
			}
		};
		timer.start();
	}
	
}
