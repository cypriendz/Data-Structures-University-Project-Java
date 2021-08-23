package structures;

public class ScapegoatTree<T extends Comparable<T>> extends BinarySearchTree<T> {
  private int upperBound;


  @Override
	public void add(T element) {
	  if (element == null) {
		  throw new NullPointerException();
	  }
		upperBound++;
		BSTNode<T> newNode = new BSTNode<T>(element, null, null);
		
		root = addToSubtree(root, newNode);
		
			if (height() > Math.log(upperBound) / Math.log((double)3/2)) {
				BSTNode<T> kid = newNode;
				BSTNode<T> w = newNode.parent;
			
				while ((double)subtreeSize(kid)/ subtreeSize(w) <= 2.0/3.0) {
				w = w.parent;
				kid = kid.parent;
			}
			ScapegoatTree<T> tree = new ScapegoatTree<T>();
			tree.root = w;
			BSTNode<T> parent1st = w.parent;
			tree.balance();
			
			if (parent1st.getLeft() == w) {
				parent1st.setLeft(tree.root);
			}
			else {
				parent1st.setRight(tree.root);
			}
		}
	}
	
  @Override
  public boolean remove(T element) {
	 
	  if(element == null) {
		  throw new NullPointerException("Element given is empty.");
	  }
	  
	  boolean contains = contains(element);
	  
	  if(contains == true) {
	  root = removeFromSubtree(root,element);
	  }
	  
	  if(upperBound > 2 * size()) {
		  balance();
		  upperBound = size();
	  }
	  return contains;
  }
}
