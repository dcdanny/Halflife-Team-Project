package network;

import java.io.*;
import java.net.Socket;


//Repeatedly reads recipient's nickname and text from the user in two
//separate lines, sending them to the server (read by ServerReceiver
//thread).

public class ClientSender extends Thread {

private String nickname;
private PrintStream toServer;
private Socket server;

ClientSender(String nickname, PrintStream toServer, Socket server) {
	this.nickname = nickname;
	this.toServer = toServer;
	this.server = server;
}

public void run() {
	// So that we can use the method readLine:
	BufferedReader user = new BufferedReader(new InputStreamReader(System.in));

	try {
		
	// Then loop forever sending messages to recipients via the server:
		
		while (true) {
			//String recipient = user.readLine();
			
			/* ----------------------------------------------
			if (recipient.equals("quit")){
				server.println(recipient);	// Matches CCCCC in ServerReceiver
				break;
			}else{
				String text = user.readLine();
				server.println(recipient);	// Matches CCCCC in ServerReceiver

				server.println(text);		// Matches DDDDD in ServerReceiver
			}
			-------------------------------------------------*/
			System.out.println("send message! ");
			String recipient = user.readLine();
			System.out.println("aab!");
			Message messagetoSend = new Message("DANN","Message Content!");
			ObjectOutputStream out = new ObjectOutputStream(toServer);
			System.out.println("sending message: "+messagetoSend.toString());
			out.writeObject(messagetoSend);
			out.flush();
			System.out.println("sent message: "+messagetoSend.toString());
		}
	}
	catch (IOException e) {
		Report.errorAndGiveUp("Communication broke in ClientSender" 
				+ e.getMessage());
	}
}
}

