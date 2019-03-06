  package main;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import com.halflife.enemies.*;
import com.halflife.entities.*;


import main.CheckCollision;
//IF YOU WANT TO TEST WITH THE SPRITE go to the start method and comment out where necessary

public class GameClient extends Application {
	public Pane root= new Pane();
	private Pane foreground=new Pane();
	private Pane display=new Pane();
	private StackPane DeathShow=new DeathScreen();

	private Player player= new Player(200,0,40,50,Color.WHITE,3);
	private SpritePlayer spplayer= new SpritePlayer();
	private List<BaseEnemy> enemies = new ArrayList<BaseEnemy>();
	private List<Spike> spikes = new ArrayList<Spike>();

	private CountdownTimer clock=new CountdownTimer();
	private Lives heart = new Lives();
	public Ammo ammo = new Ammo();
	private ArrayList<Node> platforms=new ArrayList<Node>();
	private int levelWidth;

	private SpriteAnimation sp= new SpriteAnimation();

	private String[] currentLevel;

	
	public void setCurrentLevel(String[] currentLevel) {
		this.currentLevel = currentLevel;
	}

	private Parent createContent() throws IOException {
		RectObject bg=new RectObject(0,0,800,600,GameConstants.TYPE_BACKGROUND,Color.valueOf("#4f7b8a"));

		root.setPrefSize(800, 600);
		root.getChildren().add(player);

		foreground.getChildren().add(clock);
		foreground.getChildren().add(heart);
		foreground.getChildren().add(ammo);

		root.getChildren().add(spplayer);

		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				//update();
			}
		};
		
		timer.start();
		display.getChildren().addAll(bg,root,foreground);
		

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
	
	
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setResizable(false);
		
		createContent();
		stage.setTitle("HALFLIFE");
		Scene scene = new Scene(display);
		stage.setScene(scene);
	//	spplayer.buttonPressing(this, scene);
		//spplayer.buttonReleasing(scene);
		
		//IF YOU WANT THE SPRITE UNCOMMENT THE ABOVE AND COMMENT OUT THE BELOW
//		player.buttonPressing(this, scene); 
//		player.buttonReleasing(scene);
		
		stage.show();
		
		
	}
		
	
	public static void main(String[] args) {
		launch(args);
				
	}
}
