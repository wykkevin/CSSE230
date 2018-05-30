package evaluator;

import java.util.Stack;

/**
 * 
 * Evaulates Infix
 *
 * @author kongh and wangy16. Created Mar 19, 2017.
 */

public class InfixEvaluator extends Evaluator {

	private Stack<String> stack;
	private boolean isNumberBefore = false;
	private int leftBracket = 0;
	private int rightBracket = 0;
	private boolean isInFirstParenthesis = false;
	private boolean isInSecondParenthesis = false;

	public InfixEvaluator() {
		this.stack = new Stack<String>();
	}

	/**
	 * Evaluate the infix by converting it to postfix first, then use the
	 * evaluate in postfix to evaluate it.
	 */
	@Override
	public int evaluate(String expression) throws ArithmeticException {
		if (!isConvertable(expression)) {
			throw new ArithmeticException();
		}
		String inputForpost = convertToPostfix(expression);
		PostfixEvaluator post = new PostfixEvaluator();
		int output = post.evaluate(inputForpost);

		return output;
	}

	/**
	 * Discuss the parenthesis first and put in the output in sequence according
	 * to the priority of each operator. The operator in a parenthesis is
	 * discussed first then combine all the things together.
	 */
	public String convertToPostfix(String exp) {
		if (!isConvertable(exp)) {
			throw new ArithmeticException();
		}
		String output = "";
		String tempInParathesis = "";
		String tempInSecondParathesis = "";
		String[] input = exp.split(" ");
		for (int i = 0; i < input.length; i++) {
			if (!(isInSecondParenthesis || this.isInFirstParenthesis)) {
				if (input[i].equals("(")) {
					isInFirstParenthesis = true;
				}
			} else if (!isInSecondParenthesis && this.isInFirstParenthesis) {
				if (input[i].equals("(")) {
					isInSecondParenthesis = true;
				}
				if (input[i].equals(")")) {
					isInFirstParenthesis = false;
				}
			} else if (isInSecondParenthesis && this.isInFirstParenthesis) {
				if (input[i].equals(")")) {
					isInSecondParenthesis = false;
				}
			}
			if (isInSecondParenthesis) {
				if (isNumber(input[i])) {
					output += input[i] + " ";
				} else if (!input[i].equals("(")) {
					if (!isPriorerToString(input[i], tempInSecondParathesis)) {
						output += tempInSecondParathesis;
						tempInSecondParathesis = input[i] + " ";
					} else {
						tempInSecondParathesis = input[i] + " " + tempInSecondParathesis;
					}
				}
			} else if (isInFirstParenthesis) {
				output += tempInSecondParathesis;
				tempInSecondParathesis = "";
				if (isNumber(input[i])) {
					output += input[i] + " ";
				} else if (!(input[i].equals("(") || input[i].equals(")"))) {
					if (!isPriorerToString(input[i], tempInParathesis)) {
						output += tempInParathesis;
						tempInParathesis = input[i] + " ";
					} else {
						tempInParathesis = input[i] + " " + tempInParathesis;
					}
				}
			} else {
				output += tempInParathesis;
				tempInParathesis = "";
				if (isNumber(input[i])) {
					output += input[i] + " ";
				} else if (!input[i].equals(")")) {
					while (!isPriorerToStack(input[i])) {
						output += this.stack.pop() + " ";
					}
					this.stack.push(input[i]);
				}
			}
		}
		while (!this.stack.isEmpty()) {
			output += this.stack.pop() + " ";
		}
		return output.substring(0, output.length() - 1);
	}

	/**
	 * Check to see if the infix has a good input
	 */
	public boolean isConvertable(String e) {
		isNumberBefore = false;
		String[] input = e.split(" ");
		for (int i = 0; i < input.length; i++) {
			if (input[i].equals("(")) {
				leftBracket++;
				input[i] = null;
			} else if (input[i].equals(")")) {
				rightBracket++;
				input[i] = null;
			}
			if (input[i] != null) {
				if (isNumberBefore) {
					if (!isNumber(input[i])) {
						isNumberBefore = false;
					} else {
						return false;
					}
				} else {
					if (isNumber(input[i])) {
						isNumberBefore = true;
					} else {
						return false;
					}
				}
			}
		}
		if (leftBracket != rightBracket) {
			return false;
		}
		return true;
	}

	/**
	 * Use ascii code to check if the input is a number. For numbers more than a
	 * digit, we check it one digit by one digit.
	 */
	public boolean isNumber(String str) {
		for (int i = str.length() - 1; i >= 0; i--) {
			int chr = str.charAt(i);
			if (chr < 48 || chr > 57)
				return false;
		}
		return true;
	}

	/**
	 * Decide the priority of each operator and express it by int.
	 */
	public int priority(String str) {
		if (str.equals("+") || str.equals("-")) {
			return 1;
		} else if (str.equals("*") || str.equals("/")) {
			return 2;
		} else if (str.equals("^")) {
			return 3;
		} else if (str.equals("(")) {
			return 4;
		} else {
			return 0;
		}
	}

	public boolean isPriorerToStack(String str) {
		if (this.stack.isEmpty()) {
			return true;
		}
		String a = this.stack.pop();
		this.stack.push(a);
		if (priority(str) > priority(a)) {
			return true;
		}
		return false;
	}

	public boolean isPriorerToString(String str, String prev) {
		if (prev.equals("")) {
			return true;
		}
		if (priority(str) > priority(prev.substring(prev.length() - 2, prev.length() - 1))) {
			return true;
		}
		return false;
	}
}
