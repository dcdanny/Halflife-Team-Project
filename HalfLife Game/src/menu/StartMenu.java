package menu;

import java.io.File;
//import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import main.SpriteAnimation;
import menu.view.*;

public class StartMenu extends Application {
	
	private static Stage primaryStage;
	private static Pane mainLayout;
	private SpriteAnimation sp= new SpriteAnimation();
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("POKO");
		this.primaryStage.setWidth(800);
		this.primaryStage.setHeight(600);
		this.primaryStage.setMinWidth(250);
		this.primaryStage.setMinHeight(250);
		this.primaryStage.setResizable(true);
		String musicFile = "data/Sample - summer.mp3";     // For example

		try {
			Media sound = new Media(new File(musicFile).toURI().toASCIIString());
			MediaPlayer mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.play();
			
		}catch(MediaException e) {
			System.out.println("Unable to play audio: "+e.getMessage());
		}
		showMainView();
	}
	
	// Loading the first "HALFLIFE" menu
	public void showMainView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(StartMenu.class.getResource("view/startmenu.fxml"));
		
		mainLayout = loader.load();
		sp.resizeView(300, -60,600);
		mainLayout.getChildren().add(sp);
		/*mainLayout.add(sp);*/
		//System.out.println(mainLayout.getChildren());
		
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
