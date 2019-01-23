package main;



import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;




public class Window extends Application {
	private Pane root= new Pane();
	private RectObject player=new RectObject(500,300,40,50,"player",Color.BLACK);
	private Parent createContent() {
		root.setPrefSize(800, 600);
		root.getChildren().add(player);
		return root;
		
	}
	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(createContent()));
		stage.show();
	}
	public static void main(String[] args) {
		launch(args);
		
	}
}
