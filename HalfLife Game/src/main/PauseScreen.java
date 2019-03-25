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
		 RectObject bg=new RectObject(270,150,250,300,"pausescreen",Color.valueOf("#000000"));
		 
		 Image resume = new Image("pauseResume.png");
         ImageView resumeImg= new ImageView(resume);
         resumeImg.setFitWidth(200);
         resumeImg.setTranslateX(270);
         resumeImg.setTranslateY(70);
         resumeImg.setPreserveRatio(true);
         resumeImg.setSmooth(true);
         resumeImg.setCache(true);
         
         Image exit = new Image("pauseExit.png");
         ImageView exitImg= new ImageView(exit);
         exitImg.setFitWidth(200);
         exitImg.setTranslateX(270);
         exitImg.setTranslateY(200);
         exitImg.setPreserveRatio(true);
         exitImg.setSmooth(true);
         exitImg.setCache(true);
         
         this.getChildren().add(bg);
         this.getChildren().add(resumeImg);
         this.getChildren().add(exitImg);
         
         RectObject resumeButton =new RectObject(270,70,200,50,"resume button",Color.TRANSPARENT);
		
         resumeButton.setOnMouseEntered(new EventHandler<MouseEvent>
         () {
             @Override
             public void handle(MouseEvent t) {
                resumeImg.setFitWidth(250);
             }
         });

         resumeButton.setOnMouseExited(new EventHandler<MouseEvent>
         () {
             @Override
             public void handle(MouseEvent t) {
                resumeImg.setFitWidth(200);
             }
         });
         
         RectObject exitButton =new RectObject(270,200,200,50,"exit button",Color.TRANSPARENT);
 		
         exitButton.setOnMouseEntered(new EventHandler<MouseEvent>
         () {
             @Override
             public void handle(MouseEvent t) {
                exitImg.setFitWidth(250);
             }
         });

         exitButton.setOnMouseExited(new EventHandler<MouseEvent>
         () {
             @Override
             public void handle(MouseEvent t) {
                exitImg.setFitWidth(200);
             }
         });
         
         
         this.getChildren().add(resumeButton);
         this.getChildren().add(exitButton);
	}
	
	 public void remove() {
		 this.getChildren().clear();
	 }
	
}