package structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> implements BSTInterface<T> {
  protected BSTNode<T> root;

  public boolean isEmpty() {
    return root == null;
  }

  public int size() {
    return subtreeSize(root);
  }

  protected int subtreeSize(BSTNode<T> node) {
    if (node == null) {
      return 0;
    } else {
      return 1 + subtreeSize(node.getLeft()) + subtreeSize(node.getRight());
    }
  }

  public boolean contains(T t) {
	if(t== null) {
		 throw new NullPointerException("Element given is null");
	}
return recContains(t,root);
  }
 
  public boolean recContains(T element,BSTNode<T> tree) {
	  if(tree == null) {return false;}
	  
	  else if(element.compareTo(tree.getData()) < 0) {
		  return recContains(element, tree.getLeft());
	  }
	  else if(element.compareTo(tree.getData()) > 0) {
		  return recContains(element, tree.getRight());
	  }
	  else {
		  return true;
	  }
	  
  }

  public boolean remove(T t) {
    if (t == null) {
      throw new NullPointerException();
    }
    boolean result = contains(t);
    if (result) {
      root = removeFromSubtree(root, t);
    }
    return result;
  }

  protected BSTNode<T> removeFromSubtree(BSTNode<T> node, T t) {
    // node must not be null
    int result = t.compareTo(node.getData());
    if (result < 0) {
      node.setLeft(removeFromSubtree(node.getLeft(), t));
      return node;
    } else if (result > 0) {
      node.setRight(removeFromSubtree(node.getRight(), t));
      return node;
    } else { // result == 0
      if (node.getLeft() == null) {
        return node.getRight();
      } else if (node.getRight() == null) {
        return node.getLeft();
      } else { // neither child is null
        T predecessorValue = getHighestValue(node.getLeft());
        node.setLeft(removeRightmost(node.getLeft()));
        node.setData(predecessorValue);
        return node;
      }
    }
  }

  private T getHighestValue(BSTNode<T> node) {
    // node must not be null
    if (node.getRight() == null) {
      return node.getData();
    } else {
      return getHighestValue(node.getRight());
    }
  }

  private BSTNode<T> removeRightmost(BSTNode<T> node) {
    // node must not be null
    if (node.getRight() == null) {
      return node.getLeft();
    } else {
      node.setRight(removeRightmost(node.getRight()));
      return node;
    }
  }

  public T get(T t) {
	  if(t == null) {
		  throw new NullPointerException("Element given is null");
	  }
    return recGet(t,root);
  }

  public T recGet(T element, BSTNode<T> tree) {
	  
	  if (tree == null) 
		  return null;
	  
	  else if(element.compareTo(tree.getData()) < 0) 
		  return recGet(element,tree.getLeft());
	  
	  
	  else 
		  if (element.compareTo(tree.getData()) > 0) 
			  return recGet(element,tree.getRight());
		  
		  else 
			  return tree.getData();
		  
	  
  }

  public void add(T t) {
    if (t == null) {
      throw new NullPointerException();
    }
    root = addToSubtree(root, new BSTNode<T>(t, null, null));
  }

  protected BSTNode<T> addToSubtree(BSTNode<T> node, BSTNode<T> toAdd) {
    if (node == null) {
      return toAdd;
    }
    int result = toAdd.getData().compareTo(node.getData());
    if (result <= 0) {
      node.setLeft(addToSubtree(node.getLeft(), toAdd));
    } else {
      node.setRight(addToSubtree(node.getRight(), toAdd));
    }
    return node;
  }

  @Override
  public T getMinimum() {
	  if(isEmpty()) {return null;}
    
	 BSTNode<T> currMin = root;
	  
	 while(currMin.getLeft() != null) {
		 currMin = currMin.getLeft();
		 }
	 return currMin.getData();
  }


  @Override
  public T getMaximum() {
	  if(isEmpty()) {return null;}
	    
		 BSTNode<T> currMin = root;
		  
		 while(currMin.getRight() != null) {
			 currMin = currMin.getRight();
			 }
		 return currMin.getData();
	  }
   

  @Override
  public int height() {
	  if(isEmpty()) {return -1;}
	  
	  	return recHeight(root);
	  
  }

  public int recHeight(BSTNode<T> tree) {
	  
	  int rightHolder = 0;
	  int leftHolder = 0;
  
	  if (tree.getRight() == null && tree.getLeft() == null) {return 0;}
	  
  	if (tree.getRight() != null) {
  		rightHolder = recHeight(tree.getRight());
  	}
  	
	if (tree.getLeft() != null) {
  		leftHolder = recHeight(tree.getLeft());
  	}
	
	if(leftHolder > rightHolder) {return leftHolder + 1;}
	
	else  {return rightHolder + 1;}
}
  
  public Iterator<T> preorderIterator() {
	  Queue<T> queue = new LinkedList<T>();
	    preorderTraverse(queue, root);
	    return queue.iterator();
  }

  public void preorderTraverse(Queue<T> queue, BSTNode<T> node) {
	
	  
	  if (node != null) {
		 queue.add(node.getData());
		  preorderTraverse(queue,node.getLeft());
		  preorderTraverse(queue,node.getRight());
	  }
  }

  public Iterator<T> inorderIterator() {
    Queue<T> queue = new LinkedList<T>();
    inorderTraverse(queue, root);
    return queue.iterator();
  }


 private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
    if (node != null) {
      inorderTraverse(queue, node.getLeft());
      queue.add(node.getData());
      inorderTraverse(queue, node.getRight());
    }
  }

  public Iterator<T> postorderIterator() {
	  Queue<T> queue = new LinkedList<T>();
	    postorderTraverse(queue, root);
	    return queue.iterator();
}

