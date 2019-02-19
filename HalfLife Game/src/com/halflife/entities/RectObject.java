package com.halflife.entities;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import javax.swing.plaf.synth.SynthSplitPaneUI;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class RectObject extends Rectangle {
	protected FadeTransition ft;
    protected Rectangle rect;

    public float gravity = 0.5f;
	private boolean falling = true;
	private boolean jumping = false;
    
	private boolean dead= false;
	private final String type;
	public RectObject(double d, double e,int width, int height, String type, Color col){
		super (width,height,col);
		this.type =type;

		
		//playerFade();

	 	setTranslateX(d);
		setTranslateY(e);

		

		/* Ignore for now, i was testing using images on top of shapes
		 Image player = null;
		try {
			player = new Image(new FileInputStream("res/ghostchar.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 this.setFill(new ImagePattern(player, 0, 0, 50, 50, false));
		*/
	}
	public void moveX(int i) {
		setTranslateX(getTranslateX()+i); // you could change this, idk try implement speed? rather than straight translate
	}
	public void moveY(int i) {
		setTranslateY(getTranslateY()+i);	
	}
	

	public RectObject getBullet(RectObject shooter, Color colour) {
		RectObject bullet = new RectObject(shooter.getTranslateX() + 20, shooter.getTranslateY(), 20, 5, shooter.getType() + "bullet", colour);
		return bullet;
	}
	public String getType() {
		return type;
	}
	public boolean isDead() {
		return dead;
	}
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	public boolean isFalling() {
		return falling;
	}
	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	public boolean isJumping() {
		return jumping;
	}
	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public double getYLocation() {
		return this.getTranslateY();
	}

	
//moving up and down will need to apply physics so its not included but we could try move up down for moving platforms (later)
}
