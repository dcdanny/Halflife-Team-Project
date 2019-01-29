package network;

import java.net.*;
import java.io.*;


public class Server {

	public Server(int port) {
	    ServerSocket serverSocket = null;
	    try {
	        serverSocket = new ServerSocket(port);
	      } 
	      catch (IOException e) {
	        System.out.println("Couldn't listen on port " + port);
	      }
	
	}
}
