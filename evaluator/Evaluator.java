package edu.csc413.calculator.evaluator;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;

import edu.csc413.calculator.operators.Constants;
import edu.csc413.calculator.operators.Operator;

public class Evaluator {

	private Stack<Operand> operandStack;
	private Stack<Operator> operatorStack;
	private StringTokenizer tokenizer;

	public Evaluator() {
		operandStack = new Stack<>();
		operatorStack = new Stack<>();
	}

	public int eval(String expression) {
		if (!ExpressionValidator.isValid(expression)) {
			throw new ArithmeticException("Expression is invalid ");
		}
		String token;

		// The 3rd argument is true to indicate that the delimiters should be used
		// as tokens, too. But, we'll need to remember to filter out spaces.
		this.tokenizer = new StringTokenizer(expression, Constants.DELIMITERS, true);

		// initialize operator stack - necessary with operator priority schema
		// the priority of any operator in the operator stack other than
		// the usual mathematical operators - "+-*/" - should be less than the priority
		// of the usual operators

		while (this.tokenizer.hasMoreTokens()) {
			// filter out spaces
			if (!(token = this.tokenizer.nextToken()).equals(Constants.EMPTY_STRING_SPACE)) {
				token = token.trim();
				// check if token is an operand
				if (Operand.check(token)) {
					operandStack.push(new Operand(token));
				} else {
					if (!Operator.check(token)) {
						throw new ArithmeticException("Invalid expression");
					}

					// TODO Operator is abstract - these two lines will need to be fixed:
					// The Operator class should contain an instance of a HashMap,
					// and values will be instances of the Operators. See Operator class
					// skeleton for an example.
					switch (token) {
					case Constants.CLOSE_BARCE:
						Operator peek = operatorStack.peek();
						while (peek.priority() >= 1) {
							evaluate();
							if (!operatorStack.isEmpty())
								peek = operatorStack.peek();
							else
								peek = null;
						}
						operatorStack.pop();
						continue;
					case Constants.OPEN_BARCE:
						operatorStack.push(Operator.getOperator(token));
						continue;
					default:
						break;
					}
					Operator operator = Operator.getOperator(token);
					if (operatorStack.isEmpty()) {
						operatorStack.add(operator);
						continue;
					}
					Operator peek = operatorStack.peek();
					while (null != peek && peek.priority() >= operator.priority()) {
						// note that when we eval the expression 1 - 2 we will
						// push the 1 then the 2 and then do the subtraction operation
						// This means that the first number to be popped is the
						// second operand, not the first operand - see the following code
						evaluate();
						if (!operatorStack.isEmpty())
							peek = operatorStack.peek();
						else
							peek = null;
					}

					operatorStack.push(operator);
				}
			}
		}

		// Control gets here when we've picked up all of the tokens; you must add
		// code to complete the evaluation - consider how the code given here
		// will evaluate the expression 1+2*3
		// When we have no more tokens to scan, the operand stack will contain 1 2
		// and the operator stack will have + * with 2 and * on the top;
		// In order to complete the evaluation we must empty the stacks (except
		// the init operator on the operator stack); that is, we should keep
		// evaluating the operator stack until it only contains the init operator;
		// Suggestion: create a method that takes an operator as argument and
		// then executes the while loop.
		if (!operatorStack.isEmpty()) {
			Operator peek = operatorStack.peek();
			while (null != peek && peek.priority() >= 0) {
				evaluate();
				if (!operatorStack.isEmpty())
					peek = operatorStack.peek();
				else
					peek = null;
			}
		}
		Operand pop = operandStack.pop();
		if (null == pop)
			return 0;
		return pop.getValue();
	}

	/**
	 * Evaluate the operator
	 */
	private void evaluate() {
		try {
			if (!operatorStack.isEmpty()) {
				Operator opr = operatorStack.pop();
				Operand op2 = operandStack.pop();
				Operand op1 = operandStack.pop();
				operandStack.push(opr.execute(op1, op2));
			}
		} catch (EmptyStackException e) {
			throw new ArithmeticException("Invalid expression");
		}
	}
}
