package structures;

import comparators.IntegerComparator;

import comparators.ReverseIntegerComparator;

import java.util.Comparator;
import java.util.Iterator;

public class MinQueue<V> implements PriorityQueue<Integer, V> {
  // TODO: Implement all abstract methods from PriorityQueue.
StudentArrayHeap <Integer, V> myHeap;
	
	public MinQueue() {
		
		ReverseIntegerComparator myComparator = new ReverseIntegerComparator();
		myHeap = new StudentArrayHeap<Integer,V>(myComparator);
	}
	
	public PriorityQueue<Integer, V> enqueue(Integer priority, V value){
		 myHeap.add(priority, value);
		 return this;
	}

	  /**
	   * Removes the value with the highest priority in this {@link PriorityQueue} and then returns it.
	   * This runs in O(log(size)) time.
	   *
	   * @return the value with the highest priority in this {@link PrioirtyQueue}
	   * @throws IllegalStateException if this {@link PriorityQueue} is empty.
	   */
	  public V dequeue() {
		 return myHeap.remove();
	  }

	  /**
	   * Returns the value with the highest priority in this {@link PriorityQueue}.
	   *
	   * @return the value with the highest priority in this {@link PriorityQueue}.
	   * @throws IllegalStateException if this {@link PriorityQueue} is empty.
	   */
	  public V peek() {
		  return myHeap.peek();
	  }

	  /**
	   * Returns an {@link Iterator} over all of the entries in this {@link PriorityQueue}. The order of
	   * these elements is unspecified and a call to {@link Iterator#remove()} results in an {@link
	   * UnsupportedOperationException}.
	   *
	   * @return an {@link Iterator} over all of the values in this {@link PriorityQueue}.
	   */
	  public Iterator<Entry<Integer, V>> iterator(){
		  return myHeap.asList().iterator();
	  }

	  /**
	   * Returns the {@link Comparator} that is used to determine the ordering of {@link Entry}s in this
	   * {@link PriorityQueue}.
	   *
	   * @return the {@link Comparator} that is used to determine the ordering of {@link Entry}s in this
	   *     {@link PriorityQueue}.
	   */
	  public Comparator<Integer> getComparator(){
		  return myHeap.getComparator();
	  }

	  /**
	   * Returns the total number of elements in this {@link PriorityQueue}
	   *
	   * @return the total number of elements in this {@link PriorityQueue}
	   */
	  public int size() {
		  return myHeap.size();
	  }

	  /**
	   * Returns {@code true} if this {@link PrioirtyQueue} has no elements and {@code false} otherwise.
	   *
	   * @return {@code true} if this {@link PrioirtyQueue} has no elements and {@code false} otherwise.
	   */
	  public boolean isEmpty() {
		  return myHeap.isEmpty();
	  }
	}

