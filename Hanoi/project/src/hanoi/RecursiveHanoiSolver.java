package hanoi;

import structures.ListImplementation;
import structures.ListInterface;
import hanoi.ArrayBasedHanoiBoard;
import hanoi.StackBasedHanoiPeg;
/**
 * A {@link RecursiveHanoiSolver} implements the {@link HanoiSolver} taking advantage of recursion.
 */
public class RecursiveHanoiSolver {
	ArrayBasedHanoiBoard board;
  /**
   * Moves {@code n} rings from {@code fromPeg} to the {@code toPeg}.
   *
   * @param n the number of rings to move
   * @param fromPeg the peg to move them from
   * @param auxPeg a peg that we may utilize when needed to accomplish the task
   * @param toPeg the peg to move them to
   */
  private void solverHelper( int n, int fromPeg, int auxPeg, int toPeg, ListInterface<HanoiMove> moves)
      throws IllegalHanoiMoveException {
	  	board = new ArrayBasedHanoiBoard(n);
		int A = fromPeg;
		int B = auxPeg;
		int C = toPeg;
		int rings = n;		
	  	if (n > 0) {
			
	  		
	  		
	  		solverHelper(rings-1, A, B, C,  moves);
	  		//solverHelper(rings-1, A, B, C, moves.append(new HanoiMove(A,B)));
	  		
			 moves.append(new HanoiMove(A,C));
			 
			 solverHelper(rings-1, B, A, C,  moves);
			 //solverHelper(rings-1, A, B, C,  moves.append(new HanoiMove(B,C)));
			 //solverHelper(rings-1, B, A, C,  moves.append(new HanoiMove(B,C)));
	  	}
	  	
}
		
			  
		 
		
    
  

  public HanoiSolution solve(int n) throws IllegalHanoiMoveException {
    if (n < 0) {
      throw new IllegalArgumentException();
    }
    // Initialize moves to a new list to get rid of any previous
    // solution stored there
    ListInterface<HanoiMove> moves = new ListImplementation<HanoiMove>();
    // We want to move n rings from peg 0 to peg 2.
    solverHelper(n, 0, 1, 2, moves);
    // Return a new solution with n as the number of rings
    // and moves as the solution
    return new HanoiSolution(n, moves);
  }
}
