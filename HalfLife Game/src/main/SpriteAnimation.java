package main;

import java.util.ArrayList;

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
	// ...
	public SpriteAnimation() {
		
		Image frame1 = new Image("player1.png");
		Image frame2 = new Image("player2.png");
		Image frame3 = new Image("player3.png");
		//Image frame4 = new Image("player4.png");
		
		
		ArrayList<Image> images = new ArrayList<>();
		images.add(frame1);
		images.add(frame2);
		images.add(frame3);
		//images.add(frame4);
		
		Timeline timeline= new Timeline();
		format(imageView);
	    KeyFrame nextframe = new KeyFrame(Duration.seconds(.300),
	    
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
}

