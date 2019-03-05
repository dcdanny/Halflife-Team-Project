package com.halflife.entities;

import java.io.IOException;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import main.Ammo;
import main.CheckCollision;
import main.Game;
import main.GameConstants;
import main.Lives;
import main.SpriteAnimation;
import main.WriteFile;

public class SpritePlayer extends Pane {
	SpriteAnimation animation;
	private double velX = 0;
	private double velY = 0;
	private double gravity = 0;
	private int ammo = 10; 
	private int lives = 3;
	private boolean isJumping = false;
	private Lives heart;
	private boolean completedLevel;
	private boolean dead=false;
	protected FadeTransition ft;
	
	private CheckCollision collisionChecker;
	
	public SpritePlayer() {
	

	     gravity = 0.5f;
		 boolean falling = true;
		boolean jumping = false;
	    
		 boolean dead= false;
		 final String type;

		animation =new SpriteAnimation();
		getChildren().add(animation);
		collisionChecker = new CheckCollision();
		
	}
	public void moveX(int i) {
			setTranslateX(getTranslateX()+i); 
		
	}
	public void moveY(int i) {
		setTranslateY(getTranslateY()+i); 
	
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
	
	public double getYLocation() {
		return this.getTranslateY();
	}
	public double getXLocation() {
		return this.getTranslateX();
	}
	public void loseLife(Pane root) {
		root.setLayoutX(0);
		if (lives > 0) {
			this.Fade();
			this.setTranslateX(200);
			this.setTranslateY(0);
			lives--;	
			heart.lostlife();
		}else
			dead=true;
	}
	public void tick(Pane root, Lives hearts) {
		if (lives == 0) {	
			dead=true;
			
		}
		
		this.heart = hearts;
		moveX((int)velX);
		moveY((int)velY);	
		
		//setVelY(10);
		
		/*RectObject collidedObj = collisionChecker.checkForCollision(this, root);
		if (collisionChecker.getCollided()) {
			if (collidedObj.getType().equals(GameConstants.TYPE_PLATFORM)) {
				setVelY(0);
				setTranslateY(collidedObj.getTranslateY() - 50);
//				System.out.println("1");
		    }
			else if (collidedObj.getType().equals(GameConstants.TYPE_GOAL)) {
				setVelY(0);
				if (!completedLevel) {
					completedLevel = true;
					System.out.println("Winner");
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
			}*/
		//}
	}
	
	public void buttonPressing(Game game, Scene s) {
		
		s.setOnKeyPressed(e-> {
			switch (e.getCode()) {
			case A:
				setVelX(-5);
				game.root.setLayoutX(game.root.getLayoutX()+10);
				break;
			case D: 
				setVelX(5);
				game.root.setLayoutX(game.root.getLayoutX()-10);
				game.root.setStyle("-fx-background-color: #4f7b8a;");
				break;
			case S: 
				setVelY(5);
				break;
			case W:
				if (getGravity() == 0 && hasCollided(game.root)) {
					setTranslateY(getTranslateY() - 10);
					jump();
				}
				break;
			case SPACE:
				game.ammo.lostBullet();
				//shoot(game.root);
				break;
			}
			
		});
		
	}
	public double getGravity() {
		return gravity;
	}

	public int getAmmo() {
		return ammo;
	}
	public boolean hasCollided(Pane root) {
		//collisionChecker.checkForCollision(this, root);
		
		return true;
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


public void Fade() {
	
			
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

