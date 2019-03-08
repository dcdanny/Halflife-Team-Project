package network;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.halflife.entities.Player;

public class Message implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String sender =null;
	private String text=null;
	private String[] level=null;
	  
	  public Message(String text) {
//		  this.sender = sender;
		  this.text = text;
	  }
	  public Message( String[] level) {
//		  this.sender = sender;
		  this.level = level;
	  }
	  public void setSender(String sender) {
		  this.sender = sender;
	  } 
	  public String getSender() {
		  return sender;
	  }
	  
	  public String getText() {
		  return text;
	  }
	  
	  public String toString() {
		  return "From " + sender + ": " + text + Arrays.toString(level);
	  }
	  
}
