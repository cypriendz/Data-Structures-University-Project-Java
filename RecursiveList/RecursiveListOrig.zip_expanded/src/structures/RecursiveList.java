package structures;
import java.util.Iterator;
import structures.myIterator;

public class RecursiveList<T> implements ListInterface<T> {
	int numElements;
	Node<T> myList;
	
	public Iterator<T> iterator(){
		myIterator<T> iterList = new myIterator<T>(myList);
		return iterList;
		
	}
	
	
  /**
   * Returns the number of elements in this {@link ListInterface}. This method runs in O(1) time.
   *
   * @return the number of elements in this {@link ListInterface}
   */
  public int size() {
	  return numElements;
  }

  /**
   * Adds an element to the front of this {@link ListInterface}. This method runs in O(1) time. For
   * convenience, this method returns the {@link ListInterface} that was modified.
   *
   * @param elem the element to add
   * @throws NullPointerException if {@code elem} is {@code null}
   * @return The modified {@link ListInterface}
   */
  public ListInterface<T> insertFirst(T elem){
	  return insertAt(0,elem);
	  
  }

  /**
   * Adds an element to the end of this {@link ListInterface}. This method runs in O(size) time. For
   * convenience, this method returns the {@link ListInterface} that was modified.
   *
   * @param elem the element to add
   * @throws NullPointerException if {@code elem} is {@code null}
   * @return the modified {@link ListInterface}
   */
  public ListInterface<T> insertLast(T elem){
	  return insertAt(numElements,elem);
  }

  /**
   * Adds an element at the specified index such that a subsequent call to {@link
   * ListInterface#get(int)} at {@code index} will return the inserted value. This method runs in
   * O(index) time. For convenience, this method returns the {@link ListInterface} that was
   * modified.
   *
   * @param index the index to add the element at
   * @param elem the element to add
   * @throws NullPointerException if {@code elem} is {@code null}
   * @throws IndexOutOfBoundsException if {@code index} is less than 0 or greater than {@link
   *     ListInterface#size()}
   * @return The modified {@link ListInterface}
   */
  public ListInterface<T> insertAt(int index, T elem){
	  Node<T> newNode = new Node<T>(elem);
	  
	  if(elem == null) {
		  throw new NullPointerException("Elem does not exist.");
	  }
	  if(index < 0 || index > size()) {
		  throw new IndexOutOfBoundsException("Index is out of bounds.");
	  }
	   
	  if (index == 0) {
		  newNode.setNext(myList);
		  myList = newNode;
		  numElements++;
	  }
	  else {
		   insertAtHelper(myList,index,newNode);
		   numElements++;
	  }
	  return this;
  }
  
  private void insertAtHelper(Node<T> current, int index, Node<T> newNode){
	  
	  
	  if (index == 1) {
		  newNode.setNext(current.getNext());
		  current.setNext(newNode);
		  
	  }
	  else {
		  
		  insertAtHelper(current.getNext(),index-1,newNode);
		  
	  }
  }
  
  /**
   * Removes the first element from this {@link ListInterface} and returns it. This method runs in
   * O(1) time.
   *
   * @throws IllegalStateException if the {@link ListInterface} is empty.
   * @return the removed element
   */
  public T removeFirst() {
	 if(isEmpty()) {
		 throw new IllegalStateException("List is empty.");
	 }
	 else {
		 return removeAt(0);
	 }
  }

  /**
   * Removes the last element from this {@link ListInterface} and returns it. This method runs in
   * O(size) time.
   *
   * @throws IllegalStateException if the {@link ListInterface} is empty.
   * @return the removed element
   */
  public T removeLast() {
	  if(myList == null) {
			 throw new IllegalStateException("List is empty.");
		 }
	  else {
		  return removeAt(numElements-1);
	  }
	 
  }

  /**
   * Removes the ith element in this {@link ListInterface} and returns it. This method runs in O(i)
   * time.
   *
   * @param i the index of the element to remove
   * @throws IndexOutOfBoundsException if {@code i} is less than 0 or {@code i} is greater than or
   *     equal to {@link ListInterface#size()}
   * @return The removed element
   */
  public T removeAt(int i) {
	  T retElement;
	  if(i < 0 || i >= numElements) {
		  throw new IndexOutOfBoundsException("Index is out of bounds.");
	  }
	  else if (i == 0) {
		   retElement = myList.getData();
		   myList = myList.getNext();
		   numElements--;
	  }
	  else{
		  retElement = removeAtHelper(myList,i);
		  numElements--; 
	  }
	  return retElement;
  }
  
