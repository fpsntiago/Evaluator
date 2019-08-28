package edu.csc413.calculator.evaluator;

import java.util.HashSet;
import java.util.Stack;

/**
 * Validate the expression
 * 
 * @author Francis
 *
 */
public class ExpressionValidator {
	private static final HashSet<Character> validCharactersSet = new HashSet<>();
	static {
		validCharactersSet.add('*');
		validCharactersSet.add('+');
		validCharactersSet.add('-');
		validCharactersSet.add('/');
		validCharactersSet.add('%');
		validCharactersSet.add('^');
		validCharactersSet.add('(');
		validCharactersSet.add(')');
	}

	/**
	 * Check the expression is valid or not
	 * 
	 * @param expression
	 * @return True/false
	 */
	public static boolean isValid(String expression) {
		Stack<Character> validParenthesisCheck = new Stack<>();

		for (int i = 0; i < expression.length(); i++) {
			if (!Character.isWhitespace(expression.charAt(i))) {
				if (!Character.isDigit(expression.charAt(i)) && !validCharactersSet.contains(expression.charAt(i))) {
					return false;
				}

				if (expression.charAt(i) == '(') {
					validParenthesisCheck.push(expression.charAt(i));
				}

				if (expression.charAt(i) == ')') {

					if (validParenthesisCheck.isEmpty()) {
						return false;
					}
					validParenthesisCheck.pop();
				}
			}
		}
		if (validParenthesisCheck.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}
