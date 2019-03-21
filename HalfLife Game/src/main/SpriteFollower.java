package main;

import java.awt.MouseInfo;
import java.awt.Point;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class SpriteFollower extends Pane {
	SpriteAnimation sp= new SpriteAnimation("player");
	double x;
	double y;
	
	public SpriteFollower(Pane p){
	
	sp.resizeView(0, 0, 120);
		AnimationTimer timer = new AnimationTimer() {
	        @Override
	        
	        public void handle(long now) {
	            TranslateTransition tt = new TranslateTransition(Duration.millis(200), sp);
	           // Point mouse = MouseInfo.getPointerInfo().getLocation();
	          
	            
	        	p.setOnMouseMoved(new EventHandler<MouseEvent>() 
	    		{
	    		  @Override
	    		  public void handle(MouseEvent event) {
	    		   x=event.getSceneX();
	    		  x=x-50;
	    		  if (sp.getTranslateX()>x) {
	    			  sp.flip();
	    		  }
	    		  else {
	    			  sp.flipnorm();
	    				 
	    			  
	    		  }
	    		  //  System.out.println(event.getScreenY());
	    		  }
	    		});
	           // x = mouse.getX();
	          //  y = mouse.getY();
	           // System.out.println(x);
	        	 tt.setToX(x);
	    		   tt.play();

	           
	           // tt.setToY(y);

	            
	        }
	    };

	    timer.start();
	    getChildren().add(sp);
	}
	public SpriteAnimation getSpriteAnimation(){
		return sp;
	}
	
}
