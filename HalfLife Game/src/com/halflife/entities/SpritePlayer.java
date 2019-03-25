package com.halflife.entities;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import main.SpriteAnimation;

/**
 * Adds a sprite animation on top of a player object
 * @author Halflife
 */
public class SpritePlayer extends Pane {
	
	private Player pl; //Player object that is covered with the sprite
	private SpriteAnimation ani; //Sprite animation
	private boolean forward; //Direction the sprite is facing
	
	/**
	 * Constructor of the sprite player
	 */
	public SpritePlayer(int lvlNum) {
		this.forward=true;
		this.pl=  new Player(200,0,40,50,Color.PINK,3, lvlNum);
		this.ani=new SpriteAnimation("player");
		ani.resizeView(-38, -45, 120);
		ani.translateXProperty().bindBidirectional(pl.translateXProperty());
		ani.translateYProperty().bindBidirectional(pl.translateYProperty());
		
		Pane PlayerSprite= new Pane();
		PlayerSprite.getChildren().addAll(ani);
		this.getChildren().add(PlayerSprite);
	}
	
	/**
	 * Returns the player object that the sprite animation is on top of
	 * @return Player object that the sprite animation is on top of
	 */
	public Player GetPlayer() {
		return pl;
	}
	
	/**
	 * Flips the animation for when moving in the opposite direction
	 */
	public void flipbackwards() {
		if (forward) {
			ani.flip();
		}
		forward=false;
	}
	
	/**
	 * Flips the animation back to it's original when moving in the normal direction
	 */
	public void flipforwards() {
		if (!forward) {
			ani.flipnorm();
		}
		forward=true;
	}
	public SpriteAnimation GetAnimation() {
		return ani;
	}
}

