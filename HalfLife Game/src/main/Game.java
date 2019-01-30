package main;



import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;




public class Game extends Application {
	private Pane root= new Pane();
	private RectObject player=new RectObject(500,300,40,50,"player",Color.WHITE);
	private CountdownTimer clock=new CountdownTimer();
	private Lives heart =new Lives();
	
	private Parent createContent() {
		root.setPrefSize(800, 600);
		root.getChildren().add(player);
		root.getChildren().add(clock);
		root.getChildren().add(heart);
		root.setStyle("-fx-background-color: #4f7b8a;");
		return root;
		
	}
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Game Name is Here!! ");
		Scene scene =new Scene(createContent());
		stage.  setScene(scene);
		
		stage.show();
	}
	public static void main(String[] args) {
		launch(args);
		
	}
}
