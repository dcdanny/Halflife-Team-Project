package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Lives extends Group {
	private int lives = 3;
	private boolean isDead;
	private ArrayList<ImageView> heartlist = new ArrayList<ImageView>();

	public Lives(int _lives) {

		super();
		lives = _lives;
		addLives();
	}

	public void addLives() {
		this.getChildren().clear();
		Image image = null;
		try {
			image = new Image(new FileInputStream("res/heart-1.png.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Setting the image view
		// this.setImage(image);

		List<ImageView> hearts = new ArrayList<ImageView>();
		for (int i = 0; i < lives; i++) {
			hearts.add(new ImageView(image));
		}

		for (int i = 0; i < lives; i++) {
			hearts.get(i).setX(i * 50 + 610);
			hearts.get(i).setY(-10);
			hearts.get(i).setFitHeight(100);
			hearts.get(i).setFitWidth(100);
			hearts.get(i).setPreserveRatio(true);
			this.getChildren().add(hearts.get(i));
			heartlist.add(hearts.get(i));
		}

		isDead = false;
	}
	
	// Removes lives from display when called
	public void lostlife() {

		if (lives > 0) {
			this.getChildren().remove(heartlist.get(heartlist.size() - 1));
			heartlist.remove(heartlist.size() - 1);
			lives = lives - 1;
		} else {
			isDead = true;
		}
	}

	public boolean isDead() {
		return isDead;
	}
	
	public void setLives(int _lives) {
		lives = _lives;
	}
}
