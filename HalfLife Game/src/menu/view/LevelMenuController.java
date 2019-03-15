package menu.view;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Game;
import main.Level_Info;
import network.*;

public class LevelMenuController {
	
	private Stage primaryStage;
	private Server server;
	
	public void setStage(Stage stage, Server server) {
		primaryStage = stage;
		this.server = server;
	}
	
	// The "1" Button, directing to the Game "Level 1"
	@FXML
	private void go1() throws Exception {
			Game game = new Game(server);
			game.setCurrentLevel(Level_Info.LEVEL1);
			game.start(primaryStage);
			Message m = new Message("lvl1");
			server.sendToAll(m);
	}
	
	// The "2" Button, directing to the Game "Level 2"
	@FXML
	private void go2() throws Exception {
		Game game = new Game(server);
		game.setCurrentLevel(Level_Info.LEVEL2);
		game.start(primaryStage);
		Message m = new Message("lvl2");
		server.sendToAll(m);
		
	}
	
	// The "3" Button, directing to the Game "Level 3"
	@FXML
	private void go3() throws Exception {
		Game game = new Game(server);
		game.setCurrentLevel(Level_Info.LEVEL3);
		game.start(primaryStage);
		Message m = new Message("lvl3");
		server.sendToAll(m);
	}
	
	// The "4" Button, directing to the Game "Level 4"
	@FXML
	private void go4() throws Exception {
		Game game = new Game(server);
		game.setCurrentLevel(Level_Info.LEVEL4);
		game.start(primaryStage);
		Message m = new Message("lvl4");
		server.sendToAll(m);
	}
	
	
	// The "BACK" Button, directing to the main menu "HALFLIFE"
	@FXML
	private void goBack() throws IOException {
		server.stopServer();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("mainmenu.fxml"));
		Pane levelMenu = loader.load();
		MainMenuController controller = loader.getController();
		controller.setStage(primaryStage);
		Scene scene = new Scene(levelMenu);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void sendLevel() {
		String[] s = Server.showConnected();
		if (s.length>1) {
			//send level
		}
		//else single player no code required
	}

}