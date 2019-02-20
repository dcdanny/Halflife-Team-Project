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

public class Ammo extends Group {
	
	// Number of bullets the player starts with
	private static int no_of_bullets = 6;
	
	private ArrayList<ImageView>ammoList=new ArrayList<ImageView>();
	
	// Main method
	public Ammo(){ 	
	    Image image = null;
	    try {
	    	image = new Image(new FileInputStream("res/ammo.png"));
	    } catch (FileNotFoundException e) {
		e.printStackTrace();
	    }      

	    List<ImageView> ammunition = new ArrayList<ImageView>();
	    for (int i = 0; i < no_of_bullets; i++) {
	    	ammunition.add(new ImageView(image));
	    }

	    setAmmoImages(ammunition);
	    
	    for (int k = 0; k < no_of_bullets; k++) {
	    	this.getChildren().add(ammunition.get(k));
	    	ammoList.add(ammunition.get(k));
	    }	   	   

     
	}
	
	
	public void setAmmoImages(List<ImageView> ammunition) {
		for (int i = 0; i < no_of_bullets; i++) {
	    	ammunition.get(i).setX(i*20 + 30);
	    	ammunition.get(i).setY(0);
	    	ammunition.get(i).setFitHeight(75);
	    	ammunition.get(i).setFitWidth(75);
	    	ammunition.get(i).setPreserveRatio(true);
	    }
	}

	public void lostBullet() {
		
		if (no_of_bullets>0) {
			this.getChildren().remove(ammoList.get(ammoList.size()-1));
			ammoList.remove(ammoList.size()-1);
			no_of_bullets --;
		}
	}
	
	public static void setAmmo(int i) {
		no_of_bullets = i;
	}
}
