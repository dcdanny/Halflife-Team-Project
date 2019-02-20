package menu.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JoinMenuController {
	
	private Stage primaryStage;
	
	public void setStage(Stage stage) {
		primaryStage = stage;
	}
	
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
	
	@FXML
	private void goNext() throws IOException {
	}
}