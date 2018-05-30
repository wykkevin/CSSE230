package getparent;

import java.util.NoSuchElementException;

/**
 * Exam 2. Tree methods. Write the method below. See the paper for details and
 * the unit tests for more examples.
 * 
 * @author Yuankai Wang
 */
public class BinarySearchTree {
	// Note: don't add more fields.
	BinaryNode root;

	private final BinaryNode NULL_NODE = new BinaryNode(-17); // ignore this
																// value

	public BinarySearchTree() {
		root = NULL_NODE;
	}

	/**
	 * Feel free to call from tests to use to verify the shapes of your trees
	 * while debugging. Just remove the calls you are done so the output isn't
	 * cluttered.
	 * 
	 * @return A string showing a traversal of the nodes where children are
	 *         indented so that the structure of the tree can be determined.
	 * 
	 */
	public String toIndentString() {
		return root.toIndentString("");
	}

	public BinaryNode getParent(Integer value) {
		if (this.root == NULL_NODE || this.root.data == value) {
			return null;
		} else {
			return this.root.getParent(value, this.root);
		}
	}

	// The next methods are used by the unit tests
	public void insert(Integer e) {
		root = root.insert(e);
	}

	@Override
	public String toString() {
		return root.toString();
	}

	// /////////////// BinaryNode
	public class BinaryNode {
		// Note: don't add more fields.
		public int data;
		public BinaryNode left;
		public BinaryNode right;

		public BinaryNode(int element) {
			this.data = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		// The next 2 methods are used by the unit tests. Don't change them.
		public BinaryNode insert(Integer e) {
			if (this == NULL_NODE) {
				return new BinaryNode(e);
			} else if (e.compareTo(data) < 0) {
				left = left.insert(e);
			} else if (e.compareTo(data) > 0) {
				right = right.insert(e);
			} else {
				// do nothing
			}
			return this;
		}

		@Override
		public String toString() {
			if (this == NULL_NODE) {
				return "";
			}
			return left.toString() + this.data + right.toString();
		}

		public String toIndentString(String indent) {
			if (this == NULL_NODE) {
				return indent + "NULL\n";
			}

			String myInfo = indent + String.format("%c\n", this.data);

			return myInfo + this.left.toIndentString(indent + "  ") + this.right.toIndentString(indent + "  ");
		}

		public BinaryNode getParent(Integer value, BinaryNode parent) {
			if (this == NULL_NODE) {
				throw new NoSuchElementException();
			}
			if (this.data < value) {
				return this.right.getParent(value, this);
			} else if (this.data > value) {
				return this.left.getParent(value, this);
			} else {
				return parent;
			}
		}
	}
}