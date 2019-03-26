package main;

import java.io.File;

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
			mediaPlay.setVolume(0.1);
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

