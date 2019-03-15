package network;

import java.net.*;
import java.io.*;
import java.util.Enumeration;
import java.util.concurrent.*;

import main.Game;
//Class to deal with creating threads for new clients
public class Server extends Thread {
	private volatile boolean allowNewPlayers;
	static ClientTable clientTable;
	int numClients;
	int port;
	ServerSocket serverSocket;
	private ServerReceiver receiver;
	private ServerSender sender;
	//Boolean running;
	Socket socket;
	
	public Server(int port) {
		//Initialise and open Socket
		//Boolean, true if new connections to host are allowed
		this.port = port;
		numClients = 0;
		allowNewPlayers = true;
		clientTable = new ClientTable();
		clientTable.add("server");
		serverSocket = null;
		//running = true;
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
				//System.out.println("Ip: "+socket.getLocalAddress().getHostAddress());
				// Listen to the socket waiting for new clients wanting to connect
				socket = serverSocket.accept();

				//if(!allowNewPlayers) break;
				
				//TODO:Make it so IDs can be freed up if player quits. Currently just adds one to highest player id
				String clientName = "Player" + numClients;
				numClients++;
				
				// We add the client to the table checking if already exists:
				if(clientTable.add(clientName)) {
					//BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					ObjectInputStream fromClient = new ObjectInputStream(socket.getInputStream());
					
					System.out.println(clientName + " connected");
				
					//Once client is connected, hand over to sender receiver threads
					//receiver = new ServerReceiver(clientName, fromClient, clientTable);
					//receiver.start();
					(new ServerReceiver(clientName, fromClient, clientTable)).start();
	
					// Create and start a new thread to write to the client:
					ObjectOutputStream toClient = new ObjectOutputStream(socket.getOutputStream());
					//sender = new ServerSender(clientTable.getQueue(clientName), toClient);
					//sender.start();
					(new ServerSender(clientTable.getQueue(clientName), toClient, clientTable)).start();
				}else {
					Report.error("client name already in use");
				}
			}
			//start game object here
//			System.out.println("Starting game...");
//			Game game = new Game();
			
		}catch (IOException e) {
			Report.error("Network error " + e.getMessage());
		}
	}
	
	public void setAllowNewPlayers(boolean allowNewPlayers) {
		this.allowNewPlayers = allowNewPlayers;
	}
	//Returns array of all players currently connected
	public static String[] showConnected() {
		String[] outArr;
		//If client (client table wont exist) etc return empty array
		if (clientTable != null) {
			outArr =new String[ClientTable.size()];
			int i = 0;
			for(String item : clientTable.showAll()){
				outArr[i] = item;
				i++;
			}
		}else{
			outArr = new String[0];
			
		}
		return outArr;
	}
	
	public void stopServer() {
		System.out.println("Server stopping...");
		allowNewPlayers = false;
		clientTable.stopServer();
		//running = false;
		try {
			if(socket != null) {
				socket.close();
			}
			if(serverSocket != null) {
				serverSocket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Server Running: "+this.clientTable.getServerRunning());
		
	}
	
	public void sendToAll(Message message) {
		for(String client : clientTable.showAll()) {
			BlockingQueue<Message> recipientsQueue = clientTable.getQueue(client); // Matches EEEEE in ServerSender.java
			if(client == "server") {
			}else if (recipientsQueue != null && client != "server") {
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
	public String getIpAddress() {
		String currentHostIpAddress = "";
		Enumeration e;
		try {
			e = NetworkInterface.getNetworkInterfaces();
			while(e.hasMoreElements()){
			    NetworkInterface n = (NetworkInterface) e.nextElement();
			    Enumeration ee = n.getInetAddresses();
			    while (ee.hasMoreElements()){
			        InetAddress i = (InetAddress) ee.nextElement();
		            if (i.getHostAddress().indexOf(":") == -1 && !i.isLoopbackAddress() && i.isSiteLocalAddress() && currentHostIpAddress.equals("")) {
		                currentHostIpAddress = i.getHostAddress();
		            }
			    }
			}
		} catch (SocketException e1) {
			e1.printStackTrace();
			return null;
		}
		return currentHostIpAddress;
	}
	public int getPort() {
		return port;
	}
}
