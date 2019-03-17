  package main;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import network.*;
//IF YOU WANT TO TEST WITH THE SPRITE go to the start method and comment out where necessary

public class GameClient extends Application {
	public Pane root= new Pane();
	private Pane display=new Pane();
	private StackPane DeathShow=new DeathScreen();
	//private RectObject player=new RectObject(500,300,40,50,"player",Color.WHITE);
	private NetworkedPlayer player= new NetworkedPlayer(200,0,40,50,Color.WHITE,3);

	private SpritePlayer spplayer= new SpritePlayer();
	private List<BaseEnemy> enemies = new ArrayList<BaseEnemy>();
	private List<SupplyDrop> supplies = new ArrayList<SupplyDrop>();
	private List<Spike> spikes = new ArrayList<Spike>();
	private List<NetworkedPlayer> netPlayers = new ArrayList<NetworkedPlayer>();
	private ArrayList<Node> platforms=new ArrayList<Node>();
	private int levelWidth;
	private String[] s = new String[ClientTable.size()];
	private Client client;
	private Message coords;
	private NetworkedPlayer tempNP;



	private String[] currentLevel = Level_Info.LEVEL2;

//	private SpriteAnimation sp= new SpriteAnimation();
	
	public GameClient(Client client) {
		this.client=client;
	}

	public void setCurrentLevel(String[] currentLevel) {
		this.currentLevel = currentLevel;
	}

	private Parent createContent() throws IOException {
		RectObject bg=new RectObject(0,0,800,600,GameConstants.TYPE_BACKGROUND,Color.valueOf("#4f7b8a"));

		root.setPrefSize(800, 600);
		root.getChildren().add(spplayer);
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				update();
			}
		};
		
		timer.start();
		display.getChildren().addAll(bg,root);
		
		
//		s = network.Server.showConnected();
//		Arrays.sort(s);
//		for (int i = 0; i <s.length-2;i++) {
//			System.out.println(s[i]);
//			NetworkedPlayer temp = new NetworkedPlayer(200,0,40,50,Color.GREEN,3);
//			netPlayers.add(temp);
//		}
//
//		for (NetworkedPlayer np : netPlayers) {
//			root.getChildren().add(np);
//		}		
		
//		netPlayers.add
		tempNP = new NetworkedPlayer(200, 0, 40, 50, Color.BLACK, 3);
//		for (NetworkedPlayer np : netPlayers) {
			root.getChildren().add(tempNP);
//		}
		
		root.getChildren().add(player);
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
		
		player.tick(root);
		
		for (BaseEnemy enemy : enemies) {
			enemy.tick(player, root);
		}
		for (Spike spike : spikes) {
			spike.tick(player, root);
		}
		for (SupplyDrop supply : supplies) {
			supply.tick(player, root);
		}
//		for (NetworkedPlayer np : netPlayers) {
//			np.tick(root);
//		}
		
		player.checkPos(this);
		coords = new Message(player.getTranslateX(), player.getTranslateY());
		client.sendToServer(coords);
		
		Message temp = null;
		try {
			temp = client.getRecieved().take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tempNP.setTranslateX(temp.getX());
		tempNP.setTranslateY(temp.getY());
		
	}
	
	


	
	@Override
	public void start(Stage stage) throws Exception {
//		System.out.println("Game is Starting!!!!!!!!!!");
		stage.setResizable(false);
		setUpLevel(currentLevel);
		createContent();
//		Message nodes = new Message(rectNodes);
//		server.sendToAll(nodes);
		stage.setTitle("HALFLIFE");
		Scene scene = new Scene(display);
		stage.setScene(scene);
//		spplayer.buttonPressing(this, scene);
//		spplayer.buttonReleasing(scene);
		
		//IF YOU WANT THE SPRITE UNCOMMENT THE ABOVE AND COMMENT OUT THE BELOW
		player.buttonPressing(this, scene); 
		player.buttonReleasing(scene);
		
//		temp2.buttonPressing(this, scene);
//		temp2.buttonReleasing(scene);
		
		stage.show();
		
		
	}
		
	private void setUpLevel(String[] lvl) {
		levelWidth= lvl[0].length()*150;	
		
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
				
				case '7':
					platform =new RectObject(j*150,i*100,150,10,GameConstants.TYPE_PLATFORM,Color.LIGHTSKYBLUE);
					root.getChildren().add(platform);
					platforms.add(platform);
					SupplyDrop supply =new SupplyDrop(j*150,i*100-30,30,30);
					root.getChildren().add(supply);
					supplies.add(supply);
					break;
				}
			}
		}		
	}
}
