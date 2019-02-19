package network;

import java.net.*;
import java.io.*;

//Class to deal with creating threads for new clients
public class Server {
	boolean allowNewPlayers;
	ClientTable clientTable;
	
	public Server(int port) {
		//Initialise and open Socket
		//Boolean, true if new connections to host are allowed
		allowNewPlayers = true;
	    clientTable = new ClientTable();
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
		} 
		catch (IOException e) {
			System.out.println("Error opening port " + port);
		}
        // Start loop of waiting for new connections
		try { 
			while (allowNewPlayers) {
	          // Listen to the socket for new clients wanting to connect
	          Socket socket = serverSocket.accept(); // Matches AAAAA in Client
	          // This is so that we can use readLine():
	          //BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	          ObjectInputStream fromClient = new ObjectInputStream(socket.getInputStream());

	          //TODO:Client ID Create? Save to table of some sort?
	          	// We ask the client what its name is:
	          	//String clientName = fromClient.readLine(); // Matches BBBBB in Client
	          	String clientName = "AAAAAAAAAAAA";
	          	System.out.println(clientName + " connected");
	          
	          	// We add the client to the table:
	          	clientTable.add(clientName);
	
	          //Once client is connected, hand over to sender receiver threads
	          // We create and start a new thread to read from the client:
	          (new ServerReceiver(clientName, fromClient, clientTable)).start();

	          // We create and start a new thread to write to the client:
	          //PrintStream toClient = new PrintStream(socket.getOutputStream());
	          ObjectOutputStream toClient = new ObjectOutputStream(socket.getOutputStream());
	          (new ServerSender(clientTable.getQueue(clientName), toClient)).start();
	          
	        }
		}catch (IOException e) {
			System.out.println("Network error " + e.getMessage());
		}
	}
	public void setAllowNewPlayers(boolean allowNewPlayers) {
		this.allowNewPlayers = allowNewPlayers;
	}
	/*public Message getMsg() {
		return Message ;
	}*/
	public ClientTable getclientTable() {
		return clientTable;
	}
}
