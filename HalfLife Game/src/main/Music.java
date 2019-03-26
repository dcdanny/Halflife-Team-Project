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
	
	
	public  Music(String screenname) {
		
		Media sound = new Media(new File(musicFile).toURI().toASCIIString());
		
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		
		mediaPlayer.play();
	}
	public Music() {
		
	}
	
	public void stopMus(){
		mediaPlay.stop();
	}
	
}

