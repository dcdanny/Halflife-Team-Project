package main;

import java.util.ArrayList;
import java.util.Collection;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class AnimationSprite extends Pane {
	private int frameTime=200;
	private int imageIndex = 0 ;
	ImageView imageView = new ImageView();
	ArrayList<Image> imglist= new ArrayList<Image>();
	public AnimationSprite(){
		//Set up frames
	Image frame1 = new Image("player1.png");
	
	Image frame2 = new Image("player2.png");
	
	Image frame3 = new Image("player3.png");
	
	Image frame4 = new Image("player4.png");
	
	imglist.add(frame1);
	imglist.add(frame2);
	imglist.add(frame3);
	imglist.add(frame4);
//	Formatting(view1);
//	Formatting(view2);
//	Formatting(view3);
	//Formatting(view4);
	Timeline timeLine = new Timeline();
    Collection<KeyFrame> frames = timeLine.getKeyFrames();
    Duration frameGap = Duration.millis(256);
    Duration frameTime = Duration.ZERO ;
    for (Image img : imglist) {
        frameTime = frameTime.add(frameGap);
        frames.add(new KeyFrame(frameTime, e -> imageView.setImage(img)));
       // this.add(imageView);
    }
    
    timeLine.setCycleCount(Timeline.INDEFINITE);
    timeLine.play();
	this.getChildren().add(Formatting(imageView));
}
	
	private ImageView Formatting(ImageView img) {
		img.setFitWidth(120);
		img.setFitHeight(120);
		img.setTranslateX(150);
		img.setTranslateY(120);
		return img;
	}
	
	
}