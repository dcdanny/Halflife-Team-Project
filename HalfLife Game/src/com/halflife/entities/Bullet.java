package com.halflife.entities;

import com.sun.org.apache.bcel.internal.Constants;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import main.CheckCollision;
import main.GameConstants;

public class Bullet extends RectObject{

	private CheckCollision collisionChecker;
	public Bullet(double x, double y, int width, int height, String type, Color col, Pane root) {
		super(x+30, y + 25, width, height, type, col);
		collisionChecker = new CheckCollision();
		startTimer(System.currentTimeMillis(), root);
	}
	
	public void startTimer(long startTime, Pane root) {
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				long secondsElapsed = (System.currentTimeMillis()-startTime)/1000;
				moveX(GameConstants.BULLET_SPEED);
				if (secondsElapsed >= 3 || checkCollided(root)){
					setDead(true);
					stop();
				}
			}
		};
		timer.start();
	}
	
	public boolean checkCollided(Pane root) {
	    collisionChecker.checkForCollision(this, root);
		return collisionChecker.getCollided();
	}
}
