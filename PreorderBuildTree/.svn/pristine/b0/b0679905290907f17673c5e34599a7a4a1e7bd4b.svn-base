package buildtree;

import java.util.Stack;

/**
 * 
 * @author Matt Boutell and Yuankai Wang.
 * @param <T>
 */

public class BinaryTree {

	private BinaryNode root;

	private final BinaryNode NULL_NODE = new BinaryNode(null);

	public BinaryTree() {
		root = NULL_NODE;
	}

	/**
	 * Constructs a tree (any tree of characters, not just a BST) with the given
	 * values and number of children, given in a pre-order traversal order. See
	 * the HW spec for more details.
	 * 
	 * @param chars
	 *            One char per node.
	 * @param children
	 *            L,R, 2, or 0.
	 */
	public BinaryTree(String chars, String children) {
		// TODO: Implement this constructor. You may not add any other fields to
		// the BinaryTree class, but you may add local variables and helper
		// methods if you like.
		if (chars.length() == 0) {
			root = NULL_NODE;
		}
		Stack<BinaryNode> s = new Stack<BinaryNode>();
		for (int index = chars.length() - 1; index >= 0; index--) {
			BinaryNode node = new BinaryNode(chars.charAt(index));
			if (children.charAt(index) == ('0')) {
				s.push(node);
			} else if (children.charAt(index) == ('R')) {
				node.right = s.pop();
				s.push(node);
			} else if (children.charAt(index) == ('L')) {
				node.left = s.pop();
				s.push(node);
			} else {
				node.left = s.pop();
				node.right = s.pop();
				s.push(node);
			}
			if (index == 0) {
				root = node;
			}
		}
	}

	/**
	 * In-order traversal of the characters
	 */
	@Override
	public String toString() {
		return root.toString();
	}

	/**
	 * @return A string showing an in-order traversal of nodes with extra
	 *         brackets so that the structure of the tree can be determined.
	 */
	public String toStructuredString() {
		return root.toStructuredString();
	}

	// /////////////// BinaryNode
	public class BinaryNode {

		public Character data;
		public BinaryNode left;
		public BinaryNode right;

		public BinaryNode(Character element) {
			this.data = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		@Override
		public String toString() {
			if (this == NULL_NODE) {
				return "";
			}
			return left.toString() + data + right.toString();
		}

		public String toStructuredString() {
			if (this == NULL_NODE) {
				return "";
			}
			return "(" + left.toStructuredString() + this.data + right.toStructuredString() + ")";
		}

	}
}