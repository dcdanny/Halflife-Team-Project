package network;

import java.io.*;
import java.net.*;

class Client {

	public Client(int port,String nickname,String hostname) {

    // Check correct usage:
    /*if (args.length != 2) {
      Report.errorAndGiveUp("Usage: java Client user-nickname server-hostname");
    }else if (args[0].equals("quit")){
	Report.errorAndGiveUp("Can't use 'quit' as a user-nickname");
    }*/

    // Initialize information:
    /*String nickname = args[0];
    String hostname = args[1];*/

    // Open sockets:
    PrintStream toServer = null;
    BufferedReader fromServer = null;
    Socket server = null;

    try {
      server = new Socket(hostname, port); // Matches AAAAA in Server.java
      toServer = new PrintStream(server.getOutputStream());
      fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
    } 
    catch (UnknownHostException e) {
      Report.errorAndGiveUp("Unknown host: " + hostname);
    } 
    catch (IOException e) {
      Report.errorAndGiveUp("The server doesn't seem to be running " + e.getMessage());
    }

    // Tell the server what my nickname is:
    //toServer.println(nickname); // Matches BBBBB in Server.java
     
    // Create two client threads of a diferent nature:
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
