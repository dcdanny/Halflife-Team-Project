package main;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class WriteFile {

	private String path = System.getenv("APPDATA");
	private boolean append = false;
	
	public WriteFile(String _path, boolean _append) {
		path = _path;
		append = _append;
	}
	
	public WriteFile(boolean _append) {
		append = _append;
	}
	
	public void write(String text) throws IOException {
		FileWriter writer = new FileWriter(path + "/levels.txt", append);
		PrintWriter pWriter = new PrintWriter(writer);
		
		pWriter.printf( "%s" + "%n" , text);
		pWriter.close();
	}
	
}
