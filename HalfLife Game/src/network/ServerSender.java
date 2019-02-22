package network;
import java.net.*;
import java.io.*;
import java.util.concurrent.*;

// Continuously reads from message queue for a particular client,
// forwarding to the client.

public class ServerSender extends Thread {
	private BlockingQueue<Message> clientQueue;
	private ObjectOutputStream client;
	private volatile boolean running = true;
	private ClientTable clientTable;
	
	public ServerSender(BlockingQueue<Message> q, ObjectOutputStream c, ClientTable t, Boolean r) {
		clientQueue = q;   
		client = c;
		clientTable = t;
		running = r;
	}

	public void run() {
		// So that we can use the method readLine:
		BufferedReader user = new BufferedReader(new InputStreamReader(System.in));

		try {
			while (clientTable.getServerRunning()) {
		        Message msg = clientQueue.take(); // Matches EEEEE in ServerReceiver
				client.writeObject(msg);
				client.flush();
			}
		}
		catch (IOException e) {
				Report.error("Communication broke in ServerSender" + e.getMessage());
		} catch (InterruptedException e) {
			// Return to infinite while loop.
		}
	}
	
}
