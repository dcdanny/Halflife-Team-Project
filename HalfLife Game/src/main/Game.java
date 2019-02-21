  package main;



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

import com.halflife.enemies.BaseEnemy;
import com.halflife.enemies.SpikePlatform;
import com.halflife.entities.*;
import main.CheckCollision;


public class Game extends Application {
	private Pane root= new Pane();
	private Pane foreground=new Pane();
	private Pane display=new Pane();
	private StackPane DeathShow=new DeathScreen();
	//private RectObject player=new RectObject(500,300,40,50,"player",Color.WHITE);

	private Player player= new Player(500,300,40,50,Color.WHITE,3);
	private BaseEnemy enemy = new BaseEnemy(600,300,40,50,"enemy",Color.RED);
	private SpikePlatform sp = new SpikePlatform(400,500,30,30,"sp",Color.LIGHTSKYBLUE);
	private CountdownTimer clock=new CountdownTimer();
	private Lives heart = new Lives();
	private Ammo ammo = new Ammo();
	private ArrayList<Node> platforms=new ArrayList<Node>();
	private int levelWidth;
	
	private Parent createContent() {
		RectObject bg=new RectObject(0,0,800,600,"background",Color.valueOf("#4f7b8a"));
		//root.setPrefSize(800, 600);
		root.getChildren().add(player);
		root.getChildren().add(enemy);
		foreground.getChildren().add(clock);
		foreground.getChildren().add(heart);
		foreground.getChildren().add(ammo);
		root.getChildren().add(sp);
		root.getChildren().add(sp.getSpike());
		
		//root.getChildren().add(enemy1);
		//root.getChildren().add(spike1);
		//root.setStyle("-fx-background-color: #4f7b8a;");
		
		//root.getChildren().add(DeathShow);
		
		//display.getChildren().addAll(root);
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				update();
			}
		};
		
		timer.start();
		display.getChildren().addAll(bg,root,foreground);
		
		//foreground.getChildren().add(DeathShow);
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
	

	
	public void checkCollision(Shape block) {
		  boolean isCollided = false;
		  for (Node static_bloc : getAllNodes(root)) {
		    if (static_bloc != block) {
		      ((Shape) static_bloc).setFill(Color.GREEN);

		      if (block.getBoundsInParent().intersects(static_bloc.getBoundsInParent())) {
		    	  isCollided = true;
		      }
		    }
		  }

		  if (isCollided) {
		    block.setFill(Color.RED);
		    
		  } else {
		    block.setFill(Color.WHITE);
		  }
	}
	
	

	private void tick() {
		player.tick();
		CheckCollision.checkForCollision(player, root);
		if (CheckCollision.getCollided()) {
			
		}
		
		
		
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setResizable(false);
		setUpLevel();
		createContent();
		stage.setTitle("GAME NAME HERE");
		Scene scene = new Scene(display);
		stage.setScene(scene);
		
		buttonPressing(scene);
		buttonReleasing(scene);
		
		stage.show();
		
		
	}
	
	
	private void buttonPressing(Scene s) {
	
		s.setOnKeyPressed(e-> {
			switch (e.getCode()) {
			case A:
				player.setVelX(-5);
				root.setLayoutX(root.getLayoutX()+10);
				break;
			case D: 
				player.setVelX(5);
				root.setLayoutX(root.getLayoutX()-10);
				root.setStyle("-fx-background-color: #4f7b8a;");
				break;
			case S: 
				player.setVelY(5);
				break;
			case W:
				if (player.getGravity() == 0) {
				player.jump();
				}
				break;
			case SPACE:
				ammo.lostBullet();
				player.shoot(root);
				break;
			}
			
		});
		
	}
	
	private void buttonReleasing(Scene s) {
		
		s.setOnKeyReleased(e-> {
			switch (e.getCode()) {
			case A:
				player.setVelX(0);
				break;
			case D: 
				player.setVelX(0);
				break;
			case S: 
				player.setVelY(0);
				break;
			case W:
				break;
			}
			
		});
		
	}
	
	private void setUpLevel() {
		levelWidth= Level_Info.LEVEL1[0].length()*150;
	
		
		
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
	/*public static void main(String[] args) {
		launch(args);
				
	}*/
}
