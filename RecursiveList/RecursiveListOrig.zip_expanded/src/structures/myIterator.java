package structures;
import java.util.Iterator;
import structures.Node;
import java.util.NoSuchElementException;


public class myIterator<T> implements Iterator<T>
{
	 public Node<T> current;

     public myIterator (Node<T> start) {
         current = start;
     }

    
     public boolean hasNext() {
         return current != null;
     }

    
     public T next() {
    	 if (hasNext() == true) {
    		 T result = current.getData();
    		 current = current.getNext();
    		 return result;
    	 }
    	 else {
    		 throw new NoSuchElementException("This is the Last Element.");
    	 }
     }
     
     @Override
 	public void remove() {
 		throw new UnsupportedOperationException();
 	}
}