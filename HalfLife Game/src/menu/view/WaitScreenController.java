package menu.view;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
	
	public void setStage(Stage stage, Client client){
		primaryStage = stage;
		//initialise(stage, client);
		CallableExample callableexample = new CallableExample();
		try {
			callableexample.setClient(client);
			//callableexample.call();
			
		      // Create the FutureTask with Callable 
		      msga1 = new FutureTask(callableexample); 
		  
		      // As it implements Runnable, create Thread 
		      // with FutureTask 
		      Thread t = new Thread(msga1); 
		      t.start();
		      
			
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
		/*try {
			System.out.println("Get: "+ msga1.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		System.out.println("STARTGAME");
	}
	/*public void initialise(Stage stage, Client client){
		System.out.println("a");
		Pane root = null;
		try {
			root = client.waitForMessage().getPane();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("b");
		if(root != null) {
			Scene scene = new Scene(root);
			System.out.println("c");
			primaryStage.setScene(scene);
			System.out.println("d");
		}else {
			try {
				goBack();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}*/
	class CallableExample implements Callable { 
	  
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
	    	
			switch (msg){
				case "lvl1":
					
					break;
				case "lvl2":
					
					break;
				case "lvl3":
					
					break;
				case "lvl4":
					
					break;					
			}
	        // To simulate a heavy computation, 
	        // we delay the thread for some random time 
//	        Thread.sleep(2 * 1000); 
	  
	        return 45; 
	    } 
	}
	
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


}
