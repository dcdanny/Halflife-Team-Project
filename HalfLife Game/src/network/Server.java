package network;

import java.net.*;
import java.io.*;
import java.util.concurrent.*;
//Class to deal with creating threads for new clients
public class Server extends Thread {
	boolean allowNewPlayers;
	ClientTable clientTable;
	int numClients;
	int port;
	ServerSocket serverSocket;
	
	public Server(int port) {
		//Initialise and open Socket
		//Boolean, true if new connections to host are allowed
		this.port = port;
		numClients = 0;
		allowNewPlayers = true;
		clientTable = new ClientTable();
		clientTable.add("server");
		serverSocket = null;
	}
	public void run() {
		try {
			serverSocket = new ServerSocket(port);
		} 
		catch (IOException e) {
			System.out.println("Error opening port " + port);
		}
		// Start loop of waiting for new connections
		try { 
			while (allowNewPlayers) {

				// Listen to the socket waiting for new clients wanting to connect
				Socket socket = serverSocket.accept();

				//TODO:Make it so IDs can be freed up if player quits. Currently just adds one to highest player id
				String clientName = "Player" + numClients;
				numClients++;
				// We add the client to the table checking if already exists:
				if(clientTable.add(clientName)) {
					
					//BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					ObjectInputStream fromClient = new ObjectInputStream(socket.getInputStream());
					
					System.out.println(clientName + " connected");
				
					//Once client is connected, hand over to sender receiver threads
					(new ServerReceiver(clientName, fromClient, clientTable)).start();
	
					// Create and start a new thread to write to the client:
					ObjectOutputStream toClient = new ObjectOutputStream(socket.getOutputStream());
					(new ServerSender(clientTable.getQueue(clientName), toClient)).start();
				}
			}
		}catch (IOException e) {
			Report.error("Network error " + e.getMessage());
		}
	}
	public void setAllowNewPlayers(boolean allowNewPlayers) {
		this.allowNewPlayers = allowNewPlayers;
	}
	
	public void sendToAll(Message message) {
		for(String client : clientTable.showAll()) {
			BlockingQueue<Message> recipientsQueue = clientTable.getQueue(client); // Matches EEEEE in ServerSender.java
			if (recipientsQueue != null && client != "server") {
				recipientsQueue.offer(message);
				System.out.println("Sent to: "+client);//DEBUG----------------------
			}
			else
				Report.error("Can't/won't send to "+ client + ": " + message);//DEBUG----------------------
		}
	}
	public BlockingQueue<Message> getReceived() {
		//Gets message queue of messages received by the server
		String recipient = "server";
		BlockingQueue<Message> recipientsQueue = clientTable.getQueue(recipient); // Matches EEEEE in ServerSender.java

		return recipientsQueue;
	}
	public ClientTable getclientTable() {
		return clientTable;
	}
}
