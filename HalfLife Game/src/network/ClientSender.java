package network;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;


//Repeatedly reads recipient's nickname and text from the user in two
//separate lines, sending them to the server (read by ServerReceiver
//thread).

public class ClientSender extends Thread {

private String nickname;
private ObjectOutputStream toServer;
private Socket server;
private BlockingQueue<Message> sendQueue;

ClientSender(BlockingQueue<Message> q, String nickname, ObjectOutputStream toServer, Socket server) {
	this.nickname = nickname;
	this.toServer = toServer;
	this.server = server;
	this.sendQueue = q;
}

public void run() {
	System.out.println("clientSender");
	// So that we can use the method readLine:
	BufferedReader user = new BufferedReader(new InputStreamReader(System.in));

	try {
		
	// Then loop forever sending messages to recipients via the server:
		//ObjectOutputStream out = new ObjectOutputStream(toServer);
		while (true) {
			Message msg = sendQueue.take(); // Matches EEEEE in ServerReceiver
			toServer.writeObject(msg);
			toServer.flush();
		}
	}
	catch (IOException e) {
		Report.errorAndGiveUp("Communication broke in ClientSender" 
				+ e.getMessage());
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}

