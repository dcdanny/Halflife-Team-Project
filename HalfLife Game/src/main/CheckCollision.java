package main;


import com.halflife.entities.RectObject;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import main.Game;

public class CheckCollision {

	private static boolean collided;
	
	public CheckCollision() {

	}

	public static RectObject checkForCollision(Shape block, Pane root) {
		collided = false;
		  for (Node static_bloc : Game.getAllNodes(root)) {
		    if (static_bloc != block) {

		      if (block.getBoundsInParent().intersects(static_bloc.getBoundsInParent())) {
		    	  
		    	  collided = true;
		    	  return (RectObject) static_bloc;
		      }
		    }
		  }

		  if (getCollided()) {
		    block.setFill(Color.RED);
		  } else {
		    block.setFill(Color.WHITE);
		  }
		  return null;
	}
	
	public static boolean getCollided() {
		return collided;
	}


}