public void postorderTraverse(Queue<T> queue, BSTNode<T> node) {
	
	  
	  if (node != null) {
		  postorderTraverse(queue,node.getLeft());
		  postorderTraverse(queue,node.getRight());
		  queue.add(node.getData());
	  }
}


  @Override
  public boolean equals(BSTInterface<T> other) {
    if(other == null) {
    	throw new NullPointerException("Inserted value is empty.");
    }
 
   return recEquals(this.getRoot(),other.getRoot());
  }
  
  public boolean recEquals(BSTNode<T> node1,BSTNode<T> node2) {
	  if(node1 == null && node2 == null) {
		  return true;
	  }
	  if(node1 == null && node2 != null || node1 != null && node2 == null) {
		  return false;
	  }
	  if(!node1.getData().equals(node2.getData())) {
		  return false;
	  }
	  return recEquals(node1.getLeft(), node2.getLeft()) && recEquals(node1.getRight(),node2.getRight());
	  
  }


  @Override
  public boolean sameValues(BSTInterface<T> other) {
	  if (other == null) {
		  throw new NullPointerException("Input was empty");
	  }
	  
	  Iterator<T> tree1 = this.inorderIterator();
	  Iterator<T> tree2 = other.inorderIterator();
	  
	  while (tree1.hasNext() == true && tree2.hasNext() == true) 
	  
	  if(!tree1.next().equals(tree2.next()))
		  return false;
	  
	  return !tree1.hasNext() && !tree2.hasNext();
	  
  }

  @Override
  public boolean isBalanced() {
   if(isEmpty()) {return true;}
   
   if(Math.pow(2, height()) <= size() && Math.pow(2, height() + 1) > size()) {
	   return true;
   }
   
   else {
      return false; 
   }
  }
  @Override
  @SuppressWarnings("unchecked")
  public void balance() {
	
	  Iterator<T> newIt;
	  newIt = this.inorderIterator();
	  T[] array = (T[]) new Comparable[size()];
	
	  int j = 0;
	  while(newIt.hasNext()) {
		  array[j] = newIt.next();	 
		  j++;
	  }
	  root = insertTree(array,0,array.length-1) ;
  }
  	
  public BSTNode<T> insertTree(T[] array,int start,int end){
	  if(start > end) {return null;}
	  
	  int mid = (start + end)/2;
	  BSTNode<T> newNode = new BSTNode<T>(array[mid],insertTree(array,start,mid-1),insertTree(array,mid+1,end));
	  return newNode;
  }
  		
  @Override
  public BSTNode<T> getRoot() {
    // DO NOT MODIFY
    return root;
  }

  public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
    // header
    int count = 0;
    String dot = "digraph G { \n";
    dot += "graph [ordering=\"out\"]; \n";
    // iterative traversal
    Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
    queue.add(root);
    BSTNode<T> cursor;
    while (!queue.isEmpty()) {
      cursor = queue.remove();
      if (cursor.getLeft() != null) {
        // add edge from cursor to left child
        dot += cursor.getData().toString() + " -> " + cursor.getLeft().getData().toString() + ";\n";
        queue.add(cursor.getLeft());
      } else {
        // add dummy node
        dot += "node" + count + " [shape=point];\n";
        dot += cursor.getData().toString() + " -> " + "node" + count + ";\n";
        count++;
      }
      if (cursor.getRight() != null) {
        // add edge from cursor to right child
        dot +=
            cursor.getData().toString() + " -> " + cursor.getRight().getData().toString() + ";\n";
        queue.add(cursor.getRight());
      } else {
        // add dummy node
        dot += "node" + count + " [shape=point];\n";
        dot += cursor.getData().toString() + " -> " + "node" + count + ";\n";
        count++;
      }
    }
    dot += "};";
    return dot;
  }

  public static void main(String[] args) {
    for (String r : new String[] {"a", "b", "c", "d", "e", "f", "g"}) {
      BSTInterface<String> tree = new BinarySearchTree<String>();
      for (String s : new String[] {"d", "b", "a", "c", "f", "e", "g"}) {
        tree.add(s);
      }
      Iterator<String> iterator = tree.inorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
      iterator = tree.preorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
      iterator = tree.postorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();

      System.out.println(tree.remove(r));

      iterator = tree.inorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
    }

    BSTInterface<String> tree = new BinarySearchTree<String>();
    for (String r : new String[] {"a", "b", "c", "d", "e", "f", "g"}) {
      tree.add(r);
    }
    System.out.println(tree.size());
    System.out.println(tree.height());
    System.out.println(tree.isBalanced());
    tree.balance();
    System.out.println(tree.size());
    System.out.println(tree.height());
    System.out.println(tree.isBalanced());
  }
}
