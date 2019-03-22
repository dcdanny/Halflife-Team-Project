package menu.view;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Game;
import main.Level_Info;
import main.ReadLevel;
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
			Message m = new Message(Level_Info.LEVEL1);
			server.sendToAll(m);
			game.start(primaryStage);
	}
	
	// The "2" Button, directing to the Game "Level 2"
	@FXML
	private void go2() throws Exception {
		Game game = new Game(server);
		game.setCurrentLevel(Level_Info.LEVEL2);
		Message m = new Message(Level_Info.LEVEL2);
		server.sendToAll(m);
		game.start(primaryStage);
	}
	
	// The "3" Button, directing to the Game "Level 3"
	@FXML
	private void go3() throws Exception {
		ReadLevel levelReader = new ReadLevel();
		levelReader.getLevel();
		if (levelReader.isValid()) {
			Game game = new Game(server);
			game.setCurrentLevel(levelReader.getValidatedLevel());
			Message m = new Message(levelReader.getValidatedLevel());
			server.sendToAll(m);
			game.start(primaryStage);
		}
		else
			System.out.println(levelReader.returnErrors()[0]);
	}
	
	// The "4" Button, directing to the Game "Level 4"
	@FXML
	private void go4() throws Exception {
		ReadLevel levelReader = new ReadLevel();
		levelReader.getLevel();
		if (levelReader.isValid()) {
			Game game = new Game(server);
			game.setCurrentLevel(levelReader.getValidatedLevel());
			Message m = new Message(levelReader.getValidatedLevel());
			server.sendToAll(m);
			game.start(primaryStage);
		}
	}
	
	private Scene setCursor(Scene s) {
		Image cursor = new Image("cursor.png");
		s.setCursor(new ImageCursor(cursor));
		return s;
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
		setCursor(scene);
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