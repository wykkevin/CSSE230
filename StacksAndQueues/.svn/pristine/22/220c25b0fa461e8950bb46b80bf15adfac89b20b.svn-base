package evaluator;

import java.util.Stack;

/**
 * 
 * Evaluates Postfix
 *
 * @author kongh and wangy16. Created Mar 19, 2017.
 */
public class PostfixEvaluator extends Evaluator {

	private Stack<String> stack;

	public PostfixEvaluator() {
		this.stack = new Stack<String>();
	}

	/**
	 * Evaluates postfix when string is passed in
	 */
	@Override
	public int evaluate(String expression) throws ArithmeticException {
		int check = operandAndOperator(expression);

		// if the different between number of the operand and operator is less
		// than 0 or if there is parenthesis in postfix, throws exception
		if (check <= 0) {
			throw new ArithmeticException();
		}

		// if the difference between the number of operand and operator is
		// greater than 1, it means there are too many operators
		else if (check > 1) {
			throw new ArithmeticException();
		}

		// returns the given number as there is nothing to evaluate
		if (expression.length() == 1) {
			return Integer.parseInt(expression);
		}

		int output = 0;
		String[] tempExpression = expression.split(" ");
		for (String temp : tempExpression) {
			// pushes the string if is int
			if (isNumber(temp)) {
				this.stack.push(temp);
			} else {
				// returns exception if the stack has too little operand to be
				// evaluated
				if (!checkSize(stack)) {
					throw new ArithmeticException();
				}

				// pops integers and evaluates them depending on the operators
				int tempInt = Integer.parseInt(stack.pop());
				int nextInt = Integer.parseInt(stack.pop());
				if (temp.equals("+")) {
					output = nextInt + tempInt;
					stack.push(output + "");
				} else if (temp.equals("-")) {
					output = nextInt - tempInt;
					stack.push(output + "");
				} else if (temp.equals("*")) {
					output = nextInt * tempInt;
					stack.push(output + "");
				} else if (temp.equals("/")) {
					output = nextInt / tempInt;
					stack.push(output + "");
				} else if (temp.equals("^")) {
					output = (int) Math.pow(nextInt, tempInt);
					stack.push(output + "");
				}
			}
		}
		return output;
	}

	/**
	 * 
	 * Checks the size of stack and return false when the size is less than 2
	 *
	 * @param stack
	 * @return boolean
	 */
	public boolean checkSize(Stack<String> stack) {
		if (stack.size() < 2) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * Counts and returns the different between the number of operand and
	 * operator Returns -1 if there is parenthesis in postfix
	 *
	 * @param expression
	 * @return int
	 */
	public int operandAndOperator(String expression) {
		int countOperand = 0;
		int countOperator = 0;
		String[] tempExpression = expression.split(" ");
		for (String temp : tempExpression) {
			if (temp.equals("(") || temp.equals(")")) {
				return -1;
			} else if (isNumber(temp)) {
				countOperand++;
			} else if (temp.equals("+") || temp.equals("-") || temp.equals("/") || temp.equals("*")
					|| temp.equals("^")) {
				countOperator++;
			}
		}
		return countOperand - countOperator;
	}

	/**
	 * 
	 * Checks if the string is integer
	 *
	 * @param str
	 * @return boolean
	 */
	public boolean isNumber(String str) {
		for (int i = str.length() - 1; i >= 0; i--) {
			int chr = str.charAt(i);
			if (chr < 48 || chr > 57)
				return false;
		}
		return true;
	}

}
