package main;


import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import main.Game;

public class CheckCollision {

	private static boolean collided;
	
	public CheckCollision() {

	}

	public static void checkForCollision(Shape block, Pane root) {
		collided = false;
		  for (Node static_bloc : Game.getAllNodes(root)) {
		    if (static_bloc != block) {
		      ((Shape) static_bloc).setFill(Color.GREEN);

		      if (block.getBoundsInParent().intersects(static_bloc.getBoundsInParent())) {
		    	  collided = true;
		      }
		    }
		  }

		  if (getCollided()) {
		    block.setFill(Color.RED);
		  } else {
		    block.setFill(Color.WHITE);
		  }
	}
	
	public static boolean getCollided() {
		return collided;
	}


}
