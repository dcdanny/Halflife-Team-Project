package main;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Timer extends Pane{
	private Timeline ticker;
	private int tmp=60;
	private String emp="";
	
	Label label=new Label("60");
	
	Timer() {
		
	  label.setFont(javafx.scene.text.Font.font(50 ));
	label.setTranslateX(0 ); 
	label.setTranslateY(0);
	getChildren().add(label);
	ticker= new Timeline(new KeyFrame(Duration.seconds(1), e-> timelabel()));
	
	ticker.setCycleCount(Timeline.INDEFINITE); //change the clock
	ticker.play(); 
	
	  
	}
	private void timelabel() {
		if (tmp>0) {
			tmp--;
		}
		emp=tmp+"";
		label.setText(emp);
		}
	}

