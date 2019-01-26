package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

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
   ImageView hearts0= new ImageView(image);
   ImageView hearts1= new ImageView(image);
   ImageView hearts2= new ImageView(image);
     
     //Setting the position of the image 
    hearts0.setX(0); 
    hearts0.setY(50); 
    hearts1.setX(50); 
    hearts1.setY(50); 
    hearts2.setX(100); 
    hearts2.setY(50); 
     //setting the fit height and width of the image view 
     hearts0.setFitHeight(100); 
    hearts0.setFitWidth(100); 
    hearts1.setFitHeight(100); 
    hearts1.setFitWidth(100); 
    hearts2.setFitHeight(100); 
    hearts2.setFitWidth(100); 
    
     //Setting the preserve ratio of the image view 
    
   hearts0.setPreserveRatio(true); 
   hearts1.setPreserveRatio(true);
   hearts2.setPreserveRatio(true);
   this.getChildren().add(hearts0);
   this.getChildren().add(hearts1);
   this.getChildren().add(hearts2);
   
//  VBox root= new VBox();
 // root.getChildren().add(imageView);
 // Scene scene =new Scene(new Group());
   heartlist.add(hearts0);
   heartlist.add(hearts1);
   heartlist.add(hearts2);
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
