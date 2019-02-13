package network;
import java.net.*;
import java.io.*;
import java.util.concurrent.*;

// Gets messages from client and puts them in a queue, for another
// thread to forward to the appropriate client.

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
			/*----------------------------------------------------------
			String recipient = myClient.readLine(); // Matches CCCCC in ClientSender.java
			System.out.println("recipient: "+recipient);
				String text = myClient.readLine();      // Matches DDDDD in ClientSender.java
				System.out.println("text: "+text);
				if (recipient != null && text != null) {
					Message msg = new Message(myClientsName, text);
					BlockingQueue<Message> recipientsQueue
					= clientTable.getQueue(recipient); // Matches EEEEE in ServerSender.java
					if (recipientsQueue != null)
						recipientsQueue.offer(msg);
					else
						Report.error("Message for non existent client "
							+ recipient + ": " + text);
				}
				else
					// No point in closing socket. Just give up.
					return;
				----------------------------------------------------------------*/
				/*ServerSocket server = new ServerSocket(1034);
				Socket s = server.accept();
				ObjectInputStream in = new ObjectInputStream(s.getInputStream());*/
				Message receivedMessage = (Message) myClient.readObject();
				System.out.println(receivedMessage.toString());
			}
		}
    catch (IOException e) {
      Report.error("Something went wrong with the client " 
                   + myClientsName + " " + e.getMessage()); 
      // No point in trying to close sockets. Just give up.
      // We end this thread (we don't do System.exit(1)).
    } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}

