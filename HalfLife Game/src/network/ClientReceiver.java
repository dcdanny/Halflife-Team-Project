package network;

import java.io.*;
import java.net.*;
import java.util.concurrent.BlockingQueue;

// Gets messages from other clients via the server (by the
// ServerSender thread).

public class ClientReceiver extends Thread {

  private ObjectInputStream server;
  private BlockingQueue<Message> recipientsQueue;

  ClientReceiver(BlockingQueue<Message> q, ObjectInputStream server) {
    this.server = server;
    recipientsQueue = q;
  }

  public void run() {
    // Print to the user whatever we get from the server:
    try {
      while (true) {
		Message receivedMessage = (Message) server.readObject();
		receivedMessage.setSender("server");
        if (receivedMessage != null) {     
        	recipientsQueue.offer(receivedMessage);
          /*System.out.println(receivedMessage);*/
        }
        else
          Report.errorAndGiveUp("Server seems to have died"); 
      }
    }
    catch (IOException e) {
      Report.errorAndGiveUp("Server seems to have died " + e.getMessage());
    } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}

