package com.halflife.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import main.SpriteAnimation;

public class Character extends Pane{
	//Configure sprite animation
	ImageView imageView;
	int count=2;
	int columns=2;
	int offsetX=0;
	int offsetY=0;
	int width=50;
	int height=50;
	
	public Character(ImageView img) {
		this.imageView=imageView;
		this.imageView.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
		SpriteAnimation ani=new SpriteAnimation(imageView,Duration.millis(200), columns, count, offsetX, offsetY, width, height);
		getChildren().addAll(imageView);
		
	}
	

}