  private T removeAtHelper(Node<T> current, int i) {
	 T retElement;
	  
	   if (i == 1) {
		 Node<T> afterCurrent = current.getNext();
		  retElement = afterCurrent.getData();
		 current.setNext(afterCurrent.getNext());
			return retElement;
	  }
	  	return removeAtHelper(current.getNext(),i-1);
  }

  /**
   * Returns the first element in this {@link ListInterface}. This method runs in O(1) time.
   *
   * @throws IllegalStateException if the {@link ListInterface} is empty.
   * @return the first element in this {@link ListInterface}.
   */
  public T getFirst() {
	 if (isEmpty()) {
		 throw new IllegalStateException("List is empty.");
	 }
	 else {
		 return get(0);
	 }
  }

  /**
   * Returns the last element in this {@link ListInterface}. This method runs in O(size) time.
   *
   * @throws IllegalStateException if the {@link ListInterface} is empty.
   * @return the last element in this {@link ListInterface}.
   */
  public T getLast() {
	  if (myList == null) {
			 throw new IllegalStateException("List is empty.");
		 }
		 else {
			 return get(numElements-1);
		 }
	  }
  

  /**
   * Returns the ith element in this {@link ListInterface}. This method runs in O(i) time.
   *
   * @param i the index to lookup
   * @throws IndexOutOfBoundsException if {@code i} is less than 0 or {@code i} is greater than or
   *     equal to {@link ListInterface#size()}
   * @return the ith element in this {@link ListInterface}.
   */
  public T get(int i) {
	  if(i < 0 || i >= numElements) {
		  throw new IndexOutOfBoundsException("Index is out of bounds.");
	  }
	  else {
		 return getHelper(i, myList);
	  }
  }

  private T getHelper(int i, Node<T> current) {
	  if(i == 0) {
		  return current.getData();
	  }
	  else {
		  return getHelper(i-1,current.getNext());
	  }
  }
  /**
   * Removes {@code elem} from this {@link ListInterface} if it exists. If multiple instances of
   * {@code elem} exist in this {@link ListInterface} the one associated with the smallest index is
   * removed. This method runs in O(size) time.
   *
   * @param elem the element to remove
   * @throws NullPointerException if {@code elem} is {@code null}
   * @return {@code true} if this {@link ListInterface} was altered and {@code false} otherwise.
   */
  public boolean remove(T elem) {
	  if(elem == null) {
		  throw new NullPointerException("Elem does not exist.");
	  }
	  else {
		 int indexHolder;
		 indexHolder = indexOf(elem);
		  	if(indexHolder == -1) {
		  		return false;
		  	}
		  	else {
		  		removeAt(indexHolder);
		  		return true;
		  	}
	  }
  }

  /**
   * Returns the smallest index which contains {@code elem}. If there is no instance of {@code elem}
   * in this {@link ListInterface} then -1 is returned. This method runs in O(size) time.
   *
   * @param elem the element to search for
   * @throws NullPointerException if {@code elem} is {@code null}
   * @return the smallest index which contains {@code elem} or -1 if {@code elem} is not in this
   *     {@link ListInterface}
   */
  public int indexOf(T elem) {
	  if(elem == null) {
		  throw new NullPointerException("Elem does not exist.");
	  }
	  else return indexOfHelper(elem,myList,0);
  }

  private int indexOfHelper(T toFind, Node<T> toCheck, int currentIndex) {
	  if (toCheck == null) {
		  return -1;
	  }
	  else {
		  if (toCheck.getData().equals(toFind)){
			  return currentIndex;
		  }
		  else {
			 return indexOfHelper(toFind, toCheck.getNext(), currentIndex+1);
		  }
	  }
	  
  }
  /**
   * Returns {@code true} if this {@link ListInterface} contains no elements and {@code false}
   * otherwise. This method runs in O(1) time.
   *
   * @return {@code true} if this {@link ListInterface} contains no elements and {@code false}
   *     otherwise.
   */
  public boolean isEmpty() {
	  if (myList == null) {
		  return true;
	  }
	  else {
		  return false;
	  }
  }
}