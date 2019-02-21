package network;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.*;

public class ClientTable {

  private ConcurrentMap<String,BlockingQueue<Message>> queueTable
    = new ConcurrentHashMap<String,BlockingQueue<Message>>();

  public boolean add(String nickname) {
	  if(queueTable.get(nickname) == null) {
		  queueTable.put(nickname, new LinkedBlockingQueue<Message>());
		  return true;
	  }
	  else {
		  Report.error("Error: Player with that name is already connected");
		  return false;
	  }
  }

  public void remove(String nickname) {
    queueTable.remove(nickname);
  }
  public Set<String> showAll() {
	    return queueTable.keySet();
  }
  // Returns null if the nickname is not in the table:
  public BlockingQueue<Message> getQueue(String nickname) {
    return queueTable.get(nickname);
  }
}
