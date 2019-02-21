package menu.view;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Game;

public class JoinMenuController {
	
	private Stage primaryStage;
	
	public void setStage(Stage stage) {
		primaryStage = stage;
	}
	
	// The "BACK" Button, directing to the "MULTI-PLAYERS" menu
	@FXML
	private void goBack() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("multimenu.fxml"));
		Pane joinMenu = loader.load();
		MultiMenuController controller = loader.getController();
		controller.setStage(primaryStage);
		Scene scene = new Scene(joinMenu);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	// The "NEXT" Button, directing to the Game
	@FXML
	private void goNext() throws IOException {
		 new Thread() {
	            @Override
	            public void run() {
	            	 Platform.runLater(() -> {
	                     try {
							new Game().start(new Stage());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                 });
	            }}.start();
		 
		
	}
}