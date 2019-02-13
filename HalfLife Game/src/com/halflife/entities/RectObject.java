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

    public boolean movingLeft = false; 
    public boolean movingRight = false;
    
	private boolean dead= false;
	private final String type;
	public RectObject(double d, double e,int width, int height, String type, Color col){
		super (width,height,col);
		this.type =type;
	 	setTranslateX(d);
		setTranslateY(e);
		Fade();
		
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
	public void moveLeft(int i) {
		setTranslateX(getTranslateX()-i); // you could change this, idk try implement speed? rather than straight translate
	}
	public void moveRight(int i) {
		setTranslateX(getTranslateX()+i); // you could change this, idk try implement speed? rather than straight translate
	}
	public void resetMovement() {
		movingLeft = false;
		movingRight = false;
	}
	
	//respawn animation when a player dies and respawns
	public void Fade() {
		//Overriding in Player class
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
	
//moving up and down will need to apply physics so its not included but we could try move up down for moving platforms (later)
}
