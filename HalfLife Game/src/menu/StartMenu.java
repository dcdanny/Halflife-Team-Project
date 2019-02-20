package menu;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import menu.view.*;

public class StartMenu extends Application {
	
	private static Stage primaryStage;
	private static Pane mainLayout;

	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("HALFLIFE");
		showMainView();
	}
	
	public void showMainView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(StartMenu.class.getResource("view/startmenu.fxml"));
		mainLayout = loader.load();
		StartMenuController controller = loader.getController();
		controller.setStage(primaryStage);
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
