package network;
import java.net.*;
import java.io.*;
import java.util.concurrent.*;

public class ServerReceiver extends Thread {
	private String myClientsName;
	private ObjectInputStream myClient;
	private ClientTable clientTable;

	public ServerReceiver(String n, ObjectInputStream c, ClientTable t) {
		myClientsName = n;
		myClient = c;
		clientTable = t;
	}

	public void run() {
		try {
			while (true) {
					Message receivedMessage = (Message) myClient.readObject();
					System.out.println("From: "+myClientsName+" "+receivedMessage.toString());
					
					//Security measure set message sender to what we know the sender is
					//(prevents forging sender and manipulating another player)
					receivedMessage.setSender(myClientsName);
					System.out.println(receivedMessage.toString());
					String recipient = "server";
					BlockingQueue<Message> recipientsQueue = clientTable.getQueue(recipient); // Matches EEEEE in ServerSender.java
					if (recipientsQueue != null)
						recipientsQueue.offer(receivedMessage);
					else
						Report.error("Message for non existent client "+ recipient + ": " + receivedMessage.toString());
					
				}
		}
		catch (IOException e) {
			clientTable.remove(myClientsName);
			Report.error("Something went wrong with the client " + myClientsName + " " + e.getMessage()); 

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


