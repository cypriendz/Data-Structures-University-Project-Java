package structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class StudentArrayHeap<P, V> extends AbstractArrayHeap<P, V> {
 
	  public StudentArrayHeap (Comparator<P> comparator) {
	  super(comparator);
	  }

	

	  /**
	   * Given an index of some "node" returns the index to that "nodes" left child.
	   *
	   * @param index an index in this {@link AbstractArrayHeap}
	   * @return the index of the specified "nodes" left child
	   * @throws IndexOutOfBoundsException if {@code index} is less than 0
	   */
	  public int getLeftChildOf(int index) {
		  if(index < 0) {throw new IndexOutOfBoundsException();}
		  
		  return (index*2) + 1;
		 
	  }

	  /**
	   * Given an index of some "node" returns the index to that "nodes" right child.
	   *
	   * @param index a "nodes" index
	   * @return the index of the specified "nodes" right child
	   * @throws IndexOutOfBoundsException if {@code index} is less than 0
	   */
	  public int getRightChildOf(int index) {
 if(index < 0) {throw new IndexOutOfBoundsException();}
		  
		  return (index*2) + 2;
	  }

	  /**
	   * Given an index of some "node" returns the index to that "nodes" parent.
	   *
	   * @param index a "nodes" index
	   * @return the index of the specified "nodes" parent
	   * @throws IndexOutOfBoundsException if {@code index} is less than 1
	   */
	  public int getParentOf(int index) {
if(index < 1) {throw new IndexOutOfBoundsException();}
		  
		  return (index-1)/2;
	  }
	  

	  /**
	   * This results in the entry at the specified index "bubbling up" to a location such that the
	   * property of the heap are maintained. This method should run in O(log(size)) time.
	   *
	   * <p>Note: When add is called, an Entry is placed at the end of the internal array and then this
	   * method is called on that index.
	   *
	   * @param index the index to bubble up
	   */
	  protected void bubbleUp(int index) {
		  Entry<P, V> interest;
		  interest = heap.get(index);
		  
		  int hole = index;
		  int parent = (hole-1)/2;
		  while(hole > 0 && comparator.compare(interest.getPriority(), heap.get(parent).getPriority()) > 0) {
			  heap.set(hole, heap.get(parent));
			  hole = parent;
			  parent = (parent-1)/2;
			  }
		  heap.set(hole, interest);
		  
	  }
	  

	  /**
	   * This method results in the entry at the specified index "bubbling down" to a location such that
	   * the property of the heap are maintained. This method should run in O(log(size)) time.
	   *
	   * <p>Note: When remove is called, if there are elements remaining in this {@link
	   * AbstractArrayHeap} the bottom most element of the heap is placed at the 0th index and
	   * bubbleDown(0) is called.
	   *
	   * @param index
	   */
	  protected void bubbleDown(int index) {
		  Entry<P, V> interest;
		  interest = heap.get(0);
		  int hole = 0;
		  int rightNode = 2;
		  int leftNode = 1;
		  int larger;
		  
		  while(rightNode <= heap.size()-1 ) {
			  if(comparator.compare(heap.get(leftNode).getPriority(), heap.get(rightNode).getPriority()) > 0  && rightNode <= heap.size()-1){
				  larger = leftNode;
			  }
			  
			  else {
				  larger = rightNode;
			  }
			  
			  if(comparator.compare(interest.getPriority(), heap.get(larger).getPriority()) >= 0) {
				  break;
			  }
			  heap.set(hole, heap.get(larger));
			  hole = larger;
			  rightNode = (hole*2)+2;
			  leftNode = (hole*2)+1;
		  }
		heap.set(hole,interest);
		  
		  
	  }
	}
