package menu.view;

import java.io.File;
import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
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
		Game game = new Game(server);
		game.setCurrentLevel(Level_Info.LEVEL3);
		Message m = new Message(Level_Info.LEVEL3);
		server.sendToAll(m);
		game.start(primaryStage);
	}
	
	// The "4" Button, directing to the Game "Level 4"
	@FXML
	private void go4() throws Exception {
		Game game = new Game(server);
		game.setCurrentLevel(Level_Info.LEVEL4);
		Message m = new Message(Level_Info.LEVEL4);
		server.sendToAll(m);
		game.start(primaryStage);
	}
	
	private Scene setCursor(Scene s) {
		Image cursor = new Image("cursor.png");
		s.setCursor(new ImageCursor(cursor));
		return s;
	}
	
	// The "Upload" Button
	@FXML
	private ListView listview;
	
	public void goUpload(ActionEvent event) throws Exception {
		FileChooser fc = new FileChooser();
		File selectedFile = fc.showOpenDialog(primaryStage);
		if (selectedFile != null) {
			String fileName=fc.getInitialFileName();
		} else {
			System.out.println("File is not valid");
		}
		System.out.println(selectedFile.getPath());
		ReadLevel readLevel = new ReadLevel(selectedFile.getPath());
		readLevel.read();
		
		Game game = new Game(server);
		game.setCurrentLevel(readLevel.getValidatedLevel());
		Message m = new Message(readLevel.getValidatedLevel());
		server.sendToAll(m);
		game.start(primaryStage);
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