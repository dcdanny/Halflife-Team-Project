package menu.view;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Game;
import network.Client;
import network.Server;

public class WaitScreenController {
	
	private Stage primaryStage;
	@FXML private TextField ipAddrInput;

	public void setStage(Stage stage, Client client) {
		primaryStage = stage;
//		Pane root = client.waitForMessage().getPane();
//		primaryStage.setScene(root);
	}
	
	// The "Disconnect" Button
	@FXML
	private void goBack() throws IOException {
		//client.stopClient();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("joinmenu.fxml"));
		Pane joinmenu = loader.load();
		JoinMenuController controller = loader.getController();
		controller.setStage(primaryStage);
		Scene scene = new Scene(joinmenu);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
