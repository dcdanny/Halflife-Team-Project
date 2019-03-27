package main;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class CloudsAnimation extends Pane {
	//ImageView clouds;
	ArrayList <ImageView> cloudsList= new ArrayList();
	Pane pane;
	Random rand= new Random();
	int levelWidth;
	public CloudsAnimation(int levelWidth){
		this.levelWidth=levelWidth;
		Image cloud1= new Image("cloud1.png");
		Image cloud2= new Image ("cloud2.png");
		Image cloud3= new Image ("cloud3.png");
		
		for (int i = 0; i < rand.nextInt(7)+1; i++) {
			ImageView cloudsone= new ImageView(cloud1);
			ImageView cloudstwo= new ImageView(cloud2);
			ImageView cloudsthree= new ImageView(cloud3);
			
			cloudsList.add(cloudsone);
			cloudsList.add(cloudstwo);
			cloudsList.add(cloudsthree);
		}
		
		for (int i = 0; i < cloudsList.size(); i++) {
			formatting(cloudsList.get(i),levelWidth);

		}
		//this.pane=levelWidth;
		start();
		
	}
	
	private void start() {
		TranslateTransition tt=null;
		for (int j = 0; j < cloudsList.size(); j++) {
			 tt = new TranslateTransition(Duration.seconds(100), cloudsList.get(j));
			  tt.setByX(levelWidth);
		      tt.setCycleCount(TranslateTransition.INDEFINITE);
		      tt.play();
		}
	      
	        
	           // Point mouse = MouseInfo.getPointerInfo().getLocation();
	         // tt.setFromX(-100);
	        

	            
	       
	    
	        System.out.println("playing");
	   
	    for (int i = 0; i < cloudsList.size(); i++) {
			this.getChildren().add(cloudsList.get(i));
		}
	   
	}
	private ImageView formatting(ImageView img,int levelWidth) {
		System.out.println("width"+levelWidth); 
		img.setOpacity(0.50);
		int xval=rand.nextInt(levelWidth+1+500)+-500; //max 30, min -200
		int yval=rand.nextInt(-300+1+600)+-600; //max -200, min -100
		System.out.println("XVAL"+xval);
		img.setTranslateX(xval);
		img.setTranslateY(yval);
		double scale= rand.nextInt(3)+1;
		double scaledouble=scale/10;
		System.out.println(scaledouble);
		img.setScaleX(scaledouble);
		img.setScaleY(scaledouble);
		
		//img.setPreserveRatio(true);
		return img;
	}
	
	
}

