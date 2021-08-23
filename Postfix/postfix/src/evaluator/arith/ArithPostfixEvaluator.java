package evaluator.arith;

import language.Operand;
import parser.IllegalPostfixExpressionException;
import parser.PostfixParser.Type;
import parser.Token;
import parser.arith.ArithPostfixParser;
import stack.LinkedStack;
import stack.StackInterface;
import evaluator.PostfixEvaluator;
import language.Operator;
import language.arith.PlusOperator;
import language.arith.SubOperator;


/**
 * An {@link ArithPostfixEvaluator} is a postfix evaluator over simple
 * arithmetic expressions.
 *
 */
public class ArithPostfixEvaluator implements PostfixEvaluator<Integer> {

	private final StackInterface<Operand<Integer>> stack;
	private Operand<Integer> result;
	
	/**
	 * Constructs an {@link ArithPostfixEvaluator}
	 */
	public ArithPostfixEvaluator() {
		stack = new LinkedStack<>();
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer evaluate(String expr) throws IllegalPostfixExpressionException {
		ArithPostfixParser parser = new ArithPostfixParser(expr);		
		for (Token<Integer> token : parser) {
			Type type = token.getType();
			switch (type) {
				case OPERAND:
					// if the size is equal to two and the expr to string matches the result to string + prior tsting 
					
						
					Operand<Integer> v = token.getOperand();
					stack.push(v);
					result = v;
					
					// TODO What do we do when we see an operand?
					break;
					
				case OPERATOR:
					Operator<Integer> o = token.getOperator();
					
					if(o.toString().equals("!")) {
						Operand<Integer> Operand0 = stack.pop();
						o.setOperand(0, Operand0);
						result = o.performOperation();
						stack.push(result);
						break;
					}
						Operand<Integer> Operand0 = stack.pop();
						Operand<Integer> Operand1 = stack.pop();
						o.setOperand(0, Operand1);
						o.setOperand(1, Operand0);
						result = o.performOperation();
						stack.push(result);	
						break;
					
					
				default:
					//throw new IllegalPostfixExpressionException(" Invalid Type" + type);
					throw new IllegalStateException("Parser returned an invalid Type: " + type);
			}
		}
		// TODO What do we return?
		// return stack with result
	if (stack.size() == 2 && expr.length() == 3 && !expr.contains("!")) {
		throw new IllegalPostfixExpressionException(" Invalid Type");
	}
		return result.getValue();
	}

}
