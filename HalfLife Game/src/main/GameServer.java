package main;

import network.Message;
import network.Server;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import com.halflife.enemies.*;
import com.halflife.entities.*;

public class GameServer extends Thread {
	private Pane root= new Pane();
	
	public GameServer() {
		
		
		
		System.out.println("Start server...");
		
		//Can't use 0 - 1023, Use 1024 - 65 535
		final int port = 1035;
		System.out.println("port: "+port);
		Server server = new Server(port);
		server.start();
		//launch(args);
		/*
		Message messagetoSend = new Message(namefrom,msgcontent);
		
		System.out.println("----- ----- ----- -----");
		server.sendToAll(messagetoSend);*/
		server.stopServer();
	}
	public void run() {
	
	}
	
	public static ArrayList<Node> getAllNodes(Parent root) {
	    ArrayList<Node> nodes = new ArrayList<Node>();
	    addAllDescendents(root, nodes);
	    return nodes;
	}

	private static void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
	    for (Node node : parent.getChildrenUnmodifiable()) {
	    	
	    	if (node instanceof RectObject) {
	        nodes.add((RectObject) node);
	        if (node instanceof Parent)
	            addAllDescendents((Parent)node, nodes);
	    	}
	    }
	}
	
	// Game loop variables
	long lastTime = System.nanoTime();
	final double numberOfTicks = 60.0;
	double ns = 1000000000 / numberOfTicks;
	double delta = 0;
	int updates = 0;
	int frames = 0;
	long timer = System.currentTimeMillis();
	private void update() {
		long now = System.nanoTime();
		delta += (now - lastTime) / ns;
		lastTime = now;
		
		if (delta >= 1) {
			tick();
			updates++;
			delta--;
		}
		
		frames++;
		
		if (System.currentTimeMillis() - timer > 1000) {
			timer += 1000;
			System.out.println(updates + " Ticks, Fps " + frames);
			updates = 0;
			frames = 0;
		}
		

		for (Node object : getAllNodes(root)) {
			RectObject newObj = (RectObject) object;
			if (newObj.isDead())
				root.getChildren().remove(newObj);
		}	
	}
	
	private void tick() {
		//boolean deathScreenDisplayed = false;
		player.tick(root, heart);


		for (BaseEnemy enemy : enemies) {
			enemy.tick(player, root);
		}
		for (Spike spike : spikes) {
			spike.tick(player, root);
		}
		
		checkPos();
		if (player.isDead() && !foreground.getChildren().contains(DeathShow)) {
			foreground.getChildren().add(DeathShow);
//			deathScreenDisplayed = true;
		}
	}
	
}


