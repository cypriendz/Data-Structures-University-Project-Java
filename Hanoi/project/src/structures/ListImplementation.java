package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * An {@code ListImplementation} is a Linked List that wraps {@link Node}s and provides useful
 * operations.
 */
public class ListImplementation<T> implements ListInterface<T> {
   Node<T> head; 
   Node<T> tail; 
   int size;
   
  
  // TODO: Add the sensible instance variables for a linked list implementation.
  public ListImplementation() {
    // TODO: Initialize instance variables.
    size = 0;
   tail = null;
   head = null;
    
  }

  /** Returns the number of nodes in this list. */
  @Override
  public int size() {
    // TODO: return the size of the list.
       return size;  
  }

  @Override
  public boolean isEmpty() {
    // TODO: Return true if the list is empty; false otherwise
    
    if (head == null) 
      return true;

      else 
        return false;
    
  }

  /**
   * Appends {@code elem} to the end of this {@code ListImplementation} and returns itself for
   * convenience.
   */
  @Override
  public ListImplementation<T> append(T elem) {
    // TODO: Append an item to the list
    if(elem == null){
      throw new NullPointerException ("Invalid Input");
    }
      //if (tail == null) {
       // throw new NullPointerException ("Tail is Null");
     // }
      if (size() == 0){
        Node<T> newNode = new Node<T>(elem, null);
        head = newNode;
        size++;
        return this;
      }
      
      if (size() == 1){
        Node<T> newNode;
        newNode = new Node<T>(elem, head);
        newNode = head.getNext();
        tail = newNode;
        size++;
        return this;
      }


      else {
      Node<T> newNode;
      newNode = new Node<T>(elem, tail);
       //newNode = tail.getNext();
      tail = newNode;
      size++;
      return this;
      }
    }
    
  

  /** Gets the {@code n}th element from this list. */
  @Override
  public T get(int n) {
    // TODO: Get an item from the list at the given index.
    Node <T> curr;
   curr = head;
   int count = 0; 
        while (curr != null) 
        { 
            if (count == n) 
                return curr.getData(); 
            count++; 
            curr = curr.getNext(); 
        } 
  
        assert(false); 
        return null; 
    } 

  /**
   * Returns an iterator over this list. The iterator does not support the {@code remove()} method.
   */
  @Override
  public Iterator<T> iterator() {
	  myIterator<T> list = new myIterator<T> (head);
		  return list;
		}
  }

