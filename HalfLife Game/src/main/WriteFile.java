package main;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class WriteFile {

	private String path = System.getenv("APPDATA");
	private boolean append = false;
	
	/**
	 * 1st Constructor for the WriteFile class
	 * @param _path The path to write to 
	 * @param _append Whether or not the writer should append any data to the end of an existing file or overwrite it
	 */
	public WriteFile(String _path, boolean _append) {
		path = _path;
		append = _append;
	}
	
	/**
	 * 2nd Constructor for the WriteFile class
	 * No path needed in this one, uses the default APPDATA location
	 * @param _append Whether or not the writer should append any data to the end of an existing file or overwrite it
	 */
	public WriteFile(boolean _append) {
		append = _append;
	}
	
	/**
	 * Writes level completion data to the given path
	 * @param text The text to write into the file
	 * @throws IOException
	 */
	public void write(String text) throws IOException {
		FileWriter writer = new FileWriter(path + "/levels.txt", append);
		PrintWriter pWriter = new PrintWriter(writer);
		
		pWriter.printf( "%s" + "%n" , text);
		pWriter.close();
	}
	
}
