package network;

import java.io.*;
import java.net.*;

// Gets messages from other clients via the server (by the
// ServerSender thread).

public class ClientReceiver extends Thread {

  private ObjectInputStream server;

  ClientReceiver(ObjectInputStream server) {
    this.server = server;
  }

  public void run() {
    // Print to the user whatever we get from the server:
    try {
      while (true) {
		Message receivedMessage = (Message) server.readObject();
        if (receivedMessage != null) {                
          System.out.println(receivedMessage);
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

