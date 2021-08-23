package tokenring;
import java.util.LinkedList;
import java.util.Queue;




public class WorkstationImplementation extends Workstation {
	NetworkInterface nicRef;
	Queue<Message> q;
  public WorkstationImplementation(NetworkInterface nic) {
	   q = new LinkedList<Message>();
	  if (nic == null) {
    	throw new IllegalStateException("the NIC does not exist.");
    }
    	else {
    		nicRef = nic;		
    	}
    
   
	  
  }

  public NetworkInterface getNIC() {
    // TODO: Implement the getNic() method
    return nicRef;
  }

  @Override
  public int compareTo(Workstation o) {
	 if(o == null) {
		 throw new IllegalStateException("Workstation desired doesn't exist.");
	 }
	 
	  Integer o1 = this.id;
	  Integer o2 = o.id;
    return o1.compareTo(o2);
  }

  @Override
  public boolean equals(Object obj) {
    // TODO: Implement the equals() method
	  
	   if(!(obj instanceof WorkstationImplementation)){
		  return false;   
	  }
	   WorkstationImplementation objHolder = (WorkstationImplementation) obj;
			   if(this.compareTo(objHolder) == 0) {
				   return true;
			   }
			   else {
				   return false;
			   }
				  
  
  }

  public void sendMessage(Message m) {
    q.add(m);
	  
  }

  @Override
  public void tick() {
    
	  if(!this.getNIC().hasFrame()) {
		  return;
	  }
    	
	  
	  	Frame frame1 = getNIC().read();
	  	//getNIC().write(frame1);
 
    	
	  	if(frame1.isTokenFrame()){
    		if(q.isEmpty()) {getNIC().write(frame1);}
    		
    		else if(!q.isEmpty()) {
    			DataFrame qFrame = new DataFrame(q.remove());
    			getNIC().write(qFrame);
        		incMsgSent();
    		}
	  	}

    	else if (frame1.isDataFrame()) {
    		//getNIC().write(frame1);
    		DataFrame dataFrame = (DataFrame) frame1;
    		
    		//getNIC().write(dataFrame);
    		Message myMessage = dataFrame.getMessage();
    		
    		/*case one*/if(this.id == myMessage.getReceiver()) {
    			System.out.println("message " + myMessage.toString() + " recieved by " + this.id + " ; sent by " + myMessage.getSender());
    			
    			incMsgRcvd();
    			dataFrame.setReceived(true);
    			nicRef.write(dataFrame);
    		}
    		/*case two*/else if(this.id == myMessage.getSender() && dataFrame.wasReceived()) {
    			System.out.println("Message "+myMessage.toString()+" acknowledged by sender "+myMessage.getSender()+" from destination "+ myMessage.getReceiver());
    			nicRef.write(TokenFrame.TOKEN);
    			incMsgDelivered();
    		}
    		/*case three*/ else if(this.id == myMessage.getSender() && !dataFrame.wasReceived()) {
    			System.out.println("message "+myMessage.toString()+" dropped; destination not reachable");
    			nicRef.write(TokenFrame.TOKEN);
    		}
    		/*case four*/ else if(myMessage.getReceiver() != this.id) {
    			nicRef.write(frame1);
    		}
    	}
	  }
    }
  

