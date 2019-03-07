package main;


import com.halflife.entities.RectObject;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import main.Game;

public class CheckCollision {

	private  boolean collided;
	
	public CheckCollision() {

	}

	public  RectObject checkForCollision(Shape block, Pane root) {
		collided = false;
		  for (Node static_bloc : Game.getAllNodes(root)) {
		     RectObject b = (RectObject) static_bloc;
		     if (!b.getType().equals(GameConstants.TYPE_PLAYER)) {
		    	 if (static_bloc != block && b.getType() != GameConstants.TYPE_EDGE_PLATFORM_RIGHT && b.getType() != GameConstants.TYPE_EDGE_PLATFORM_LEFT) {

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
		     }
		return null;
		    
	}

	public  boolean getCollided() {
		return collided;
	}
//

}
