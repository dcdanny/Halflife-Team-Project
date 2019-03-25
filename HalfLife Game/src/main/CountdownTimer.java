package main;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class CountdownTimer extends Pane{
	private Timeline ticker;
	private int tmp=0;
	private String emp="";
	
	Label label=new Label("0");
	
	public CountdownTimer() {
		label.setFont(javafx.scene.text.Font.font(50 ));
		label.setTextFill(Color.valueOf("#C2C1BB"));
		label.setTranslateX(0 ); 
		label.setTranslateY(0);
		getChildren().add(label);
		ticker= new Timeline(new KeyFrame(Duration.seconds(1), e-> timelabel()));
		ticker.setCycleCount(Timeline.INDEFINITE); //change the clock
		ticker.play();  
	}
	private void timelabel() {
		if (tmp>-1) {
			tmp++;
		}
		emp=tmp+"";
		label.setText(emp);
		}
	
	public int getTime() {
		return tmp;
	}
	
	public void resetTime() {
		tmp=0;
	}
	
	public void pauseTime() {
		ticker.pause();
	}
	
	public void continueTime() {
		ticker.play();
	}
}


