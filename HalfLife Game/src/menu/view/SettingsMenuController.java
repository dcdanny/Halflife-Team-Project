package menu.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class SettingsMenuController {
	
	private Stage primaryStage;
	
	public void setStage(Stage stage) {
		primaryStage = stage;
	}
	
	private Scene setCursor(Scene s) {
		Image cursor = new Image("cursor.png");
		s.setCursor(new ImageCursor(cursor));
		return s;
	}
	
	private MediaPlayer mediaplayer;
	//private Button soundOn, soundOff, musicOn, musicOff;
	
	public void start (Stage stage) {
		
		Button soundOn = new Button("soundOn");
		Button soundOff = new Button("soundOff");
		Button musicOn = new Button("musicOn");
		Button musicOff = new Button("musicOff");
		
		soundOn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				Media soundFile = new Media("shootSound.mp3");
				mediaplayer = new MediaPlayer(soundFile);
				mediaplayer.stop();
			}	
		});
		
		soundOff.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Media soundFile = new Media("shootSound.mp3");
				mediaplayer = new MediaPlayer(soundFile);
				mediaplayer.stop();
			}	
		});
		
		musicOn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Media musicFile = new Media("Sample - summer.mp3");
				mediaplayer = new MediaPlayer(musicFile);
				mediaplayer.play();
			}	
		});
		
		musicOff.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Media musicFile = new Media("Sample - summer.mp3");
				mediaplayer = new MediaPlayer(musicFile);
				mediaplayer.stop();
			}	
		});

		mediaplayer.setAutoPlay(true);
		mediaplayer.setVolume(0.1);	
	}
	
	// The "BACK" button, directing to the main menu "HALFLIFE"
	@FXML
	private void goBack() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("mainmenu.fxml"));
		Pane settingsMenu = loader.load();
		MainMenuController controller = loader.getController();
		controller.setStage(primaryStage);
		Scene scene = new Scene(settingsMenu);
		setCursor(scene);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}