package language.arith;

import language.Operand;
import language.UnaryOperator;

// TODO Implement the NegateOperator class
//
// You will notice that this class extends BinaryOperator<T>.
// That is not correct as negate is a unary operator. To fix this
// you must do the following:
//
// (1) Write an abstract class called language.UnaryOperator<T>. This class
// will be similar to language.BinaryOperator<T>, however, it will
// provide an abstraction for unary operators (e.g., negation).
//
// (2) Change this class to extend your new UnaryOperator<T> class. Do not
// forget to import language.UnaryOperator<T>!
public class NegateOperator extends UnaryOperator<Integer>{

	@Override
	public Operand<Integer> performOperation() {
		if(op0 == null)
			throw new IllegalStateException("Could not perform operation prior to operands being set.");
		Integer result = -op0.getValue();
		return new Operand<Integer>(result);
	}

	public String toString() {
		return "!";
    }
}
