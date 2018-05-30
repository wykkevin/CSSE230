/**
 * More Binary Tree practice problems. This problem creates BSTs of type
 * Integer: 1. Neither problem makes use of the BST ordering property; I just
 * found insert() to be a convenient way to build trees for testing. 2. I used
 * Integer instead of T since the makeTree method sets the data value of each
 * node to be a depth, which is an Integer.
 * 
 * @author Matt Boutell and Yuankai Wang.
 * @param <T>
 */

/*
 * DONE: 0 You are to implement the methods below. Use recursion!
 */
public class BinarySearchTree {

	private BinaryNode root;

	private final BinaryNode NULL_NODE = new BinaryNode(null);

	public BinarySearchTree() {
		root = NULL_NODE;
	}

	/**
	 * This constructor creates a full tree of Integers, where the value of each
	 * node is just the depth of that node in the tree.
	 * 
	 * @param maxDepth
	 *            The depth of the leaves in the tree.
	 */
	public BinarySearchTree(int maxDepth) {
		// DONE: 1 Write this.
		// Hint: You may find it easier if your recursive helper method is
		// outside of the BinaryNode class.
		root = NULL_NODE;
		if (maxDepth >= 0) {
			insert(0);
			root = root.insertAtXLevel(maxDepth, 1, 0);
		}
	}

	public int getSumOfHeights() {
		// DONE. 2 Write this.
		// Can you do it in O(n) time instead of O(n log n) by avoiding repeated
		// calls to height()?
		SumWrapper wrapper = new SumWrapper(0, 0);
		return root.getSumOfHeights(wrapper).sum;
	}

	// These are here for testing.
	public void insert(Integer e) {
		root = root.insert(e);
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

		public Integer data;
		public BinaryNode left;
		public BinaryNode right;

		public BinaryNode(Integer element) {
			this.data = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

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

		public String toStructuredString() {
			if (this == NULL_NODE) {
				return "";
			}
			return "[" + left.toStructuredString() + this.data + right.toStructuredString() + "]";
		}

		public BinaryNode insertAtXLevel(int maxDepth, int itsDepth, int count) {
			if (count >= (Math.pow(2, itsDepth))) {
				itsDepth++;
				count = 0;
			}
			if (itsDepth <= maxDepth) {
				BinaryNode add = new BinaryNode(itsDepth);
				this.left = add;
				this.right = add;
				count += 2;
				left.insertAtXLevel(maxDepth, itsDepth + 1, count);
				right.insertAtXLevel(maxDepth, itsDepth + 1, count);
			}
			return this;
		}

		public SumWrapper getSumOfHeights(SumWrapper wrapper) {
			int height = 0;
			if (this.left != NULL_NODE && this.right != NULL_NODE) {
				height = 1 + Math.max(left.getSumOfHeights(wrapper).height, right.getSumOfHeights(wrapper).height);
			} else if (this.left != NULL_NODE) {
				height += 1 + left.getSumOfHeights(wrapper).height;
			} else if (this.right != NULL_NODE) {
				height += 1 + right.getSumOfHeights(wrapper).height;
			}
			wrapper.sum += height;
			wrapper.height = height;
			return wrapper;
		}
	}

	public class SumWrapper {
		private int sum;
		private int height;

		public SumWrapper(int sum, int height) {
			this.sum = sum;
			this.height = height;
		}
	}
}