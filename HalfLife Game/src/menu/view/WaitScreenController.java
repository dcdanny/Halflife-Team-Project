package menu.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Game;
import main.Level_Info;
import network.Client;
import network.Message;
import network.Server;

public class WaitScreenController {
	
	private Stage primaryStage;
	@FXML private TextField ipAddrInput;
	private FutureTask msga1;
	private Client client;
	ArrayList<Node> msgR;
	
	public void setStage(Stage stage, Client client){
		this.client = client;
		primaryStage = stage;
		//initialise(stage, client);
		//CallableExample callableexample = new CallableExample();
		try {
			//callableexample.setClient(client);
			//callableexample.call();
			
		      // Create the FutureTask with Callable 
		      //msga1 = new FutureTask(callableexample); 
			
			
		      // As it implements Runnable, create Thread 
		      // with FutureTask
			
			 Task<Integer> task = new Task<Integer>() {
		         @Override protected Integer call() throws Exception {
		        	 msgR = client.waitForMessage().getNodes();
		             System.out.println("msgRCeiVeDD " + msgR);
		             return 0;
		         }
		     };
			
		      Thread t = new Thread(task); 
		      t.start();
		      
		      task.setOnSucceeded(event -> startGame(msgR));

		     System.out.println("m");

			//System.out.println("Get: "+ msga1.get());
			System.out.println("n");
		      
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@FXML
	private void initialize(){
		waitConnection();	
	}
	public void waitConnection() {	
		System.out.println("STARTGAME");
	}
	
	/*Object result;
	Task<Integer> task2 = new Task<Integer>() {;
	task2.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
	    @Override
	    public void handle(WorkerStateEvent t) {
	        result = task2.getValue();
	    }
	});
	}*/


	
	/*Task<Integer> task = new Task<Integer>() {
	    @Override protected Integer call() throws Exception {
	    	String msg = null;
	    	msg = client.waitForMessage().getText();

	        return iterations;
	    }
	};*/
	/*class CallableExample implements Callable { 
	  
		private Client client;
		
		public void setClient(Client c) {
			client = c;
		}
		
	    public Object call() throws Exception 
	    { 
	    	String msg = null;
	        // Create random number generator 
	    	System.out.println("heyyyy neww threaddd");
//	    	Thread.sleep(2 * 1000);
	    	System.out.println("ha");
	    	msg = client.waitForMessage().getText();
            /*Platform.runLater(() -> {
            
			switch (msg){
				case "lvl1":
					System.out.println("here 1234567890");
//					FXMLLoader loader = new FXMLLoader(getClass().getResource("mainmenu.fxml"));
//					Pane levelMenu = loader.load();
//					MainMenuController controller = loader.getController();
//					controller.setStage(primaryStage);
//					Scene scene = new Scene(levelMenu);
//					primaryStage.setScene(scene);
//					primaryStage.show();
					
					
					
					Game game = new Game();
					game.setCurrentLevel(Level_Info.LEVEL1);
				try {
					game.start(primaryStage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					primaryStage.show();
					break;
				case "lvl2":
					
					break;
				case "lvl3":
					
					break;
				case "lvl4":
					
					break;					
			}
            });*//*
	    	
	        // To simulate a heavy computation, 
	        // we delay the thread for some random time 
//	        Thread.sleep(2 * 1000); 
	  
	        return 45; 
	    } 
	}*/
	
	// The "Disconnect" Button
	@FXML
	private void goBack() throws IOException {
		//client.stopClient();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("joinmenu.fxml"));
		Pane joinmenu = loader.load();
		JoinMenuController controller = loader.getController();
		controller.setStage(primaryStage);
		Scene scene = new Scene(joinmenu);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	private void startGame(ArrayList<Node> msgR2){
//		System.out.println("message:"+msgR2);
		Pane root = new Pane();
		for (Node node : msgR2) {
			root.getChildren().add(node);
		}				
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}

}
