package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.halflife.entities.Player;
import com.halflife.entities.RectObject;
import com.halflife.entities.SpritePlayer;
import com.sun.prism.paint.ImagePattern;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.scene.image.*;

import javafx.scene.paint.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class DeathScreen extends StackPane {

	 DeathScreen(Game game, Player player) {
		 RectObject bg=new RectObject(0,0,800,600,"deathscreen",Color.valueOf("#333333"));
		 Image youdied = new Image("youdied.png");
         ImageView img= new ImageView(youdied);
         img.setFitWidth(500);
         img.setTranslateY(-100);
         img.setPreserveRatio(true);
         img.setSmooth(true);
         img.setCache(true);
		 
		 Fade(bg);
				         
	         Image restart = new Image("restart.png");
	         ImageView resimg= new ImageView(restart);
	         resimg.setFitWidth(400);
	         resimg.setTranslateX(-150);
	         resimg.setTranslateY(180);
	         resimg.setPreserveRatio(true);
	         resimg.setSmooth(true);
	         resimg.setCache(true);
	         RectObject resbutton=new RectObject(-150,180,250,100,"restart button",Color.TRANSPARENT);
	       
	         
	      //  resbutton.setFill(new javafx.scene.paint.ImagePattern(restart));
	         
	         resbutton.setOnMouseEntered(new EventHandler<MouseEvent>
	         () {

	             @Override
	             public void handle(MouseEvent t) {
	                resimg.setFitWidth(500);
	             }
	         });

	         resbutton.setOnMouseExited(new EventHandler<MouseEvent>
	         () {

	             @Override
	             public void handle(MouseEvent t) {
	                resimg.setFitWidth(450);
	             }
	         });
	         
	         resbutton.setOnMouseClicked((MouseEvent e) -> {
	             remove();
	             player.setDead(false);
	             player.addLives(3);	   
	             player.addAmmo(10);
	             player.getTimer().resetTime();
	             player.setTranslateX(200);
	             player.setTranslateY(0);
	             player.getForeground().getChildren().remove(this);
	             game.setUpLevel(game.GetCurrentLevel());
	             
	             
	         });
	         Image exit= new Image("exit.png");
	         ImageView exitimg= new ImageView(exit);
	         exitimg.setFitWidth(400);
	         exitimg.setTranslateX(140);
	         exitimg.setTranslateY(180);
	         exitimg.setPreserveRatio(true);
	         exitimg.setSmooth(true);
	         exitimg.setCache(true);
	         
	         RectObject exitbutton=new RectObject(150,180,160,100,"exit button",Color.TRANSPARENT);
	         exitbutton.setOnMouseEntered(new EventHandler<MouseEvent>
	         () {

	             @Override
	             public void handle(MouseEvent f) {
	                exitimg.setFitWidth(450);
	             }
	         });

	         exitbutton.setOnMouseExited(new EventHandler<MouseEvent>
	         () {

	             @Override
	             public void handle(MouseEvent f) {
	                exitimg.setFitWidth(400);
	             }
	         });
	         exitbutton.setOnMouseClicked((MouseEvent e) -> {
	             System.out.println("Clicked Exit!"); // change functionality
	             remove();
	         });
	         
	         this.getChildren().add(bg);
	         this.getChildren().add(img);
			this.getChildren().add(resimg);
			this.getChildren().add(resbutton);
			
			this.getChildren().add(exitimg);
			this.getChildren().add(exitbutton);
			
		
}
	 public void Fade(RectObject rec) {
			if (rec.getType().equals("deathscreen")) {
					
				 FadeTransition ft = new FadeTransition(Duration.millis(2000), this);
				//the fade may seem to quick but it is there, while the game is running you can see
				 //the fade better
				 ft.setFromValue(0.0);
				 ft.setToValue(1.0);
				 ft.setCycleCount(1);
				 ft.setAutoReverse(false);
				    
				
				 Double opa = this.getOpacity();
		         if (opa.intValue() == 0) {
		             return;
		         }
		            
		         Animation.Status as = ft.getStatus();
		         
		         if (as == Animation.Status.RUNNING) {
		             return;
		         
		         }
		         if (as == Animation.Status.STOPPED) {
		             ft.play();
		         }           
		         
			}
		}
	 public void remove() {
		 this.getChildren().clear();
		 
		// this.getChildren().remove(from, to);
	 }
		
}


