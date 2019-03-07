package network;

import java.io.*;
import java.net.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Client extends Thread {
	ObjectOutputStream toServer;
	ObjectInputStream fromServer;
	Socket server;
	boolean clientStart;
	int port;
	String nickname;
	String hostname;
	private BlockingQueue<Message> sendQueue; 
	
	public Client(int port,String nickname,String hostname) {

		// Open sockets:
		toServer = null;
		fromServer = null;
		server = null;
		clientStart = false;
		this.port = port;
		this.nickname = nickname;
		this.hostname = hostname;
		this.sendQueue = new LinkedBlockingQueue<Message>();
		
	}
	public void run(){
	
		try {
			server = new Socket(hostname, port); // Matches AAAAA in Server.java
			//toServer = new PrintStream(server.getOutputStream());
			toServer = new ObjectOutputStream(server.getOutputStream());
			//fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
			fromServer = new ObjectInputStream(server.getInputStream());
			clientStart = true;
		}
		catch (UnknownHostException e) {
			Report.error("Unknown host: " + hostname);
		} 
		catch (IOException e) {
			Report.error("The server doesn't seem to be running " + e.getMessage());
		}
		
		if(clientStart) {
			// Create two threads to send and receive
			ClientSender sender = new ClientSender(sendQueue,nickname,toServer,server);
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
	public void sendToServer(Message message) {
		BlockingQueue<Message> recipientsQueue = sendQueue; // Matches EEEEE in ServerSender.java
		recipientsQueue.offer(message);
		System.out.println("Sent to: servaa");//DEBUG----------------------
	}
}
