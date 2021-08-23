package stack;

/**
 * A {@link LinkedStack} is a stack that is implemented using a Linked List
 * structure to allow for unbounded size.
 *
 * @param <T> the elements stored in the stack
 */
public class LinkedStack<T> implements StackInterface<T> {
	protected LLNode<T> top;
	
	public LinkedStack() {
		top = null;
	}
	
	@Override
	public T pop() throws StackUnderflowException {
		// TODO Implement the stack operation for `pop`!
		LLNode<T> curr;
		
		
		if (!isEmpty()){	
			curr = top;
			top = top.getNext();	
			return curr.getData();
		}
		
		
		
		else {
			 throw new StackUnderflowException("Pop attempted on an empty stack.");
		 }
}

	@Override
	public T top() throws StackUnderflowException {
		// TODO Implement the stack operation for `top`!
		if (!isEmpty()) {
			return top.getData();	
		}
		
		else {
			throw new StackUnderflowException("Pop attempted on an empty stack.");
		}
	}

	@Override
	public boolean isEmpty() {
		return(top == null);
	}

	@Override
	public int size() {
		int count = 0;
		LLNode<T> curr;
	
		for (curr = top; curr != null; curr = curr.getNext()) {
			count++;
		}
			return count;
		/*while (top != null){
			count++;
			top = top.getNext();
			return count;
		}*/
			
		// TODO Implement the stack operation for `size`!
		
	}

	@Override
	public void push(T elem) {
		// TODO Implement the stack operation for `push`!
		LLNode<T> newNode = new LLNode<T>(elem);
		newNode.setNext(top);
		newNode.setData(elem);
		top = newNode;
		
	
	}
}
