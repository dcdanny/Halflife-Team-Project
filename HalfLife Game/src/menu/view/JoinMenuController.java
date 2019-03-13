package menu.view;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.Game;
import network.Client;
import network.Server;

public class JoinMenuController {
	
	private Stage primaryStage;
	@FXML private TextField ipAddrInput;

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
		System.out.println(ipAddrInput.getText());
		System.out.println("Network Client Connecting to: "+ipAddrInput.getText());
		//Can't use 0 - 1023, Use 1024 - 65 535
		final int port = 1035;
		System.out.println("port: "+port);
		Client client = new Client(port,"dan",ipAddrInput.getText());
		client.start();
		//server.getclientTable().getQueue("dan");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("waitscreen.fxml"));
		Pane joinMenu = loader.load();
		WaitScreenController controller = loader.getController();
		controller.setStage(primaryStage, client);
		Scene scene = new Scene(joinMenu);
		primaryStage.setScene(scene);
		
		primaryStage.setOnShowing((WindowEvent e) -> {
			System.out.println("onshowing triggered");
	        //controller.waitConnection();
			Pane asdf = null;
			try {
				asdf = client.waitForMessage().getPane();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("end onshowing");
			
	    });
		
		primaryStage.show();
		
		
		/*while (!primaryStage.isShowing()) {
			if (primaryStage.isShowing())
				break;
		}*/
		
		/*
		System.out.println("z");
		if (primaryStage.isShowing())
			initialiseController(controller, client);
			
			System.out.println("waiting");
*/
	}
	/*public void initialiseController(WaitScreenController controller, Client client) {
		controller.initialise(primaryStage, client);
	}*/
}