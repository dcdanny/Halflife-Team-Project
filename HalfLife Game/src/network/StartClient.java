package network;
//Class for Testing only
import java.net.*;
import java.io.*;


public class StartClient {
	
	public static void main(String [] args) {
		System.out.println("Start client...");
		//Can't use 0 - 1023, Use 1024 - 65 535
		final int port = 1035;
		System.out.println("port: "+port);
		Client client = new Client(port,"dan","localhost");
		//server.getclientTable().getQueue("dan");
		
	}
}
