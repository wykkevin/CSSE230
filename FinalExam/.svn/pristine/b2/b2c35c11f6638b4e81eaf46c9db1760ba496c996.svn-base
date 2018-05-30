package ancestor;

public class BinarySearchTree {

	BinaryNode root;

	// The -17 is arbitrary -any int would be fine since we never refer to it.
	final BinaryNode NULL_NODE = new BinaryNode(-17);

	public BinarySearchTree() {
		root = NULL_NODE;
	}

	public boolean properAncestorOf(Integer a, Integer b) {
		return this.root.properAncestorOf(a, b);
	}

	// The next methods are used by the unit tests
	public void insert(Integer e) {
		root = root.insert(e);
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

	// /////////////// BinaryNode
	public class BinaryNode {

		public Integer data;
		public BinaryNode left;
		public BinaryNode right;

		// The rest of the methods are used by the unit tests and for debugging
		public BinaryNode(Integer element) {
			this.data = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		public BinaryNode insert(Integer e) {
			if (this == NULL_NODE) {
				return new BinaryNode(e);
			} else if (e < data) {
				left = left.insert(e);
			} else if (e > data) {
				right = right.insert(e);
			} else {
				// do nothing
			}
			return this;
		}

		public boolean properAncestorOf(Integer a, Integer b) {
			if (this == NULL_NODE) {
				return false;
			}

			if (a < this.data) {
				return this.left.properAncestorOf(a, b);
			} else if (a > this.data) {
				return this.right.properAncestorOf(a, b);
			} else {
				if (b == a) {
					return false;
				}
				return contains(b);
			}
		}

		public boolean contains(Integer b) {
			if (this == NULL_NODE) {
				return false;
			}
			if (b < this.data) {
				return this.left.contains(b);
			} else if (b > this.data) {
				return this.right.contains(b);
			} else {
				return true;
			}
		}
	}
}