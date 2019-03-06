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
import com.sun.corba.se.spi.activation.Server;

import main.CheckCollision;
import network.ClientTable;
//IF YOU WANT TO TEST WITH THE SPRITE go to the start method and comment out where necessary

public class Game extends Application {
	public Pane root= new Pane();
	private Pane foreground=new Pane();
	private Pane display=new Pane();
	private StackPane DeathShow=new DeathScreen();
	//private RectObject player=new RectObject(500,300,40,50,"player",Color.WHITE);

	private Player player= new Player(200,0,40,50,Color.WHITE,3);
	private SpritePlayer spplayer= new SpritePlayer();
	private List<BaseEnemy> enemies = new ArrayList<BaseEnemy>();
	private List<Spike> spikes = new ArrayList<Spike>();
	private List<NetworkedPlayer> netPlayers = new ArrayList<NetworkedPlayer>();
	private CountdownTimer clock=new CountdownTimer();
	private Lives heart = new Lives();
	public Ammo ammo = new Ammo();
	private ArrayList<Node> platforms=new ArrayList<Node>();
	private int levelWidth;
	private ClientTable cTable = new ClientTable();

	private String[] currentLevel;

	private SpriteAnimation sp= new SpriteAnimation();

	
	public void setCurrentLevel(String[] currentLevel) {
		this.currentLevel = currentLevel;
	}

	private Parent createContent() throws IOException {
		RectObject bg=new RectObject(0,0,800,600,GameConstants.TYPE_BACKGROUND,Color.valueOf("#4f7b8a"));

		root.setPrefSize(800, 600);
		//root.getChildren().add(player);
		//root.getChildren().add(enemy);
		foreground.getChildren().add(clock);
		foreground.getChildren().add(heart);
		foreground.getChildren().add(ammo);

		root.getChildren().add(spplayer);

		//root.getChildren().add(sp.getSpike());
		
		//root.getChildren().add(enemy1);
		//root.getChildren().add(spike1);
	
	    	//root.getChildren().add(ani);
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
		spplayer.tick(root, heart);
		
		for (BaseEnemy enemy : enemies) {
			enemy.tick(player, root);
		}
		for (Spike spike : spikes) {
			spike.tick(player, root);
		}
		
		player.checkPos(this);
		if (player.isDead() && !foreground.getChildren().contains(DeathShow)) {
			foreground.getChildren().add(DeathShow);
//			deathScreenDisplayed = true;
		}
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		for (String string : currentLevel) {
			
		}
		
		stage.setResizable(false);
		setUpLevel(Level_Info.LEVEL2);
		createContent();
		stage.setTitle("HALFLIFE");
		Scene scene = new Scene(display);
		stage.setScene(scene);
	//	spplayer.buttonPressing(this, scene);
		//spplayer.buttonReleasing(scene);
		
		//IF YOU WANT THE SPRITE UNCOMMENT THE ABOVE AND COMMENT OUT THE BELOW
		player.buttonPressing(this, scene); 
		player.buttonReleasing(scene);
		
		stage.show();
		
		
	}
		
	
	private void setUpLevel(String[] lvl) {
		levelWidth= lvl[0].length()*150;
		
		
		root.getChildren().add(player);
		
		for (NetworkedPlayer np : netPlayers) {
			// 
			root.getChildren().add(np);
		}
	
		
		
		for (int i = 0; i < lvl.length; i++) {
			String line=lvl[i];
			for (int j = 0; j < line.length(); j++) {
				switch(line.charAt(j)) {
				case '0':
					Node edgePlatR =new RectObject(j*150,i*100,1,1,GameConstants.TYPE_EDGE_PLATFORM_RIGHT,Color.valueOf("#4f7b8a"));
					edgePlatR.setTranslateX(edgePlatR.getTranslateX()+1);
					root.getChildren().add(edgePlatR);
					platforms.add(edgePlatR);
					Node edgePlatL =new RectObject(j*150,i*100,1,1,GameConstants.TYPE_EDGE_PLATFORM_LEFT,Color.valueOf("#4f7b8a"));
					edgePlatL.setTranslateX(edgePlatL.getTranslateX()+148);
					root.getChildren().add(edgePlatL);
					platforms.add(edgePlatL);
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
