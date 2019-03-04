package main;

<<<<<<< HEAD

	import javafx.animation.Interpolator;
	import javafx.animation.Transition;
	import javafx.geometry.Rectangle2D;
	import javafx.scene.image.ImageView;
	import javafx.util.Duration;

	public class SpriteAnimation extends Transition {

	    private final ImageView imageView;
	    private final int count;
	    private final int columns;
	    private final int offsetX;
	    private final int offsetY;
	    private final int width;
	    private final int height;

	    private int lastIndex;

	    public SpriteAnimation(
	            ImageView imageView, 
	            Duration duration, 
	            int count,   int columns,
	            int offsetX, int offsetY,
	            int width,   int height) {
	        this.imageView = imageView;
	        this.count     = count;
	        this.columns   = columns;
	        this.offsetX   = offsetX;
	        this.offsetY   = offsetY;
	        this.width     = width;
	        this.height    = height;
	        setCycleDuration(duration);
	        setInterpolator(Interpolator.LINEAR);
	    }

	    protected void interpolate(double k) {
	        final int index = Math.min((int) Math.floor(k * count), count - 1);
	        if (index != lastIndex) {
	            final int x = (index % columns) * width  + offsetX;
	            final int y = (index / columns) * height + offsetY;
	            imageView.setViewport(new Rectangle2D(x, y, width, height));
	            lastIndex = index;
	        }
	    }
	}


=======
import java.util.ArrayList;

import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.KeyFrame;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SpriteAnimation extends Pane {
	private int imageIndex = 0 ;
	private final int frameTime = 2000; // milliseconds

	// ...
	public SpriteAnimation() {
		
		Image frame1 = new Image("player1.png");
		Image frame2 = new Image("player2.png");
		Image frame3 = new Image("player3.png");
		Image frame4 = new Image("player4.png");
		
		
		ImageView imageView = new ImageView();
		ArrayList<Image> images = new ArrayList<>();
		images.add(frame1);
		images.add(frame2);
		images.add(frame3);
		images.add(frame4);
		
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

}
>>>>>>> visuals
