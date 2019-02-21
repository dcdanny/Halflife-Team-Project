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
		//ObjectOutputStream out;
		/*try {
			out = new ObjectOutputStream(client);
		} catch (IOException e1) {
			Report.errorAndGiveUp("Communication broke in ServerSender" 
					+ e1.getMessage());
		}*/
		//while (true) {
		try {
			//out = new ObjectOutputStream(client);
			while (true) {
		    	/*System.out.println("----- Message Object -----");
				System.out.print("From: ");
				String namefrom = user.readLine();
				System.out.print("Content: ");
				String msgcontent = user.readLine();
				Message messagetoSend = new Message(namefrom,msgcontent);
		
				System.out.println("----- ----- ----- -----");
				client.writeObject(messagetoSend);
				client.flush();
				System.out.println("sent message: "+messagetoSend.toString());
					*/
				
		        Message msg = clientQueue.take(); // Matches EEEEE in ServerReceiver
				client.writeObject(msg);
				client.flush();
				System.out.println("sent message: "+msg.toString());
		        //client.println(msg); // Matches FFFFF in ClientReceiver
			}
		}
		//catch (InterruptedException e) {
				// Do nothing and go back to the infinite while loop.
		//}
		catch (IOException e) {
				Report.errorAndGiveUp("Communication broke in ServerSender" + e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
