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
	private static int ammo=10;
	private ArrayList<ImageView>ammoList=new ArrayList<ImageView>();
	
	public Ammo(){ 
		super();	
	    Image image = null;
	    try {
	    	image = new Image(new FileInputStream("res/ammo.png"));
	    } catch (FileNotFoundException e) {
		e.printStackTrace();
	    }      

	    List<ImageView> ammunition = new ArrayList<ImageView>();
	    for (int i = 0; i < 10; i++) {
	    	ammunition.add(new ImageView(image));
	    }

	    setAmmoImages(ammunition);
	    
	    for (int k = 0; k < 10; k++) {
	    	this.getChildren().add(ammunition.get(k));
	    	ammoList.add(ammunition.get(k));
	    }	   	   
     
}
	
	
	public void setAmmoImages(List<ImageView> ammunition) {
		for (int i = 0; i < 10; i++) {
	    	ammunition.get(i).setX(i*20 + 30);
	    	ammunition.get(i).setY(0);
	    	ammunition.get(i).setFitHeight(75);
	    	ammunition.get(i).setFitWidth(75);
	    	ammunition.get(i).setPreserveRatio(true);
	    }
	}

	public void lostBullet() {
		
		if (ammo>0) {
			this.getChildren().remove(ammoList.get(ammoList.size()-1));
			ammoList.remove(ammoList.size()-1);
			ammo=ammo-1;
		}
	}
	
	public static void setAmmo(int i) {
		ammo = i;
	}
}
