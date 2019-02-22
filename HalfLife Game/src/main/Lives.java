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
	private int lives=3;
	private ArrayList<ImageView>heartlist=new ArrayList<ImageView>();
	Lives(){
	 

		super();
		
	 Image image = null;
	try {
		image = new Image(new FileInputStream("res/heart-1.png.png"));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
     
	
	
     //Setting the image view 
	//  this.setImage(image);
	
	List<ImageView> hearts = new ArrayList<ImageView>();
    for (int i = 0; i < lives; i++) {
    	hearts.add(new ImageView(image));
    }
    
    for (int i = 0; i < lives; i++) {
    	hearts.get(i).setX(i*50 + 25);
    	hearts.get(i).setY(50);
    	hearts.get(i).setFitHeight(100);
    	hearts.get(i).setFitWidth(100);
    	hearts.get(i).setPreserveRatio(true);
    	this.getChildren().add(hearts.get(i));
    	heartlist.add(hearts.get(i));
    }

   
//  VBox root= new VBox();
 // root.getChildren().add(imageView);
 // Scene scene =new Scene(new Group());

    // this.getChildren().remove(hearts2);
    // lostlife();
    // lostlife();
  //   lostlife();
  
     
     
   
     
}
	//Removes lives from display when called
	void lostlife() {
		
		if (lives>0) {
			this.getChildren().remove(heartlist.get(heartlist.size()-1));
			heartlist.remove(heartlist.size()-1);
			lives=lives-1;
		}
	}
}
