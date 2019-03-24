package menu.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.SpriteAnimation;
import javafx.stage.WindowEvent;

public class StartMenuController {
	
	private Stage primaryStage;
	
	public void setStage(Stage stage) {
		primaryStage = stage;
	}

	private Scene setCursor(Scene s) {
		Image cursor = new Image("cursor.png");
		s.setCursor(new ImageCursor(cursor));
		return s;
	}
	
	// The "START" Button, directing to the main menu "HALFLIFE"
	@FXML
	private void goStart() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("mainmenu.fxml"));
		Pane mainMenu = loader.load();
		
		MainMenuController controller = loader.getController();
		controller.setStage(primaryStage);
		Scene scene = new Scene(mainMenu);
		setCursor(scene);
		primaryStage.setScene(scene);
		this.primaryStage.setOnCloseRequest((WindowEvent event) -> {
	        System.exit(0);
	    });
		primaryStage.show();
	}
	
	// The "EXIT" Button, quitting the game
	@FXML
	private void goExit() throws IOException {
		System.exit(0);
	}
}
