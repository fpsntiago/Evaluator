package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

/**
 * This is the opening braces which has highest priority
 * 
 * @author Francis
 *
 */
public class OpenBraceOperator extends Operator {
	@Override
	public int priority() {
		return -1;
	}

	@Override
	public Operand execute(Operand op1, Operand op2) {
		return null;
	}
}
