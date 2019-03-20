package com.halflife.entities;

import java.io.IOException;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.Scene;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.*;

/**
 * Player --- Creates the main player object for when the 
 * user plays single player games
 * @author David
 */
public class Player extends RectObject{

	private double velX = 0; //Velocity of the player in X direction
	private double velY = 0; //Velocity of the player in Y direction
	private double gravity = 0; //Value for the gravity in the game
	private int ammoNo = 10; //Number of bullets the player starts with 
	private int lives = 3; //Number of lives the player starts with
	private Lives heart; //Heart object for displaying lives
	private boolean completedLevel; //Determines whether player has completed the level
	private Pane foreground=new Pane(); //Objects on the screen that are independent to the player
	private Ammo ammo; //Ammo object for displaying bullets
	private CountdownTimer clock; //Clock object for displaying the count down timer
	private boolean bulletDir = true; //Determines which way the player is facing
	protected CheckCollision collisionChecker; //Determines whether the player has collided with another object
	
	/**
	 * Constructor of the player object
	 * @param x X coordinate of the player object
	 * @param y Y coordinate of the player object
	 * @param width Width of the player object
	 * @param height Height of the player object
	 * @param col Colour of the player object
	 * @param lives Number of lives the player object has
	 */
	public Player(double x, double y, int width, int height, Color col, int lives) {
		super(x, y, width, height, GameConstants.TYPE_PLAYER, col);
		
		collisionChecker = new CheckCollision();

		movement(y);		
		
		completedLevel = false;
		
		heart = new Lives(GameConstants.MAX_LIVES);
		heart.setLives(lives);
		ammo = new Ammo(GameConstants.MAX_AMMO);
		ammo.setAmmo(ammoNo);
		clock = new CountdownTimer();
		foreground.getChildren().add(clock);
		foreground.getChildren().add(heart);
		foreground.getChildren().add(ammo);

	}
	
	/**
	 * Returns the foreground objects to display on the screen
	 * for the player
	 * @return Returns the foreground pane
	 */
	public Pane getForeground() {
		return foreground;
	}
	
	/**
	 * Constantly runs, from the Game class, to check how the player
	 * object is interacting with other objects
	 * @param root Contains all objects that the player could interact with
	 */
	public void tick(Pane root) {
		if (lives == 0) {	
			setDead(true);
			
		}
		
		moveX((int)velX);
		moveY((int)velY);	
		
		setVelY(10);
		
		RectObject collidedObj = collisionChecker.checkForCollision(this, root);
		if (collisionChecker.getCollided()) {
			if (collidedObj.getType().equals(GameConstants.TYPE_PLATFORM)) {
				setVelY(0);
				setTranslateY(collidedObj.getTranslateY() - 50);
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
			}
		}
	}
	
	/**
	 * Determines what happens when the player loses a life
	 * @param root Contains all objects that the player could interact with
	 */
	public void loseLife(Pane root) {
		root.setLayoutX(0);
		if (lives > 0) {
			this.Fade();
			this.setTranslateX(200);
			this.setTranslateY(0);
			lives--;	
			heart.lostlife();
			if (lives==0)
				setDead(true);
		}else
			setDead(true);
	}
	
	/**
	 * Sets the velocity of the player in the X direction
	 * @param v Velocity value to change by
	 */
	public void setVelX(double v) {
		this.velX = v;
	}
	
	/**
	 * Sets the velocity of the player in the Y direction
	 * @param v Velocity value to change by
	 */
	public void setVelY(double v) {
		this.velY = v;
	}
	
	/**
	 * Returns the velocity of the player in the X direction
	 * @return Velocity of the player in the X direction
	 */
	public double getVelX() {
		return velX;
	}
	
	/**
	 * Returns the velocity of the player in the Y direction
	 * @return Velocity of the player in the Y direction
	 */
	public double getVelY() {
		return velY;
	}
	
	/**
	 * Determines what happens when the player presses the Jump button
	 */
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
	
	/**
	 * Uses the gravity variable to make the player fall
	 * @param y Y coordinate of the player object
	 */
	public void movement(double y) {
		if (isFalling() || isJumping()) {
			y += gravity;
		}
	}

