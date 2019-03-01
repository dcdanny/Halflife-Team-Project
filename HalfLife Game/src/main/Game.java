  package main;



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
import main.CheckCollision;


public class Game extends Application {
	private Pane root= new Pane();
	private Pane foreground=new Pane();
	private Pane display=new Pane();
	private StackPane DeathShow=new DeathScreen();
	//private RectObject player=new RectObject(500,300,40,50,"player",Color.WHITE);

	private Player player= new Player(200,0,40,50,Color.WHITE,3);
	private List<BaseEnemy> enemies = new ArrayList<BaseEnemy>();
	private List<Spike> spikes = new ArrayList<Spike>();
	//private BaseEnemy enemy = new BaseEnemy(600,300,40,50,"enemy",Color.RED);
	//private SpikePlatform sp = new SpikePlatform(400,400,30,30,"sp",Color.LIGHTSKYBLUE);
	private CountdownTimer clock=new CountdownTimer();
	private Lives heart = new Lives();
	private Ammo ammo = new Ammo();
	private ArrayList<Node> platforms=new ArrayList<Node>();
	private int levelWidth;
	
	private Parent createContent() throws IOException {
		RectObject bg=new RectObject(0,0,800,600,GameConstants.TYPE_BACKGROUND,Color.valueOf("#4f7b8a"));
		root.setPrefSize(800, 600);
		root.getChildren().add(player);
		//root.getChildren().add(enemy);
		foreground.getChildren().add(clock);
		foreground.getChildren().add(heart);
		foreground.getChildren().add(ammo);
		//root.getChildren().add(sp);
		//root.getChildren().add(sp.getSpike());
		
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
	
	private void checkPos() {
		double x =player.getXLocation();
		if (x>400) {
			root.setLayoutX(root.getTranslateX()-(x-400));
		}
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setResizable(false);
		setUpLevel();
		createContent();
		stage.setTitle("HALFLIFE");
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
				if (player.getGravity() == 0 && player.hasCollided(root)) {
					player.setTranslateY(player.getTranslateY() - 10);
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
					Node edgePlat =new RectObject(j*150,i*100,1,1,GameConstants.TYPE_EDGE_PLATFORM,Color.valueOf("#4f7b8a"));
					edgePlat.setTranslateX(edgePlat.getTranslateX()+1);
					root.getChildren().add(edgePlat);
					platforms.add(edgePlat);
					break;
				case '1':
				Node platform =new RectObject(j*150,i*100,150,10,GameConstants.TYPE_PLATFORM,Color.LIGHTSKYBLUE);
				root.getChildren().add(platform);
				platforms.add(platform);
				break; 
				case '2':
					Node gPlatform =new GoalPlatform(j*150,i*100,150,30);
					root.getChildren().add(gPlatform);
					platforms.add(gPlatform);
					break;
				case '3': 
					Node floor;
					if (j==1) {
						 floor =new Floor(j*150,i*120,400,10);
						 floor.setTranslateX(0);
					}
					else {
						 floor =new Floor(j*150,i*120,150,10);
					}					
					
					root.getChildren().add(floor);
					platforms.add(floor);
					break;
				case '4': 
					Node wall =new Wall(j*150,i*150,25,150);
					root.getChildren().add(wall);
					platforms.add(wall);
					break;
				case '5':
					platform =new RectObject(j*150,i*100,150,10,GameConstants.TYPE_PLATFORM,Color.LIGHTSKYBLUE);
					root.getChildren().add(platform);
					platforms.add(platform);
					Node bEnemy = new BaseEnemy(j*150,i*100-30,30,30);
					bEnemy.setTranslateX(bEnemy.getTranslateX()+120);
					root.getChildren().add(bEnemy);
					enemies.add((BaseEnemy) bEnemy);
					break;
				case '6':
					platform =new RectObject(j*150,i*100,150,10,GameConstants.TYPE_PLATFORM,Color.LIGHTSKYBLUE);
					root.getChildren().add(platform);
					platforms.add(platform);
					SpikePlatform sPlatform =new SpikePlatform(j*150,i*100,30,10);
					root.getChildren().add(sPlatform.getSpike());
					spikes.add(sPlatform.getSpike());
					break;
				}
			}
		}	
		
	}
	public static void main(String[] args) {
		launch(args);
				
	}
}
