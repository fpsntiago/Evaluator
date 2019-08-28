package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

/**
 * This operator evaluate the addtion expression
 * 
 * @author Francis
 *
 */
public class AddOperator extends Operator {
	@Override
	public int priority() {
		return 1;
	}

	@Override
	public Operand execute(Operand op1, Operand op2) {
		if (null == op1 || null == op2)
			return null;
		Operand ans = new Operand(op1.getValue() + op2.getValue());
		return ans;
	}

}
