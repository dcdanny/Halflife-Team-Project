package menu;

import java.io.File;
//import java.io.File;
import java.io.IOException;

import com.halflife.enemies.SpriteEnemy;

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
	private SpriteAnimation sp1 = new SpriteAnimation("player");
	private SpriteAnimation sp2 = new SpriteAnimation("player");
	private SpriteEnemy spEnemy1 = new SpriteEnemy(0,0,30,30);
	private SpriteEnemy spEnemy2 = new SpriteEnemy(0,0,30,30);
	private SpriteEnemy spEnemy3 = new SpriteEnemy(0,0,30,30);
	private SpriteEnemy spEnemy4 = new SpriteEnemy(0,0,30,30);
	
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
		spEnemy1.setTranslateX(20);
		spEnemy1.setTranslateY(-310);
		spEnemy2.setTranslateX(720);
		spEnemy2.setTranslateY(-310);
		spEnemy3.setTranslateX(720);
		spEnemy3.setTranslateY(140);
		spEnemy4.setTranslateX(20);
		spEnemy4.setTranslateY(140);
	}
	
	// Loading the first "HALFLIFE" menu
	public void showMainView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(StartMenu.class.getResource("view/startmenu.fxml"));
		setAnis();
		mainLayout = loader.load();
		mainLayout.getChildren().add(sp1);
		mainLayout.getChildren().add(sp2);
		mainLayout.getChildren().add(spEnemy1);
		mainLayout.getChildren().add(spEnemy2);
		mainLayout.getChildren().add(spEnemy3);
		mainLayout.getChildren().add(spEnemy4);
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
