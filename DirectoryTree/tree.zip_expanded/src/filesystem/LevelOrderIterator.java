package filesystem;
import structures.Node;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import structures.Queue;
import java.util.Arrays;
/**
 * An iterator to perform a level order traversal of part of a filesystem. A level-order traversal
 * is equivalent to a breadth- first search.
 */
public class LevelOrderIterator extends FileIterator<File> {
	
	/**
   * Instantiate a new LevelOrderIterator, rooted at the rootNode.
   *
   * @param rootNode
   * @throws FileNotFoundException if the rootNode does not exist
   */
	Queue<File> tree;
	File[] arr;
  public LevelOrderIterator(File rootNode) throws FileNotFoundException {
    // TODO implement the constructor for LevelOrderIterator
	  if(!rootNode.exists()) {
		  throw new FileNotFoundException("No file exists.");
	  }
	
	  tree = new Queue<File>();
	  tree.enqueue(rootNode);
  
  }
  
  @Override
  public boolean hasNext() {
	 if(tree.isEmpty()) {
    return false;
	 }
	 else {
		 return true;
	 }
  }
  @Override
  public File next() throws NoSuchElementException {
	 // currFileNode = currFileNode.getNext();
	  if (hasNext() == false) {
		  throw new NoSuchElementException("No such file found.");	
	  		}
	  	File newFile = tree.dequeue();	
	  		if (newFile.isDirectory()) {
	  			arr = newFile.listFiles();
	  			Arrays.sort(arr);
	  			
	  			int i = 0;
	  			while(i < arr.length) {
	  				tree.enqueue(arr[i]);
	  				i++;
	  			}
		
	  		}
	  		return newFile;
  	}

  @Override
  public void remove() {
    // Leave this one alone.
    throw new UnsupportedOperationException();
  }
}
