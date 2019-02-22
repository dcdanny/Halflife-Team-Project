package com.halflife.entities;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import main.Ammo;
import main.CheckCollision;
import main.Lives;

public class Player extends RectObject{
	
	
	private double velX = 0;
	private double velY = 0;
	private double gravity = 0;
	private int ammo = 10; 
	private boolean isJumping = false;
	
	public Player(double x, double y, int width, int height, Color col, int lives) {
		super(x, y, width, height, "player", col);

	

		Ammo.setAmmo(ammo);

		movement(x, y);		
	}
	
	public void tick(Pane root, Lives heart) {
		moveX((int)velX);
		moveY((int)velY);	
		
		setVelY(5);
		
		RectObject collidedObj = CheckCollision.checkForCollision(this, root);
		if (CheckCollision.getCollided()) {
			if (collidedObj.getType().equals("plat")) {
				 setVelY(0);
				setTranslateY(collidedObj.getTranslateY() - 50);
		    }
			else if (collidedObj.getType().equals("goal")) 
				System.out.println("Winner");
			else if (collidedObj.getType().equals("floor")) {
				heart.lostlife();
				this.setTranslateX(200);
				this.setTranslateY(0);
				//this.setDead(true);
			}
			else if (collidedObj.getType().equals("wall")) {
				this.setTranslateX(getTranslateX() + 20);
				//this.setTranslateY(getTranslateY() + 50);
			}
		}
		

	}
	public void loseLife() {
		 
		
	}
	
	public void setVelX(double v) {
		this.velX = v;
	}
	public void setVelY(double v) {
		this.velY = v;
	}
	
	public double getVelX() {
		return velX;
	}
	public double getVelY() {
		return velY;
	}
	
	public void jump() {
		double startingY = this.getTranslateY();
		AnimationTimer jTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				setVelY(-15+gravity);
				gravity += .5;
				if (startingY < getYLocation() || CheckCollision.getCollided()) {
					stop();
                    gravity = 0;
                    setVelY(0);
                    setTranslateY(getTranslateY() - 15);
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

	
	public void shoot(Pane root) {
		
		if (ammo > 0) {
			Bullet bullet = getBullet(this, Color.RED);
			root.getChildren().add(bullet);
			ammo--;
		}else
			System.out.println("No Bullets");
		Ammo.setAmmo(ammo);
	}
	
//respawn animation, flashing player
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
	
	public double getGravity() {
		return gravity;
	}

	public int getAmmo() {
		return ammo;
	}


}
