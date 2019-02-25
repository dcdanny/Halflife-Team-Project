package network;
//Class for Testing only
import java.net.*;
import java.io.*;


public class StartClient {
	
	public static void main(String [] args) throws IOException {
		System.out.println("Start client...");
		BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
		String connectAddress;
		connectAddress = user.readLine();
		//Can't use 0 - 1023, Use 1024 - 65 535
		final int port = 1035;
		System.out.println("port: "+port);
		Client client = new Client(port,"dan",connectAddress);
		client.start();
		//server.getclientTable().getQueue("dan");
		
	}
}
