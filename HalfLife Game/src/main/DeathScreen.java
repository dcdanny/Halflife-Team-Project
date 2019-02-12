package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.sun.prism.paint.ImagePattern;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class DeathScreen extends StackPane {

	 DeathScreen() {
			Image death = new Image("deathscreen.png");
			ImageView img= new ImageView(death);
			img.setFitWidth(450);
			img.setTranslateX(170);
			img.setTranslateY(100);
	         img.setPreserveRatio(true);
	         img.setSmooth(true);
	         img.setCache(true);
			this.getChildren().add(img);
			
		
}
}


