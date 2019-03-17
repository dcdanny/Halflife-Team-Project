package main;

import com.halflife.entities.RectObject;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class VictoryScreen extends StackPane {
	SpriteAnimation sp= new SpriteAnimation();
	VictoryScreen(){
		
		 RectObject bg=new RectObject(0,0,800,600,"victoryscreen",Color.valueOf("#4CAF88"));
		 Image youwon = new Image("youwon.png");
         ImageView img= new ImageView(youwon);
         formatting(img, 0, -200, 500);
        
		 
    	 Image trophyimg = new Image("trophy.png");
         ImageView trop= new ImageView(trophyimg);
         formatting(trop,-300,0,300);
    
         
         Image timeimg = new Image("time.png");
         ImageView time= new ImageView(timeimg);
       formatting(time,-50,0,300);
		 
		 sp.flip();
		 sp.resizeView(500, 150, 300);
				         
	         Image restart = new Image("restart.png");
	         ImageView resimg= new ImageView(restart);
	         formatting(resimg,-150,180,400);
	
	         RectObject resbutton=new RectObject(-150,180,250,100,"restart button",Color.TRANSPARENT);
	       
	         
	      //  resbutton.setFill(new javafx.scene.paint.ImagePattern(restart));
	         
	         resbutton.setOnMouseEntered(new EventHandler<MouseEvent>
	         () {

	             @Override
	             public void handle(MouseEvent t) {
	                resimg.setFitWidth(500);
	             }
	         });

	         resbutton.setOnMouseExited(new EventHandler<MouseEvent>
	         () {

	             @Override
	             public void handle(MouseEvent t) {
	                resimg.setFitWidth(450);
	             }
	         });
	         
	         resbutton.setOnMouseClicked((MouseEvent e) -> {
	             System.out.println("Clicked!"); // change functionality
	         });
	         Image exit= new Image("exit.png");
	         ImageView exitimg= new ImageView(exit);
	         formatting(exitimg,140,180,400);
	 
	         
	         RectObject exitbutton=new RectObject(150,180,160,100,"exit button",Color.TRANSPARENT);
	         exitbutton.setOnMouseEntered(new EventHandler<MouseEvent>
	         () {

	             @Override
	             public void handle(MouseEvent f) {
	                exitimg.setFitWidth(450);
	             }
	         });

	         exitbutton.setOnMouseExited(new EventHandler<MouseEvent>
	         () {

	             @Override
	             public void handle(MouseEvent f) {
	                exitimg.setFitWidth(400);
	             }
	         });
	         exitbutton.setOnMouseClicked((MouseEvent e) -> {
	             System.out.println("Clicked Exit!"); // change functionality
	         });
	         
	         this.getChildren().add(bg);
	         this.getChildren().add(trop);
	         this.getChildren().add(time);
	         this.getChildren().add(img);
	         this.getChildren().add(sp);
			this.getChildren().add(resimg);
			this.getChildren().add(resbutton);
			
			this.getChildren().add(exitimg);
			this.getChildren().add(exitbutton);
			
		
}
	
	
	private ImageView formatting(ImageView img ,int x,int y, int fitwidth) {
		img.setTranslateX(x);
		img.setTranslateY(y);
		img.setFitWidth(fitwidth);
		img.setPreserveRatio(true);
        img.setSmooth(true);
        img.setCache(true);
		
		return img;
	}
	}


