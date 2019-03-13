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
	
	public SpritePlayer() {
		 this.pl=  new Player(200,0,40,50,Color.WHITE,3);
		//this.getChildren().add(pl);
		this.ani=new SpriteAnimation();
		ani.resizeView(-38, 0, 120);
		System.out.println(pl.translateXProperty());
		System.out.println(ani.translateXProperty());
		ani.translateXProperty().bindBidirectional(pl.translateXProperty());
		//this.getChildren().add(ani);
		Pane PlayerSprite= new Pane();
		PlayerSprite.getChildren().addAll(pl,ani);
		this.getChildren().add(PlayerSprite);
	}
	
	public Player GetPlayer() {
		return pl;
	}
}

