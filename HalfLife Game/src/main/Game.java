  package main;



import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
	
	private void update() {

		if (player.movingLeft)
			player.moveLeft(5);
		else if (player.movingRight)
			player.moveRight(5);
		
		player.resetMovement();

		for (Node object : getAllNodes(root)) {
			RectObject newObj = (RectObject) object;
			if (newObj.getType().equals("playerbullet")) {
				newObj.moveRight(5);
			}
		}
	}
	
	public void shoot(RectObject shooter) {
		RectObject bullet = player.getBullet(player, Color.GREEN);
		System.out.println(bullet.getType());
		root.getChildren().add(bullet);
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
			case SPACE:
				shoot(player);
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
