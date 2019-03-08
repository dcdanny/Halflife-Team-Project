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

	public void setStage(Stage stage) {
		primaryStage = stage;
	}
	
	
}