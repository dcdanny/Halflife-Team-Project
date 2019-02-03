package com.halflife.entities;

import java.awt.event.*;

import javafx.scene.input.KeyCode;

public class Player extends KeyAdapter {
	
	// Players x and y coordinates.
	private double x;
	private double y;

	public Player(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	// This will detect when a keyboard button is pressed, which controls the player.
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_W) {
			jump();
		} else if (key == KeyEvent.VK_A) {
			x = x - 5;
		} else if (key == KeyEvent.VK_S) {
			crouch();
		} else if (key == KeyEvent.VK_D) {
			x = x + 5;
		} 
	 
		
		
	}
	
	// This will detect when a keyboard button has been released.
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
	}
	
	private void jump() {
		
	}
	
	private void crouch() {
		
	}
}
