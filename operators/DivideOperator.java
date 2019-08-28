package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

/**
 * This operator evaluate the devision expression. It returns null if one of the
 * operand is null or value of second operand is zero
 * 
 * @author Francis
 *
 */
public class DivideOperator extends Operator {

	@Override
	public int priority() {
		return 2;
	}

	@Override
	public Operand execute(Operand op1, Operand op2) {
		if (null == op1 || null == op2)
			return null;
		if (op2.getValue() == 0) {
			throw new ArithmeticException("Cannot divid by 0");
		}
		Operand ans = new Operand(op1.getValue() / op2.getValue());
		return ans;
	}
}
