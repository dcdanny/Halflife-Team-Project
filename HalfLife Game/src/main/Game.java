  package main;



import java.util.ArrayList;

import com.halflife.entities.RectObject;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.halflife.entities.*;


public class Game extends Application {
	private Pane root= new Pane();
	private Player player=new Player(500,300,40,50,Color.WHITE);
	private CountdownTimer clock=new CountdownTimer();
	private Lives heart =new Lives();
	private ArrayList<Node> platforms=new ArrayList<Node>();
	private int levelWidth;
	
	private Parent createContent() {
		root.setPrefSize(800, 600);
		root.getChildren().add(player);
		root.getChildren().add(clock);
		root.getChildren().add(heart);
		root.setStyle("-fx-background-color: #4f7b8a;");
		
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				update();
			}
		};
		
		timer.start();
		
		return root;	
	}
	
	private void update() {
		if (player.movingLeft == true)
			player.moveLeft();
		else if (player.movingRight == true)
			player.moveRight();
		
		player.resetMovement();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		setUpLevel();
		stage.setTitle("Gaaaaaame is Here!! ");
		Scene scene =new Scene(createContent());
		stage.setScene(scene);
		
		scene.setOnKeyPressed(e-> {
			switch (e.getCode()) {
			case A:
				player.movingRight = false;
				player.movingLeft = true;
				break;
			case D: 
				player.movingLeft = false;
				player.movingRight = true;
				break;
			}
		});
		
		stage.show();
		
	}
	private void setUpLevel() {
		levelWidth= Level_Info.LEVEL1[0].length()*60;
		
		for (int i = 0; i < Level_Info.LEVEL1.length; i++) {
			String line=Level_Info.LEVEL1[i];
			for (int j = 0; j < line.length(); j++) {
				switch(line.charAt(j)) {
				case '0':
					break;
				case '1':
				Node platform =new RectObject(j*150,i*150,150,30,"plat",Color.LIGHTSKYBLUE);
				root.getChildren().add(platform);
				platforms.add(platform);
				break; 
					
				}
			}
		}
		
	}
	public static void main(String[] args) {
		launch(args);
		
	}
}
