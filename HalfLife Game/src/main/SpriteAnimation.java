package main;

import java.util.ArrayList;

import com.halflife.entities.ListofAnimations;

import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.KeyFrame;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SpriteAnimation extends Pane {
	private int imageIndex = 0 ;
	private final int frameTime = 2000; // milliseconds
	private ImageView imageView = new ImageView();
	private ImageView enemyimg=new ImageView();
	ArrayList<Image> images = new ArrayList<>();
	Timeline timeline= new Timeline();
	ListofAnimations playingAnimations= new ListofAnimations();

	public SpriteAnimation(String type) {
		
		Image frame1=null;
		Image frame2=null;
		Image frame3=null;
		double t=0;
		if (type.equals("enemy")) {
			 frame1 = new Image("enemy-1.png");
			 frame2 = new Image("enemy-2.png");
			 frame3=new Image("enemy-1.png");
			 t=.500;
			 System.out.println("AN ENEMY");
		}
		if(type.equals("player")) {
			
			 frame1 = new Image("player1.png");
			 frame2 = new Image("player2.png");
			 frame3 = new Image("player3.png");
			 t=.300;
			
		}
		images.add(frame1);
		images.add(frame2);
		images.add(frame3);
		
		if (type.equals("player")) {
		format(imageView);
		}
	    KeyFrame nextframe = new KeyFrame(Duration.seconds(t),
	    
                new EventHandler<ActionEvent>() {

                    public void handle(ActionEvent event) {
                    if (imageIndex==images.size()) {
                    	imageIndex=0;
                    }
                    imageView.setImage(images.get(imageIndex));
					imageIndex++;
					
                    }
                });
		
	    this.setPickOnBounds(false);
		
	    timeline.getKeyFrames().add(nextframe);
		timeline.setCycleCount(Timeline.INDEFINITE);
		
		timeline.play();
		
		this.getChildren().add(imageView);
		playingAnimations.addToList(this);
		
		
	
	}
	public void stopAnimation() {
		timeline.stop();
		
		
		System.out.println("HAS STOPPED");
	}
	public Timeline getTimeline() {
		return timeline;
	}
	private ImageView format (ImageView img) {
		img.setFitWidth(120);
		img.setFitHeight(120);
		img.setTranslateX(100);
		return img;
		
	}
	public ImageView flip() {
		imageView.setScaleX((double)-1);
		return imageView;
	}
	public ImageView flipnorm() {
		imageView.setScaleX((double)1);
		return imageView;
	}

	public ImageView resizeView(int x , int y , int fit) {
		imageView.setFitHeight(fit);
		imageView.setFitWidth(fit);
		imageView.setTranslateX(x);
		imageView.setTranslateY(y);
		return imageView;
	}
	public void keepAllbut (int howmany) {
	
			playingAnimations.keepAllBut(howmany);
			
		
	}
	public void removeAllOthers() {
		playingAnimations.wipeList();
	}
	public void keepitself() {
		playingAnimations.keepOnly(this);
	}
}

