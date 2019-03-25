package main;

import com.halflife.entities.Player;
import com.halflife.entities.RectObject;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PauseScreen extends StackPane {
	
	PauseScreen(){
		 RectObject bg=new RectObject(0,0,400,300,"pausescreen",Color.valueOf("#333333"));
		 
		 Image resume = new Image("pauseResume.png");
         ImageView resumeImg= new ImageView(resume);
         resumeImg.setFitWidth(500);
         resumeImg.setTranslateY(-100);
         resumeImg.setPreserveRatio(true);
         resumeImg.setSmooth(true);
         resumeImg.setCache(true);
         
         Image pause = new Image("pauseExit.png");
         ImageView pauseImg= new ImageView(pause);
         pauseImg.setFitWidth(500);
         pauseImg.setTranslateY(100);
         pauseImg.setPreserveRatio(true);
         pauseImg.setSmooth(true);
         pauseImg.setCache(true);
		
	}
	
}