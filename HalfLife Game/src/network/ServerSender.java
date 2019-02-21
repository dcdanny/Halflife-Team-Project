package network;
import java.net.*;
import java.io.*;
import java.util.concurrent.*;

// Continuously reads from message queue for a particular client,
// forwarding to the client.

public class ServerSender extends Thread {
	private BlockingQueue<Message> clientQueue;
	private ObjectOutputStream client;

	public ServerSender(BlockingQueue<Message> q, ObjectOutputStream c) {
		clientQueue = q;   
		client = c;
	}

	public void run() {
		// So that we can use the method readLine:
		BufferedReader user = new BufferedReader(new InputStreamReader(System.in));

		try {
			while (true) {
				
		        Message msg = clientQueue.take(); // Matches EEEEE in ServerReceiver
				client.writeObject(msg);
				client.flush();
		        //client.println(msg); // Matches FFFFF in ClientReceiver
			}
		}
		catch (IOException e) {
				Report.errorAndGiveUp("Communication broke in ServerSender" + e.getMessage());
		} catch (InterruptedException e) {
			// Return to infinite while loop.
		}
	}
}
