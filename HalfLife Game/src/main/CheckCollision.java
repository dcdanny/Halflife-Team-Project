package main;


import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import main.Game;

public class CheckCollision {

	public CheckCollision() {

	}

	static void checkIt(Shape block, Pane root) {
		boolean isCollided = false;
		  for (Node static_bloc : Game.getAllNodes(root)) {
		    if (static_bloc != block) {
		      ((Shape) static_bloc).setFill(Color.GREEN);

		      if (block.getBoundsInParent().intersects(static_bloc.getBoundsInParent())) {
		    	  isCollided = true;
		      }
		    }
		  }

		  if (isCollided) {
		    block.setFill(Color.RED);
		  } else {
		    block.setFill(Color.WHITE);
		  }
	}


}
