package com.halflife.entities;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import main.CheckCollision;
import main.GameConstants;
import main.Mathematics;

public class SupplyDrop extends RectObject{

	
	
	private CheckCollision collisionChecker;
	private int ammoContents;
	private int livesContents;
	
	public SupplyDrop(double d, double e, int width, int height) {
		super(d, e, width, height, GameConstants.TYPE_SUPPLY_DROP, Color.INDIGO);
		collisionChecker = new CheckCollision();
		generateContents();
	}
	
	public void generateContents() {
		Random r = new Random();
		ammoContents = r.nextInt(10);
		livesContents = r.nextInt(2);
	}

	public void tick(Player player, Pane root) {
		if (isCollidedWithPlayer(player) && ammoContents != 0 && livesContents != 0) {
			player.addAmmo(ammoContents);
			player.addLives(livesContents);
			System.out.println(ammoContents + " - " + livesContents);
			this.setDead(true);
			
		} else {
			this.dead = false;
		}
	}
	

	public boolean isCollidedWithPlayer(Player player)
	{
		if(this.getBoundsInParent().intersects(player.getBoundsInParent()))
			return true;
		return false;
	}
	
	public boolean checkCollided(Pane root) {
	    collisionChecker.checkForCollision(this, root);
		return collisionChecker.getCollided();
	}
	
	@Override
	public void setDead(boolean t) {
		this.dead = t;
		ammoContents = 0;
		livesContents = 0;
	}
	
}
