package network;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.halflife.entities.Player;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class Message implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String sender =null;
	private String text=null;

	private ArrayList<Node> nodes;
	  
	  public Message(String text) {
//		  this.sender = sender;
		  this.text = text;
	  }

	  public Message(ArrayList<Node> allNodes) {
		  this.setNodes(allNodes);
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
		  return "From " + sender + ": " + text;
	  }

	public ArrayList<Node> getNodes() {
		return nodes;
	}

	private void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}
	  
}
