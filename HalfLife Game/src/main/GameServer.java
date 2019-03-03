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

public class GameServer {
	public static void main(String[] args) {
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
	
	
}