	/**
	 * Checks whether the player object has collided with another object
	 * @param root Contains all objects that the player could interact with
	 * @return Whether the player has collided or not
	 */
	public boolean hasCollided(Pane root) {
		collisionChecker.checkForCollision(this, root);
		return collisionChecker.getCollided();
	}
	
	/**
	 * Called when the player presses the shoot button
	 * @param root Contains all objects that the player could interact with
	 */
	public void shoot(Pane root) {
		
		if (ammoNo > 0) {
			Bullet bullet = getBullet(this, Color.GREEN, root, bulletDir);
			root.getChildren().add(bullet);
			ammoNo--;
		}else {
			System.out.println("No Bullets");
			this.noBullets();
		}
		ammo.setAmmo(ammoNo);
	}
	
	/**
	 * Displays a message on the screen when the player has no bullets remaining
	 */
	private void noBullets() {	
		Image noAmmo = new Image("No Ammo.png");
        ImageView img= new ImageView(noAmmo);
        img.setX(640);
        img.setY(60);
        img.setFitHeight(35);
        img.setFitWidth(150);
		
		ft = new FadeTransition(Duration.millis(400), img);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.setCycleCount(5);
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
            foreground.getChildren().remove(img);
        }  
        
		foreground.getChildren().add(img);
		
	}
	
	/**
	 * Creates a flashing animation for certain objects, such as when
	 * the player loses a life
	 */
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
	
	/**
	 * Returns the gravity value for the player
	 * @return Gravity value for the player
	 */
	public double getGravity() {
		return gravity;
	}

	/**
	 * Returns the number of bullets the player has
	 * @return Number of bullets the player has
	 */
	public int getAmmo() {
		return ammoNo;
	}

	/**
	 * Adds ammo to the player when they pick up a supply drop
	 * @param i Number of bullets to add to the player
	 */
	public void addAmmo(int i) {
		System.out.println("OLD AMMO + " + ammoNo);
		if (ammoNo + i > GameConstants.MAX_AMMO)
			ammoNo = GameConstants.MAX_AMMO;
		else
			ammoNo += i;
		System.out.println("NEW AMMO + " + ammoNo);
		
		ammo.setAmmo(ammoNo);
		ammo.addBullets();
	}

	/**
	 * Adds lives to the player when they pick up a supply drop
	 * @param i Number of lives to add to the player
	 */
	public void addLives(int i) {
		if (lives + i > GameConstants.MAX_LIVES)
			lives = GameConstants.MAX_LIVES;
		else
			lives += i;
		
		heart.setLives(lives);
		heart.addLives();
	}
	
	/**
	 * Detects when a user presses a button and decides what to do
	 * in each case
	 * @param game Main game object
	 * @param s Scene object to display all the objects
	 * @param sppl The sprite player object that displays the player animation
	 */
	public void buttonPressing(Game game, Scene s, SpritePlayer sppl) {
		s.setOnKeyPressed(e -> {
			switch (e.getCode()) {
			case A:
				setVelX(-5);
				game.root.setLayoutX(game.root.getLayoutX() + 10);
				sppl.flipbackwards();
				bulletDir = false;
				break;
			case D:
				setVelX(5);
				game.root.setLayoutX(game.root.getLayoutX() - 10);
				sppl.flipforwards();
				bulletDir = true;
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
				ammo.lostBullet();
				shoot(game.root);
				break;
			default:
				break;
			}
		});
	}

	/**
	 * Detects when a user releases a button and decides what to do
	 * @param s Scene object to display all the objects
	 */
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
			default:
				break;
			}
			
		});
		
		
	}

	/**
	 * Moves the screen so that the player can always be seen
	 * @param game Main game object
	 */
	public void checkPos(Game game) {
		double x =getXLocation();
		if (x>400) {
			game.root.setLayoutX(game.root.getTranslateX()-(x-400));
		}
		
	}
	
	/**
	 * Returns whether the player has finished the current level
	 * @return Whether the player has finished the current level
	 */
	public boolean getLevelFinish() {
		return completedLevel;
	}

	/**
	 * Returns the clock object in the foreground
	 * @return Clock object in the foreground
	 */
	public CountdownTimer getTimer() {
		return clock;
	}

}
