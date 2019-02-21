package network;

import java.io.*;
import java.net.Socket;


//Repeatedly reads recipient's nickname and text from the user in two
//separate lines, sending them to the server (read by ServerReceiver
//thread).

public class ClientSender extends Thread {

private String nickname;
private ObjectOutputStream toServer;
private Socket server;

ClientSender(String nickname, ObjectOutputStream toServer, Socket server) {
	this.nickname = nickname;
	this.toServer = toServer;
	this.server = server;
}

public void run() {System.out.println("clientSender");
	// So that we can use the method readLine:
	BufferedReader user = new BufferedReader(new InputStreamReader(System.in));

	try {
		
	// Then loop forever sending messages to recipients via the server:
		//ObjectOutputStream out = new ObjectOutputStream(toServer);
		while (true) {

			System.out.println("----- Message Object -----");
			System.out.print("From: ");
			String namefrom = user.readLine();
			System.out.print("Content: ");
			String msgcontent = user.readLine();
			Message messagetoSend = new Message(namefrom,msgcontent);

			System.out.println("----- ----- ----- -----");
			toServer.writeObject(messagetoSend);
			toServer.flush();
			System.out.println("sent message: "+messagetoSend.toString());
		}
	}
	catch (IOException e) {
		Report.errorAndGiveUp("Communication broke in ClientSender" 
				+ e.getMessage());
	}
}
}

