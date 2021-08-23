package hanoi;
import hanoi.HanoiRing;
import hanoi.StackBasedHanoiPeg;
import structures.Node;
/** A {@link ArrayBasedHanoiBoard} is a simple implementation of {@link HanoiBoard} */
public class ArrayBasedHanoiBoard implements HanoiBoard {
  // TODO: Add useful instance variables to the ArrayBasedHanoiBoard class

  /**
   * Creates a {@link ArrayBasedHanoiBoard} with three empty {@link HanoiPeg}s and {@code n} rings
   * on peg 0.
   */
	
	
	StackBasedHanoiPeg[] board = new StackBasedHanoiPeg[3];

  public ArrayBasedHanoiBoard(int n) {
	 // top = null;
	  if (n < 0) {
	  throw new IllegalArgumentException("Number of rings entered is negative.");
	  }
	 
	  int i = 0;
	  while(i<3) { 
	  board[i] = new StackBasedHanoiPeg();
	  i++;
	  }
	  
	  int j = n;
	  while(j >= 1) {
		 HanoiRing newRing = new HanoiRing(j);
		  board[0].addRing(newRing);
		  j--;
	  }
	  
	  
	
    // TODO: Implement the ArrayBasedHanoiBoard constructor
  }

  @Override
  public void doMove(HanoiMove move) throws IllegalHanoiMoveException {
    // TODO: Implement the doMove method
	 
	  if(!isLegalMove(move)) {
		  throw new IllegalHanoiMoveException("This move is illegal.");
	  }
	  
	  if (move.getFromPeg() == 0 && move.getToPeg() == 2) {
		 board[2].addRing(board[0].getTopRing());
		 board[0].remove(); 
	 }
	 else if (move.getFromPeg() == 0 && move.getToPeg() == 1) {
		 board[1].addRing(board[0].getTopRing());
		 board[0].remove(); 
	 }
	 
	 else if (move.getFromPeg() == 1 && move.getToPeg() == 2) {
		 board[2].addRing(board[1].getTopRing());
		 board[1].remove(); 
	 }
	 
	 else if (move.getFromPeg() == 1 && move.getToPeg() == 0) {
		 board[0].addRing(board[1].getTopRing());
		 board[1].remove(); 
	 }
  
	 else if (move.getFromPeg() == 2 && move.getToPeg() ==1) {
		 board[1].addRing(board[2].getTopRing());
		 board[2].remove(); 
	 }
	 
	 else if (move.getFromPeg() == 2 && move.getToPeg() == 0) {
		 board[0].addRing(board[2].getTopRing());
		 board[2].remove(); 
	 }
  
  }
  

  @Override
  public boolean isSolved() {
    // TODO: Implement the isSolved method.
	 
	  if (board[0].hasRings() == false && board[1].hasRings()== false && board[2].hasRings() == false) {
		  return true;
	   }
	  
	  else if (board[0].hasRings() == false && board[1].hasRings()== false && board[2].hasRings() == true) {
		  return true;
	   }
	
	  else {
	  return false;
	  }
  }

  /**
   * A {@link HanoiMove} is not legal if either is true:
   *
   * <ul>
   *   <li>The from peg has no rings
   *   <li>The to peg has rings AND the ring to be moved has a size larger than the topmost ring on
   *       the to peg.
   * </ul>
   *
   * Otherwise, the move is legal.
   */
  @Override
  public boolean isLegalMove(HanoiMove move) {
    // TODO: Implement the isLegalMove method
	 if (move == null) {
		  throw new  NullPointerException("move was null.");
	  }
	
	 else if( move.getFromPeg() == move.getToPeg()) {
		 return false;
	 }
	 else if (move.getFromPeg() == 0 && board[0].hasRings() == false || move.getFromPeg() == 1 && board[1].hasRings() == false || move.getFromPeg() == 2 && board[2].hasRings() == false) {
		 return false;
	 }
	
	 if (move.getFromPeg() == 0 && move.getToPeg() == 1 && board[1].hasRings() == true && board[1].getTopRing().getSize() < board[0].getTopRing().getSize()){
		 return false;
	 }
	 
	 else if (move.getFromPeg() == 0 && move.getToPeg() == 2 && board[2].hasRings() == true && board[2].getTopRing().getSize() < board[0].getTopRing().getSize()){
		 return false;
	 }
	 
	 else if (move.getFromPeg() == 1 && move.getToPeg() == 2 && board[2].hasRings() == true && board[2].getTopRing().getSize() < board[1].getTopRing().getSize()){
		 return false;
	 }
	 
	 else if (move.getFromPeg() == 1 && move.getToPeg() == 0 && board[0].hasRings() == true && board[0].getTopRing().getSize() < board[1].getTopRing().getSize()){
		 return false;
	 }
	 
	 else if (move.getFromPeg() == 2 && move.getToPeg() == 1 && board[1].hasRings() == true && board[1].getTopRing().getSize() < board[2].getTopRing().getSize()){
		 return false;
	 }
	 
	 else if (move.getFromPeg() == 2 && move.getToPeg() == 0 && board[0].hasRings() == true && board[0].getTopRing().getSize() < board[2].getTopRing().getSize()){
		 return false;
	 }
	 
	 else {
		 return true;
	 }
	 /*  if (from.hasRings())*/
   
  }
}
