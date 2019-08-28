package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

/**
 * This operator evaluate the multiplication expression
 * 
 * @author Francis
 *
 */
public class MultiplyOperator extends Operator {

	@Override
	public int priority() {
		return 2;
	}

	@Override
	public Operand execute(Operand op1, Operand op2) {
		if (null == op1 || null == op2)
			return null;
		Operand ans = new Operand(op1.getValue() * op2.getValue());
		return ans;
	}

}
