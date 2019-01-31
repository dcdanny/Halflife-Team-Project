  package main;



import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
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
	private ArrayList<Node> platforms=new ArrayList<Node>();
	private int levelWidth;
	
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
		setUpLevel();
		stage.setTitle("Game Name is Here!! ");
		Scene scene =new Scene(createContent());
		stage.  setScene(scene);
		
		stage.show();
	}
	private void setUpLevel() {
		levelWidth= Level_Info.LEVEL1[0].length()*60;
		
		for (int i = 0; i < Level_Info.LEVEL1.length; i++) {
			String line=Level_Info.LEVEL1[i];
			for (int j = 0; j < line.length(); j++) {
				switch(line.charAt(j)) {
				case '0':
					break;
				case '1':
				Node platform =new RectObject(j*150,i*200,150,30,"plat",Color.LIGHTSKYBLUE);
				root.getChildren().add(platform);
				platforms.add(platform);
				break; 
					
				}
			}
		}
		
	}
	public static void main(String[] args) {
		launch(args);
		
	}
}
