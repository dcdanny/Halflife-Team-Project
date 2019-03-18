package com.halflife.entities;

import java.io.IOException;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import main.Ammo;
import main.CheckCollision;
import main.CountdownTimer;
import main.Game;
import main.GameConstants;
import main.Lives;
import main.SpriteAnimation;
import main.WriteFile;


public class SpritePlayer extends Pane {
	Player pl;
	SpriteAnimation ani;
	boolean forward;
	public SpritePlayer() {
		this.forward=true;
		 this.pl=  new Player(200,0,40,50,Color.PINK,3);
		//this.getChildren().add(pl);
		this.ani=new SpriteAnimation();
		ani.resizeView(-38, -45, 120);
		//System.out.println(pl.translateXProperty());
		//System.out.println(ani.translateXProperty());
		ani.translateXProperty().bindBidirectional(pl.translateXProperty());
		ani.translateYProperty().bindBidirectional(pl.translateYProperty());
		//this.getChildren().add(ani);
		Pane PlayerSprite= new Pane();
		PlayerSprite.getChildren().addAll(ani);
		this.getChildren().add(PlayerSprite);
	}
	
	public Player GetPlayer() {
		return pl;
	}
	public void flipbackwards() {
		if (forward) {
			ani.flip();
		}
		forward=false;
	}
	public void flipforwards() {
		if (!forward) {
			ani.flipnorm();
		}
		forward=true;
	}
}

