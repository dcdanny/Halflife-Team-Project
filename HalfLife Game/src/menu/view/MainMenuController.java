package menu.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainMenuController {
	
	private Stage primaryStage;
	
	public void setStage(Stage stage) {
		primaryStage = stage;
	}
	
	@FXML
	private void goSingle() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("levelmenu.fxml"));
		Pane mainMenu = loader.load();
		LevelMenuController controller = loader.getController();
		controller.setStage(primaryStage);
		Scene scene = new Scene(mainMenu);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
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