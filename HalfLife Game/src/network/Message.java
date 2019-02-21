package network;

public class Message implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String sender;
	  private final String text;

	  Message(String sender, String text) {
		  this.sender = sender;
		  this.text = text;
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

}
