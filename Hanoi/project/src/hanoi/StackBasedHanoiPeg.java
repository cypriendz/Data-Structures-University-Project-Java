package hanoi;

import structures.LinkedStack;
import structures.Node;
import structures.StackInterface;

import java.util.NoSuchElementException;

import hanoi.HanoiRing;

/** A {@link StackBasedHanoiPeg} is a {@link HanoiPeg} backed by a {@link LinkedStack} */
public class StackBasedHanoiPeg implements HanoiPeg {
	  Node<HanoiRing> top;
	  
	public StackBasedHanoiPeg() {
		
		top = null;
		
  }

  @Override
  public void addRing(HanoiRing ring) throws IllegalHanoiMoveException {
    // TODO: Implement the addRing method
	  
	  if(ring == null) {
		  throw new NullPointerException("The ring was null");
	  }
	  
	  else if (top == null) {
		  top = new Node<HanoiRing>(ring, top);
		  
	  }
	  
	  else if (top.getData().getSize() <= ring.getSize()) {
	      throw new IllegalHanoiMoveException("Size of ring is larger than prior.");
	    }
	  
	  else {
		  top = new Node<HanoiRing>(ring, top);
		  
	  }
	   
  }

  @Override
  public HanoiRing remove() throws IllegalHanoiMoveException {
    // TODO: Implement the remove method
    if(top == null) {
    	throw new IllegalHanoiMoveException("There are no rings on peg.");
    }
    else {
    	Node<HanoiRing> oldTop = top;
        top = top.getNext();
        return oldTop.getData();
    }
  }

  @Override
  public HanoiRing getTopRing() throws IllegalHanoiMoveException {
	  if (top == null) {
		  throw new IllegalHanoiMoveException("There are no rings on peg.");
	  }
	  else {
	  	return top.getData();
	  }
  }

  @Override
  public boolean hasRings() {
    // TODO: Implement the hasRings method
	  if (top == null) {
		  return false;
	  }
	  else {
	  return true;
	  }
  }
}