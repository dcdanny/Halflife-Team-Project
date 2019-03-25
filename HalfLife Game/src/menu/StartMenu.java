package menu;

import java.io.File;
import java.io.FileNotFoundException;
//import java.io.File;
import java.io.IOException;

import com.halflife.enemies.SpriteEnemy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import main.ReadLevel;
import main.SpriteAnimation;
import main.WriteFile;
import menu.view.*;

public class StartMenu extends Application {
	
	private static Stage primaryStage;
	private static Pane mainLayout;
	private SpriteAnimation sp1 = new SpriteAnimation("player");
	private SpriteAnimation sp2 = new SpriteAnimation("player");
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("POKO");
		this.primaryStage.setWidth(800);
		this.primaryStage.setHeight(600);
		this.primaryStage.setMinWidth(250);
		this.primaryStage.setMinHeight(250);
		this.primaryStage.setResizable(false);
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
	
	private void setAnis() {
		sp1.resizeView(10, -135, 300);
		sp2.flip();
		sp2.resizeView(470, -220, 300);
	}
	
	private Scene setCursor(Scene s) {
		Image cursor = new Image("cursor.png");
		s.setCursor(new ImageCursor(cursor));
		return s;
	}
	
	// Loading the first "HALFLIFE" menu
	public void showMainView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(StartMenu.class.getResource("view/startmenu.fxml"));
		setAnis();
		mainLayout = loader.load();
		mainLayout.getChildren().add(sp1);
		mainLayout.getChildren().add(sp2);
		StartMenuController controller = loader.getController();
		controller.setStage(primaryStage);
		Scene scene = new Scene(mainLayout);
		setCursor(scene);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	/**
	 * Main Class to start the game
	 * @param args Command line arguments passed - None needed
	 */
	public static void main(String[] args) {
		WriteFile firstRunCheck = new WriteFile(true);
		try {
			firstRunCheck.firstTimeStart();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		launch(args);

	}
}
