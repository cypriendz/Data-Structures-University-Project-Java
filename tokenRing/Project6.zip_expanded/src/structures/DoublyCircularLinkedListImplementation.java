package structures;

public class DoublyCircularLinkedListImplementation<T extends Comparable<T>>
    implements DoublyCircularLinkedList<T> {
	DLLNode<T> head;
	DLLNode<T> tail;
	DLLNode<T> currHead; 
	DLLNode<T> currTail;
	int numElem;
  public DoublyCircularLinkedListImplementation() {
    // TODO: implement the constructor
	  head = null;
	  tail = null;
	  currHead = null;
	  currTail = null;
	  numElem = 0;
	  
  }

  @Override
  public int size() {
    // TODO: implement the size method.
    return numElem;
  }

  @Override
  public void add(T element) {
    // TODO: Implement the add method!
	  DLLNode<T> newNode = new DLLNode<T>(element);
	  if (numElem == 0) {
		  newNode.setBack(newNode);
		  newNode.setForward(newNode);
		  head = newNode;
		  tail = newNode;
		  currHead= newNode;
		  currTail = newNode;
		  numElem++;
	  }
	  else if (numElem == 1) {
		  head.setForward(newNode);
		  head.setBack(newNode);
		  newNode.setForward(head);
		  newNode.setBack(head);
		  tail = newNode;
		  currTail = newNode;
	 	  numElem++;
		  
	  }
	  else {
		  tail.setForward(newNode);
		  newNode.setBack(tail);
		  newNode.setForward(head);
		  head.setBack(newNode);
		  tail = newNode;
		  currTail = newNode;
		  numElem++;
	  }
  }

  @Override
  public boolean remove(T element) {
    // TODO: implemement the remove method!
    if(size() <=0) {
    	return false;
    }
    else if(size() == 1) {
		 head = null;
		 tail = null;
		 currHead= null;
		 currTail = null;
		 numElem--;
		 return true;
	 }
    else if(numElem == 2) {
    	tail = head;
    	currTail = head;
    	head.setForward(head);
    	head.setBack(head);
    	numElem--;
    	return true;
    }
    else {
    tail.getBack().setForward(head);
    head.setBack(tail.getBack());
    tail = tail.getBack();
    currTail = tail;
    numElem--;
    return true;
    
    }
    
  }


  @Override
  public boolean contains(T element) {
    // TODO: Implement the contains() method
	  if (numElem == 0) {
		  return false;
	  }

	  else {
		  do {
			  if (currHead.getInfo().equals(element)) {
				  return true;
			  }
			  currHead = currHead.getForward();
		  }
		  while (currHead != head);
		  return false;
		  }
	  }
	 
	 
   
  

  @Override
  public T get(T element) {
	
	  
	  if (numElem == 0) {
		 return null;
	  }

	  else {
		  do {
			  if (currHead.getInfo().equals(element)) {
				  return currHead.getInfo();
			  }
			  currHead = currHead.getForward();
		  }
		  while (currHead != head);
		  return null;
		  }
	  }
  		   
  

  @Override
  public void reset() {
   if(numElem == 0) {
	   throw new IllegalStateException("List is empty.");
   }
	  currHead = head;
    // TODO: Implement the reset() method
  }

  @Override
  public T getNext() {
	  T holder;
	  if(numElem == 0) {
	   throw new IllegalStateException("List is empty.");
   }
	  
	  if (currHead == tail) {
	   holder = currHead.getInfo();
	   currHead = head;
	   return holder;
   }
	  else {
	  holder = currHead.getInfo();
	  currHead = currHead.getForward();
	  return holder;
	  }
	  
	  
  } 
	  

  @Override
  public T getPrevious() {
	  if(numElem == 0) {
		   throw new IllegalStateException("List is empty.");
	   }
	  
	  if(currHead == head) {
		  currHead = tail;
		  return currHead.getInfo();
	  }
	
	  else {
		  currHead = currHead.getBack();
		  return currHead.getInfo();
	  }
	  } 
}
