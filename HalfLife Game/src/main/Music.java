package main;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Music {
	String musicFile = "data/Sample - summer.mp3"; 
	static MediaPlayer mediaPlay;
	String sound= "Pop.mp3";
	
	public  Music(String musicType) {
		if (musicType.equals("music")) {
			Media sound = new Media(new File(musicFile).toURI().toASCIIString());
			mediaPlay = new MediaPlayer(sound);
		
		}
		
		playMus();
		
	}
	public Music() {
		
	}
	
	public void stopMus(){
		mediaPlay.stop();
	}
	public void playMus() {
		mediaPlay.play();
	}
	
}

