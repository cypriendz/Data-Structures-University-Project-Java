package structures;
import structures.Node;
import java.util.NoSuchElementException;

public class Queue<T> implements UnboundedQueueInterface<T> {
	public Node<T> front;
	public Node<T> rear;
	public int size;
  public Queue() {
	  front = null;
	  rear  =null;
	  size = 0;
    // TODO implement the constructor
  }

  public Queue(Queue<T> other) {
	 
		 front = null;
		 rear = null;
		 size = 0;

		Node<T> copy = other.front;
		
		while (copy!= null) {
			 enqueue(copy.getData());
			 copy = copy.getNext();		
		 }
	 }
  
		 
  
  @Override
  public boolean isEmpty() {
    if (front == null) {
    	return true;
    }
    else {
    return false;
    }
  }

  @Override
  public int size() {
    // TODO implement the size method
    return size;
  }

  @Override
  public void enqueue(T element) {
    Node<T> newNode = new Node<T> (element);
    if (front == null) {
    	front = newNode;
    	rear = newNode;
    }
    else {
    	rear.setNext(newNode);
    	rear = newNode;
    }
    	size++;
  }

  @Override
  public T dequeue() throws NoSuchElementException {
    if (isEmpty()) {
    	throw new NoSuchElementException("Deque attempted on empty queue.");
    }
    
    	T element;
    	element = front.getData();
    	
    	 if (front.getNext() == null) {
    		front = null;
    		rear = null;
    	}
    	else 
    	front = front.getNext();
    	size--;
    	
    	return element;
    	
    }	
  

  @Override
  public T peek() throws NoSuchElementException {
	  if (isEmpty()) {
	      throw new NoSuchElementException("Queue is empty.");
	    }
	  else {
		  return front.getData();
	  }
  }
  @Override
  public UnboundedQueueInterface<T> reversed() {
    // TODO implement the reversed method
	 
	 if (isEmpty())
		 return new Queue<T>();
	 
	 T array[] = (T[]) new Object[size()];
	 Node<T> curr = front;
	 
	 int c = 0;
	 while(curr  != null) {
		 array[c] = curr.getData();
		 c++;
		 curr = curr.getNext();
	}
	 Queue<T> myQueue = new Queue<T>();
	 int i = array.length-1;
	 
	 	while (i >= 0 ) {
		 myQueue.enqueue(array[i]);
		 i--;
	 }
	 return myQueue;
  }
}
