package menu.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.SpriteFollower;

public class MainMenuController {
	
	private Stage primaryStage;
	private SpriteFollower sf;
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
		StartMenuController controller = loader.getController();
		controller.setStage(primaryStage);
		Scene scene = new Scene(mainMenu);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}