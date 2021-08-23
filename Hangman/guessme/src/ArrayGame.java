

public class ArrayGame {

  // stores the next number to guess
  public int currGuess;
  public int nmatch;
  public int numGuesses;
  public boolean ansFound;
  public boolean[] isElm = new boolean [9000];
  public int[] prior = new int[18];
  public int guess = 1000; 
  // TODO: declare additional data members, such as arrays that store
  // prior guesses, eliminated candidates etc.

  // NOTE: only primitive type arrays are allowed, such as int[], boolean[] etc.
  // You MAY NOT use any Collection type (such as ArrayList) provided by Java.

  /********************************************************
   * NOTE: you are allowed to add new methods if necessary,
   * but DO NOT remove any provided method, otherwise your
   * code will fail the JUnit tests.
   * Also DO NOT create any new Java files, as they will
   * be ignored by the autograder!
   *******************************************************/

  // ArrayGame constructor method
  public ArrayGame() {
    // TODO
   
    nmatch = 0;
    //priorGuess = 0;
    //large = 0;
   // known = 0;
    ansFound = false;
    numGuesses = 0;
    currGuess = 0 ;
   // numPossible = 9000;
   guess = 1000;
    
   int j = 0;
   while (j < isElm.length){
     isElm[j] = false;
     j++;
   }
    //isPrior = new boolean[numPossible];
    int i = 0;
    while (i < prior.length) {
      prior[i] = 0;
      i++;
    }
  }
  /**
   *  Resets data members and game state so we can play again.
   */
  public void reset() {
    // TODO
   
    
    nmatch = 0;
   // priorGuess = 0;
    //large = 0;
   // known = 0;
    ansFound = false;
    numGuesses = 0;
    currGuess = 0 ;
   // numPossible = 9000;
   guess = 1000;
  
   int j = 0;
   while (j < isElm.length){
     isElm[j] = true;
     j++;
   }
    //isPrior = new boolean[numPossible];
    int i = 0;
    while (i < prior.length) {
      prior[i] = 0;
      i++;
    }
  }

  /**
   *  Returns true if n is a prior guess; false otherwise.
   */
  public boolean isPriorGuess(int n) {
    // TODO
    
    int i =0;
    while (i < prior.length){
      
      if (n == prior[i]) 
      return true;  
      i++;
    }
   
    return false;
  }

  /**
   *  Returns the number of guesses so far.
   */
  public int numGuesses() {
    // TODO
    
    return numGuesses;
  }

  /**
   * Returns the number of matches between integers a and b.
   * You can assume that both are 4-digits long (i.e. between 1000 and 9999).
   * The return value must be between 0 and 4.
   * 
   * <p>A match is the same digit at the same location. For example:
   *   1234 and 4321 have 0 match;
   *   1234 and 1114 have 2 matches (1 and 4);
   *   1000 and 9000 have 3 matches (three 0's).
   */
  
  public static int numMatches(int a, int b) { // DO NOT remove the static qualifier
    // TODO
   
    int nmatches = 0;
  
      if ((a) % 10 == (b) % 10) {
        nmatches = nmatches + 1;
      }
     
      if ((a/10) % 10 == (b/10) % 10 ) {
        nmatches = nmatches + 1;
      }
      
      if ((a/100) % 10 == (b/100) % 10 ) {
        nmatches = nmatches + 1;
      }
      if ((a/1000) % 10 == (b/1000) % 10 ) {
        nmatches = nmatches + 1;
      }


    return nmatches;
   
    
  }


 
  /**
   * Returns true if the game is over; false otherwise.
   * The game is over if the number has been correctly guessed
   * or if all candidates have been eliminated.
   */
  public boolean isOver() {
    // TODO
    return ansFound;
  }

  
  public int getGuess() {
    // TODO: add guess to the list of prior guesses.
        currGuess = numGuesses;
        prior[currGuess] = guess;
        numGuesses++;
        
      return guess;
    }
      
  

  /**
   * Updates guess based on the number of matches of the previous guess.
   * If nmatches is 4, the previous guess is correct and the game is over.
   * Check project description for implementation details.
   * 
   * <p>Returns true if the update has no error; false if all candidates
   * have been eliminated (indicating a state of error);
   */
  public boolean updateGuess(int nmatches) {
    // TODO 
  
    if (guess < 1000 || guess > 9999) {
      return false;
    }
    
    if (nmatches == 4) {
      ansFound= true;
      return true;
    }
  
    for (int i = 1000; i < 10000; i++) {
      if (numMatches(guess, i) != nmatches) {
        isElm[i-1000] = false;
      }
    }
    
    for (int i = 1000; i < 10001; i++) {
      if (i == 10000){
        return false;
      }
     
      if (isElm[i-1000] == false) {
        continue;
      }
      else {
        guess = i;
        break; 
    }
  }
  return true;
}



  public int[] priorGuesses() {
    
    // TODO
    int [] guesses = new int [numGuesses];
   
    int k = 0;
      while (k < numGuesses){
        guesses[k] = prior[k];
      k++;
      }
        if (numGuesses == 0) 
      return null;
      
      else
      {
        if (nmatch ==4)
        reset();
      }
    
      return guesses;
    }
    
  }

