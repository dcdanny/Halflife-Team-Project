package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ReadLevel {
	private String path = System.getenv("APPDATA");
	private String[] userLevel;
	/**
	 * 1st Constructor for the WriteFile class
	 * @param _path The path to write to 
	 * @param _append Whether or not the writer should append any data to the end of an existing file or overwrite it
	 */
	public ReadLevel(String _path) {
		path = _path;
	}
	
	/**
	 * 2nd Constructor for the WriteFile class
	 * No path needed in this one, uses the default APPDATA location
	 * @param _append Whether or not the writer should append any data to the end of an existing file or overwrite it
	 */
	public ReadLevel() {
	}
	
	/**
	 * Reads a user submitted level 
	 * @throws IOException
	 */
	public String[] read() throws IOException {
		FileReader reader = new FileReader(path + "/userLevel.txt");
		BufferedReader r =  new BufferedReader(reader);
		
		String[] level = new String[6];
		for (int i = 0; i < 6; i++) {
			level[i] = r.readLine();
		}
		
		return level;
	}
	
	
	public String[] getLevel() {
		try {
			if (validateLevel(read()))
				return userLevel;
		} catch (IOException e) {
			return new String[] {"Level contains errors"};
		}
		return new String[] {"Level contains errors"};
	}
	
	public boolean validateLevel(String[] level) {
		if (level.length !=6)
			return false;
		
		//Row Length
		int rowLength = level[0].length();
		for (int i = 1; i < level.length; i++) {
			if (level[i].length() != rowLength)
				return false;
		}
		
		String topRow = level[0].substring(1);
		for (char c : topRow.toCharArray()) {
			if (c != '0')
				return false; // Air in top row
		}		

		String bottomRow = level[5].substring(1);
		for (char c : bottomRow.toCharArray()) {
			if (c != '3')
				return false; // No floor
		}
		
		boolean hasStartPlat = false;
		for (int i = 1; i < 5; i++) {
			if (level[i].charAt(1) == '1' )
				hasStartPlat = true;
		}
		if (!hasStartPlat)
			return false;
		
		boolean hasGoal = false;
		for (int i = 1; i < 5; i++) {
			if (level[i].substring(1).contains("2")){
				hasGoal = true;
			}
		}
		if (!hasGoal)
			return false;
		
		//Checking all 4 at start of each string
		for (int i = 0; i < level.length; i++) {
			if (level[i].charAt(0) != '4')
				return false;
		}
		
		userLevel = level;
		return true;
	}
}
