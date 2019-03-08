package network;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.halflife.entities.Player;

import javafx.scene.layout.Pane;

public class Message implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String sender =null;
	private String text=null;
	private Pane root=null;
	  
	  public Message(String text) {
//		  this.sender = sender;
		  this.text = text;
	  }
	  public Message(Pane root) {
//		  this.sender = sender;
		  this.root = root;
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
	  public Pane getPane() {
		  return root;
	  }
	  
	  public String toString() {
		  return "From " + sender + ": " + text;
	  }
	  
}
