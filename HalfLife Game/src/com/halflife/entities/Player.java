package com.halflife.entities;

import java.awt.event.KeyAdapter;

import javafx.scene.input.KeyCode;

public class Player extends KeyAdapter {
	
	
	
	// This will detect when a keyboard button is pressed, which controls the player.
	public void keyPressed(KeyCode key) {
		
		if (key == KeyCode.W) {
		 	System.out.println("Pressed W");
		} 
		else if (key == KeyCode.S) {
			System.out.println("Pressed S");
		} 
		else if (key == KeyCode.A) {
			System.out.println("Pressed A");
		}
		else if (key == KeyCode.D) {
			System.out.println("Pressed D");
		}	
	}
	
	// This will detect when a keyboard button has been released.
	public void keyReleased(KeyCode key) {
		if (key == KeyCode.W) {
			//STOP DOING SOMETHING
		}
	}
}
