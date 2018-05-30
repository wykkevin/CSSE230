package subtree;

import java.util.Stack;

/*
 * TODO: 0 Directions: (1) Be sure to read the directions at the top of the test
 * paper. (2) Implement the given methods. See the paper for details.
 */
public class BinaryTree {
	private BinaryNode root;

	private final BinaryNode NULL_NODE = new BinaryNode('$', 0);

	public BinaryTree() {
		root = NULL_NODE;
	}

	/** See the paper for details */
	public String maxSubtree() {
		RootandSum temp = this.root.maxSubtree();
		return "" + temp.root + temp.maxSum;
	}

	/**
	 * If you already got more than 5 points on the previous problem, just skip
	 * this one. But if you are stuck on the maxsubtree problem, and are
	 * currently earning less than 5 points, I encourage you to do this problem.
	 * While it is only worth 5 points, we will give you the max score of the
	 * maxsubtree problem and the sum problem so it should be a straightforward
	 * way to get 5 points. More importantly, solving it may give you some ideas
	 * on the previous problem.
	 * 
	 * @return The sum of the numeric values of the nodes in this tree. If the
	 *         tree is empty, returns 0. The sum of the nodes can be negative.
	 * 
	 */
	public int sum() {
		// CONSIDER: 2 Write this.
		return Integer.MIN_VALUE;
	}

	// /////////////// BinaryNode
	public class BinaryNode {

		// Note there are two "data" fields: a label and a value:
		public Character label;
		public int value;
		public BinaryNode left;
		public BinaryNode right;

		public BinaryNode(Character label, int value) {
			this.label = label;
			this.value = value;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		public RootandSum maxSubtree() {
			if (this == NULL_NODE) {
				return new RootandSum('$', 0, 0);
			}
			RootandSum leftWrapper = this.left.maxSubtree();
			int left = leftWrapper.maxSum;
			RootandSum rightWrapper = this.right.maxSubtree();
			int right = rightWrapper.maxSum;
			int thisSum = leftWrapper.sum + this.value + rightWrapper.sum;
			if (left >= right) {
				if (thisSum <= left) {
					return new RootandSum(leftWrapper.root, leftWrapper.maxSum, thisSum);
				} else {
					return new RootandSum(this.label, thisSum, thisSum);
				}

			} else {
				if (thisSum < right) {
					return new RootandSum(rightWrapper.root, rightWrapper.maxSum, thisSum);
				} else {
					return new RootandSum(this.label, thisSum, thisSum);
				}
			}

		}

		// The next methods are used by the unit tests
		@Override
		public String toString() {
			if (this == NULL_NODE) {
				return "";
			}
			return left.toString() + this.label.toString() + right.toString();
		}

		public int height() {
			if (this == NULL_NODE) {
				return -1;
			}

			return 1 + Math.max(this.left.height(), this.right.height());
		}

		public int size() {
			if (this == NULL_NODE) {
				return 0;
			}
			return 1 + left.size() + right.size();
		}
	}

	// The next 4 methods are used by the unit tests
	@Override
	public String toString() {
		return root.toString();
	}

	/**
	 * This constructs a tree according to the preorder method introduced in
	 * displayable binary tree.
	 *
	 * @param charElements
	 * @param childCodes
	 */
	public BinaryTree(CharSequence charElements, CharSequence childCodes, int[] values) {
		root = preOrderBuild(charElements, childCodes, values);
	}

	/**
	 * Generates a binary tree whose node contents and pre-order traversal order
	 * come from charElements. For each of those nodes, the corresponding
	 * element in childCodes indicates the number of children, where L means one
	 * left child, R means one right child, and 0 and 2 mean zero and two
	 * children respectively.
	 *
	 * @param charElements
	 * @param childCodes
	 * @return the binary tree constructed from charElements and childCodes
	 */
	public BinaryNode preOrderBuild(CharSequence charElements, CharSequence childCodes, int[] values) {
		Stack<BinaryNode> stack = new Stack<BinaryNode>();
		for (int i = charElements.length() - 1; i >= 0; i--) {
			char label = charElements.charAt(i);
			char code = childCodes.charAt(i);
			int value = values[i];
			BinaryNode left = NULL_NODE, right = NULL_NODE;
			if (code == 'L' || code == '2') {
				left = stack.pop();
			}
			if (code == 'R' || code == '2') {
				right = stack.pop();
			}
			BinaryNode node = new BinaryNode(label, value);
			node.left = left;
			node.right = right;
			stack.push(node);
		}
		return stack.pop();
	}

	public int height() {
		return root.height();
	}

	public int size() {
		return root.size();
	}

	class RootandSum {
		private Character root;
		private int maxSum;
		private int sum;

		public RootandSum(Character root, int maxSum, int sum) {
			this.root = root;
			this.maxSum = maxSum;
			this.sum = sum;
		}
	}
}