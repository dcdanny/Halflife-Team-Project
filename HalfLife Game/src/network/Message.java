package network;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.halflife.entities.Player;
import com.halflife.entities.RectObject;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class Message implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String sender =null;
	private String text=null;
	private String[] level=null;
	private Double x=null;
	private Double y=null;

	  
	  public Message(String text) {
//		  this.sender = sender;
		  this.text = text;
	  }	  

	public Message(String[] level) {
		this.setLevel(level);
	}
	public Message(Double x, Double y) {
		this.setX(x);
		this.setY(y);
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

	public String[] getLevel() {
		return level;
	}

	private void setLevel(String[] level) {
		this.level = level;
	}

	public Double getX() {
		return x;
	}

	private void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	private void setY(Double y) {
		this.y = y;
	}

}
