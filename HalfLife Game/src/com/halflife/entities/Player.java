package com.halflife.entities;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Player extends RectObject{
	
	public Player(int x, int y, int width, int height, Color col) {
		super(x, y, width, height, "player", col);
		movement(x, y);
	}

	public void movement(int x, int y) {
		if (isFalling() || isJumping()) {
			y += gravity;
		}
	}
	
	@Override
	public void Fade() {
		if (getType().equals("player")) {
				
			 ft = new FadeTransition(Duration.millis(500), this);
			
			 ft.setFromValue(1.0);
			 ft.setToValue(0.0);
			 ft.setCycleCount(10);
			 ft.setAutoReverse(true);
			    
			
			 Double opa = this.getOpacity();
	         if (opa.intValue() == 0) {
	             return;
	         }
	            
	         Animation.Status as = ft.getStatus();
	         
	         if (as == Animation.Status.RUNNING) {
	             return;
	         
	         }
	         if (as == Animation.Status.STOPPED) {
	             ft.play();
	         }           
	         
		}
	}
}
