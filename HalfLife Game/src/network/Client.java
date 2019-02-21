package network;

import java.io.*;
import java.net.*;

class Client {

	public Client(int port,String nickname,String hostname) {

    // Open sockets:
    ObjectOutputStream toServer = null;
    ObjectInputStream fromServer = null;
    Socket server = null;

    try {
      server = new Socket(hostname, port); // Matches AAAAA in Server.java
      //toServer = new PrintStream(server.getOutputStream());
      toServer = new ObjectOutputStream(server.getOutputStream());
      //fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
      fromServer = new ObjectInputStream(server.getInputStream());
    } 
    catch (UnknownHostException e) {
      Report.errorAndGiveUp("Unknown host: " + hostname);
    } 
    catch (IOException e) {
      Report.errorAndGiveUp("The server doesn't seem to be running " + e.getMessage());
    }
     
    // Create two threads to send and receive
    ClientSender sender = new ClientSender(nickname,toServer,server);
    ClientReceiver receiver = new ClientReceiver(fromServer);

    // Run them in parallel:
    sender.start();
    receiver.start();
    
    // Wait for them to end and close sockets.
    try {
      sender.join();
      toServer.close();
      receiver.join();
      fromServer.close();
      server.close();
    }
    catch (IOException e) {
      Report.errorAndGiveUp("Something wrong " + e.getMessage());
    }
    catch (InterruptedException e) {
      Report.errorAndGiveUp("Unexpected interruption " + e.getMessage());
    }
  }
}
