package com.halflife.enemies;

import com.halflife.entities.Player;
import com.halflife.entities.RectObject;

import javafx.scene.paint.Color;
import main.Mathematics;

public class BaseEnemy extends RectObject {
	
	private boolean startMoving;

	private double velX = 0;
	private double velY = 0;
	
	public BaseEnemy(int x, int y, int width, int height) {
		
		super(x, y, width, height, "enemy", Color.RED);		
		// TODO Auto-generated constructor stub
		startMoving = false;
	}
	
	public void tick(Player player) {
		velX = 0;
		if (isCollided(player)) {
			System.out.println("afjisdfsd");
		}
		if (isNear(player)) {
			if (Mathematics.getDistanceX(this, player)< 0)
				velX = 2;
			else if (Mathematics.getDistanceX(this, player)> 0)
				velX = -2;
		}
		moveX((int)velX);
		//moveY((int)velY);	
	}
	
	public void PlayerNear(Player player) {
		// detection for if the player comes within x pixels
		
		startMoving = true;
		while(isNear(player) && !isDead()) {
			this.setVelX(5);
		}
	}
	
	public boolean isNear(Player player) {
		if (Mathematics.getDistanceX(this, player) < 400 && Mathematics.getDistanceY(this, player) < 30) {
			return true;
		}
		return false;
	}

	public boolean isCollided(Player player)
	{
		if(this.getBoundsInParent().intersects(player.getBoundsInParent()))
			return true;
		return false;
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
	
}
