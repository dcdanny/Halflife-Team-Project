package menu.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HostMenuController {
	
	private Stage primaryStage;
	
	public void setStage(Stage stage) {
		primaryStage = stage;
	}
	
	// The "BACK" Button, directing to the "MULTI-PLAYERS" menu
	@FXML
	private void goBack() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("multimenu.fxml"));
		Pane hostMenu = loader.load();
		MultiMenuController controller = loader.getController();
		controller.setStage(primaryStage);
		Scene scene = new Scene(hostMenu);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	// The "NEXT" Button, directing to the "SELECT GAME LEVEL" menu
	@FXML
	private void goNext() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("levelmenu.fxml"));
		Pane hostMenu = loader.load();
		LevelMenuController controller = loader.getController();
		controller.setStage(primaryStage);
		Scene scene = new Scene(hostMenu);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}