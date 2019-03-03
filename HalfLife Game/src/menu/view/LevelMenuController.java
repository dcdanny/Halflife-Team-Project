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

public class LevelMenuController {
	
	private Stage primaryStage;
	
	public void setStage(Stage stage) {
		primaryStage = stage;
	}
	
	// The "1" Button, directing to the Game "Level 1"
	@FXML
	private void go1() throws Exception {
		/*new Thread() {
			@Override
			public void run() {
				Platform.runLater(() -> {
					try {
						new Game().start(primaryStage);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				}*/
			Game game = new Game();
			game.setCurrentLevel(Level_Info.LEVEL1);
			game.start(primaryStage);
			//}.start();	
	}
	
	// The "2" Button, directing to the Game "Level 2"
	@FXML
	private void go2() throws Exception {
		Game game = new Game();
		game.setCurrentLevel(Level_Info.LEVEL2);
		game.start(primaryStage);
	}
	
	// The "3" Button, directing to the Game "Level 3"
	@FXML
	private void go3() throws Exception {
		Game game = new Game();
		game.setCurrentLevel(Level_Info.LEVEL3);
		game.start(primaryStage);
	}
	
	// The "4" Button, directing to the Game "Level 4"
	@FXML
	private void go4() throws Exception {
		Game game = new Game();
		game.setCurrentLevel(Level_Info.LEVEL4);
		game.start(primaryStage);
	}
	
	// The "BACK" Button, directing to the main menu "HALFLIFE"
	@FXML
	private void goBack() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("mainmenu.fxml"));
		Pane levelMenu = loader.load();
		MainMenuController controller = loader.getController();
		controller.setStage(primaryStage);
		Scene scene = new Scene(levelMenu);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}