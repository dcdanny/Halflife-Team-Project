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
import javafx.scene.layout.Pane;

public class RectObject extends Rectangle implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2072511921833351490L;
	protected FadeTransition ft;
    protected Rectangle rect;

    public float gravity = 0.5f;
	private boolean falling = true;
	private boolean jumping = false;
    
	protected boolean dead= false;
	private final String type;
	private double x,y,w,h;
	private Color col;

	
	public RectObject(double x, double y,int width, int height, String type, Color col){
		super (width,height,col);
		this.type =type;
		this.x=x;
		this.y=y;
		this.w=width;
		this.h=height;
		this.col=col;

		
		//playerFade();

	 	setTranslateX(x);
		setTranslateY(y);

		

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
	

	public Bullet getBullet(RectObject shooter, Color colour, Pane root, boolean bulletDir) {
		int moveBulletX = 0;
		if (bulletDir == true) {
			moveBulletX = 50;
		} else {
			moveBulletX = -30;
		}
		Bullet bullet = new Bullet(shooter.getTranslateX() + moveBulletX, shooter.getTranslateY(), 20, 5, shooter.getType() + "bullet", Color.GREEN, root, bulletDir);
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
	public double getXLocation() {
		return this.getTranslateX();
	}
	
	@Override
	public String toString() {
		return "RectObject x: "+x+ " y: "+ y +" w: "+ w +" h: " + h;
		
	}
	

	
//moving up and down will need to apply physics so its not included but we could try move up down for moving platforms (later)
}
