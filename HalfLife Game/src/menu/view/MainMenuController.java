package menu.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.SpriteAnimation;
import main.SpriteFollower;
import network.Client;
import network.Server;

public class MainMenuController {
	
	private Stage primaryStage;
	private SpriteFollower sf;
	private SpriteAnimation sp= new SpriteAnimation();
	private String serverLocation;
	public Server server;
	
	
	public void setStage(Stage stage) {
		primaryStage = stage;
	}
	
		
	
	// The "SINGLE-PLAYER" button, directing to the "SELECT GAME LEVEL" menu
	@FXML
	private void goSingle() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("levelmenu.fxml"));
		Pane mainMenu = loader.load();
		sf= new SpriteFollower(mainMenu);
		mainMenu.getChildren().add(sf);
		LevelMenuController controller = loader.getController();
		controller.setStage(primaryStage);
		Scene scene = new Scene(mainMenu);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		System.out.println("Start server...");
		//Can't use 0 - 1023, Use 1024 - 65 535
		final int port = 1035;
		System.out.println("port: "+port);
		server = new Server(port);
		server.setAllowNewPlayers(true); //Allow new players to connect
		serverLocation = server.getIpAddress()+":"+server.getPort();
		server.start();
		server.setAllowNewPlayers(false); //Stop any further players from connecting
		System.out.println("Network Client Connecting to: "+"localhost");
		//Can't use 0 - 1023, Use 1024 - 65 535
		Client client = new Client(1035,"dan","localhost");
		client.start();
	}
	
	// The "MULTI-PLAYERS" button, directing to the "MULTI-PLAYERS" menu
	@FXML
	private void goMulti() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("multimenu.fxml"));
		Pane mainMenu = loader.load();
		MultiMenuController controller = loader.getController();
		controller.setStage(primaryStage);
		Scene scene = new Scene(mainMenu);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	// The "SETTINGS" button, directing to the "SETTINGS" menu
	@FXML
	private void goSettings() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("settingsmenu.fxml"));
		Pane mainMenu = loader.load();
		SettingsMenuController controller = loader.getController();
		controller.setStage(primaryStage);
		Scene scene = new Scene(mainMenu);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	// The "BACK" button, directing to the first "HALFLIFE" menu
	@FXML
	private void goBack() throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("startmenu.fxml"));
		Pane mainMenu = loader.load();
		sp.resizeView(300, -60,600);
		mainMenu.getChildren().add(sp);
		StartMenuController controller = loader.getController();
		controller.setStage(primaryStage);
		Scene scene = new Scene(mainMenu);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}