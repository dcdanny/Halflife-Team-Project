package com.halflife.entities;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class Player extends RectObject{
	
	
	private double velX = 0;
	private double velY = 0;
	private int gravity = 0;

	public Player(double x, double y, int width, int height, Color col) {
		super(x, y, width, height, "player", col);

		movement(x, y);		
	}
	
	public void tick() {
		moveX((int)velX);
		moveY((int)velY);	
	}
	
	public void setVelX(double v) {
		this.velX = v;
	}
	public void setVelY(double v) {
		this.velY = v;
	}
	
	public void jump() {
		double startingY = this.getTranslateY();
		
		AnimationTimer jTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				setVelY(-10+gravity);
				gravity += 1;
				if (startingY < getYLocation()) {
					stop();
                    gravity = 0;
                    setVelY(0);
				}
			}
		};
		
		jTimer.start();
	}
	
	public void movement(double x, double y) {
		if (isFalling() || isJumping()) {
			y += gravity;
		}
	}
	
	private void checkCollision(Shape block) {
		  boolean isCollided = false;
		  for (Node static_bloc : getAllNodes(root)) {
		    if (static_bloc != block) {
		      ((Shape) static_bloc).setFill(Color.GREEN);

		      if (block.getBoundsInParent().intersects(static_bloc.getBoundsInParent())) {
		    	  isCollided = true;
		      }
		    }
		  }

		  if (isCollided) {
		    block.setFill(Color.BLUE);
		  } else {
		    block.setFill(Color.GREEN);
		  }
	}
	

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
	
	public int getGravity() {
		return gravity;
	}

}
