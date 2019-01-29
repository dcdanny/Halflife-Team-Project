package network;
//Class for Testing only
import java.net.*;
import java.io.*;

public class startServer {

	public static void main(String [] args) {
		System.out.println("Start server...");
		//Can't use 0 - 1023, Use 1024 - 65 535
		final int port = 1034;
		Server server = new Server(port);
		
	}
}
