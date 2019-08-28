package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

import java.util.HashMap;

public abstract class Operator {

	// The Operator class should contain an instance of a HashMap
	// This map will use keys as the tokens we're interested in,
	// and values will be instances of the Operators.
	// ALL subclasses of operator MUST be in their own file.
	// Example:
	// Where does this declaration go? What should its access level be?
	// Class or instance variable? Is this the right declaration?
	private static final HashMap<String, Operator> operators = new HashMap<>();
	static {
		operators.put(Constants.ADD, new AddOperator());
		operators.put(Constants.SUBTRACT, new SubtractOperator());
		operators.put(Constants.DIVIDE, new DivideOperator());
		operators.put(Constants.MULTIPLY, new MultiplyOperator());
		operators.put(Constants.POWER, new PowerOperator());
		operators.put(Constants.OPEN_BARCE, new OpenBraceOperator());
	}

	/**
	 * Get the priority of the operator
	 * 
	 * @return Operator priority
	 */
	public abstract int priority();

	/**
	 * Execute the operator
	 * 
	 * @param op1 - Operand 1
	 * @param op2 - Operand 2
	 * @return Result Operand of operator evaluation
	 */
	public abstract Operand execute(Operand op1, Operand op2);

	/**
	 * determines if a given token is a valid operator. please do your best to avoid
	 * static checks for example token.equals("+") and so on. Think about what
	 * happens if we add more operators.
	 */
	public static boolean check(String token) {
		boolean isVaid = false;
		switch (token.trim()) {
		case Constants.ADD:
		case Constants.SUBTRACT:
		case Constants.DIVIDE:
		case Constants.MULTIPLY:
		case Constants.POWER:
		case Constants.OPEN_BARCE:
		case Constants.CLOSE_BARCE:
			isVaid = true;
			break;
		default:
			break;
		}
		return isVaid;
	}

	/**
	 * Get the operator for given token
	 * 
	 * @param token - Operator token
	 * @return Operator for given token
	 */
	public static Operator getOperator(String token) {
		return operators.getOrDefault(token, null);
	}
}
