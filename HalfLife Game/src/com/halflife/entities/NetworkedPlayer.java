package com.halflife.entities;

import java.io.IOException;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import main.Ammo;
import main.CheckCollision;
import main.CountdownTimer;
import main.Game;
import main.GameConstants;
import main.Lives;
import main.WriteFile;
import network.Message;

public class NetworkedPlayer extends Player {
	
	
	private double velX = 0;
	private double velY = 0;
	private double gravity = 0;
	private int ammoNo = 10; 
	private boolean isJumping = false;
	private boolean completedLevel;
	private Ammo ammo;
	private CountdownTimer clock;
	private Pane foreground=new Pane();

	public NetworkedPlayer(double x, double y, int width, int height, Color col, int lives) {
		super(x,y,width,height, col, 3);
		
		collisionChecker = new CheckCollision();


		ammo.setAmmo(ammoNo);

		movement(x, y);		
		
		completedLevel = false;
		
		ammo = new Ammo(0);
		clock = new CountdownTimer();
		foreground.getChildren().add(clock);
		foreground.getChildren().add(ammo);
	}
	
	public Pane getForeground() {
		return foreground;
	}

	
	public void tick(Pane root, Lives hearts) {
//		if (lives == 0) {	
//			setDead(true);
//			
//		}
		
		moveX((int)velX);
		moveY((int)velY);	
		
		setVelY(10);
		
		RectObject collidedObj = collisionChecker.checkForCollision(this, root);
		if (collisionChecker.getCollided()) {
			if (collidedObj.getType().equals(GameConstants.TYPE_PLATFORM)) {
				this.setFill(Color.RED);
				setVelY(0);
				setTranslateY(collidedObj.getTranslateY() - 50);
//				System.out.println("1");
		    }
			else if (collidedObj.getType().equals(GameConstants.TYPE_GOAL)) {
				setVelY(0);
				if (!completedLevel) {
					completedLevel = true;
					System.out.println("Winner");
//					Message mWin = new Message("Goal");
				}
				
				
				WriteFile wr = new WriteFile(false);
				try {
					wr.write("level1=true");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}	
			else if (collidedObj.getType().equals(GameConstants.TYPE_FLOOR)) {
				loseLife(root);
			}
			else if (collidedObj.getType().equals(GameConstants.TYPE_WALL)) {
				this.setTranslateX(getTranslateX() + 20);
			}
		}
	}
//	public void loseLife(Pane root) {
//		root.setLayoutX(0);
//		if (lives > 0) {
//			this.Fade();
//			this.setTranslateX(200);
//			this.setTranslateY(0);
//			lives--;	
//			heart.lostlife();
//		}else
//			setDead(true);
//			Message mDead = new Message("", "hasDied");
//	}
	
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
				setVelY(-10+gravity);
				gravity += .5;
				if (startingY < getYLocation() || collisionChecker.getCollided()) {
					stop();
                    gravity = 0;
                    setVelY(0);
                    setTranslateY(getTranslateY() - 10);
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

	public boolean hasCollided(Pane root) {
		collisionChecker.checkForCollision(this, root);
		return collisionChecker.getCollided();
	}
	
	public void shoot(Pane root) {
		
		if (ammoNo > 0) {
			Bullet bullet = getBullet(this, Color.RED, root);
			root.getChildren().add(bullet);
			ammoNo--;
		}else
			System.out.println("No Bullets");
		ammo.setAmmo(ammoNo);
	}
	
//respawn animation, flashing player
	public void Fade() {
		if (getType().equals(GameConstants.TYPE_PLAYER)) {
				
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
		return ammoNo;
	}

	public void buttonPressing(Game game, Scene s) {
	
		s.setOnKeyPressed(e-> {
			switch (e.getCode()) {
			case A:
				// message to server
//				Message mLeft= new Message("","A");
				
				setVelX(-5);
				game.root.setLayoutX(game.root.getLayoutX()+10);
				break;
			case D: 
				//message to server
//				Message mRight = new Message("","D");
				
				setVelX(5);
				game.root.setLayoutX(game.root.getLayoutX()-10);
				game.root.setStyle("-fx-background-color: #4f7b8a;");
				break;
			case S: 
				//setVelY(5);
				break;
			case W:
				//message to server
//				Message mJump = new Message("","W");
				
				
				if (getGravity() == 0 && hasCollided(game.root)) {
					setTranslateY(getTranslateY() - 10);
					jump();
				}
				break;
			case SPACE:
				//message to server
//				Message mShoot = new Message("","SPACE");
				
				
				ammo.lostBullet();
				shoot(game.root);
				break;
			}
			
		});
		
	}

	public void buttonReleasing(Scene s) {
		
		s.setOnKeyReleased(e-> {
			switch (e.getCode()) {
			case A:
				setVelX(0);
				break;
			case D: 
				setVelX(0);
				break;
			case S: 
				setVelY(0);
				break;
			case W:
				break;
			}
			
		});
		
	}

	public void checkPos(Game game) {
		double x =getXLocation();
		if (x>400) {
			game.root.setLayoutX(game.root.getTranslateX()-(x-400));
		}
		
	}
}
