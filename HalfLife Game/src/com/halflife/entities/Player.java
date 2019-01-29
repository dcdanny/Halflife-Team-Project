package com.halflife.entities;

import javafx.scene.input.KeyCode;

public class Player {
	
	
	
	public void keyPressed(KeyCode key) {
		if (key == KeyCode.W) {
			//DO SOMETHING
			System.out.println("Pressed W");
		}
			
	}
	
	public void keyReleased(KeyCode key) {
		if (key == KeyCode.W) {
			//STOP DOING SOMETHING
		}
	}
}
