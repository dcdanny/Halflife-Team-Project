package main;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectObject extends Rectangle {
	boolean dead= false;
	final String type;
	RectObject(int x, int y,int width, int height, String type, Color col){
		super (width,height,col);
		this.type=type;
		setTranslateX(x);
		setTranslateY(y);
		
	}
	void moveLeft() {
		setTranslateX(getTranslateX()-5); // you could change this, idk try implement speed? rather than straight translate
	}
	void moveRight() {
		setTranslateX(getTranslateX()+5); // you could change this, idk try implement speed? rather than straight translate
	}

//moving up and down will need to apply physics so its not included but we could try move up down for moving platforms (later)
}
