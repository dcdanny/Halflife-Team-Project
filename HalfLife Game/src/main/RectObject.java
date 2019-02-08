package main;

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
	private FadeTransition ft;
    private Rectangle rect;

	boolean dead= false;
	final String type;
	RectObject(int x, int y,int width, int height, String type, Color col){
		super (width,height,col);
		this.type =type;
	 	setTranslateX(x);
		setTranslateY(y);
		playerFade();
		
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
	void moveLeft() {
		setTranslateX(getTranslateX()-5); // you could change this, idk try implement speed? rather than straight translate
	}
	void moveRight() {
		setTranslateX(getTranslateX()+5); // you could change this, idk try implement speed? rather than straight translate
	}
	//respawn animation when a player dies and respawns
	void playerFade() {
		if (type.equals("player")) {
			
			
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

//moving up and down will need to apply physics so its not included but we could try move up down for moving platforms (later)
}
