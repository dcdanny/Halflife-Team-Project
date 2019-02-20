package menu.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MultiMenuController {
	
	private Stage primaryStage;
	
	public void setStage(Stage stage) {
		primaryStage = stage;
	}
	
	@FXML
	private void goHost() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("hostmenu.fxml"));
		Pane multiMenu = loader.load();
		HostMenuController controller = loader.getController();
		controller.setStage(primaryStage);
		Scene scene = new Scene(multiMenu);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@FXML
	private void goJoin() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("joinmenu.fxml"));
		Pane multiMenu = loader.load();
		JoinMenuController controller = loader.getController();
		controller.setStage(primaryStage);
		Scene scene = new Scene(multiMenu);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@FXML
	private void goBack() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("mainmenu.fxml"));
		Pane multiMenu = loader.load();
		MainMenuController controller = loader.getController();
		controller.setStage(primaryStage);
		Scene scene = new Scene(multiMenu);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}