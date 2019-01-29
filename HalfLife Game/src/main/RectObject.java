package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class RectObject extends Rectangle {
	boolean dead= false;
	final String type;
	RectObject(int x, int y,int width, int height, String type, Color col){
		super (width,height,col);
		this.type =type;
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
	void moveLeft() {
		setTranslateX(getTranslateX()-5); // you could change this, idk try implement speed? rather than straight translate
	}
	void moveRight() {
		setTranslateX(getTranslateX()+5); // you could change this, idk try implement speed? rather than straight translate
	}

//moving up and down will need to apply physics so its not included but we could try move up down for moving platforms (later)
}
