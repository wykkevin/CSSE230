package countsame;

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

	public int countSame() {
		CountWrapper output = this.root.countSame();
		return output.count;
	}

	// The next methods are used by the unit tests. Don't change them.
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

		public CountWrapper countSame() {
			if (this == NULL_NODE) {
				return new CountWrapper(0, -1);
			}
			CountWrapper left = this.left.countSame();
			CountWrapper right = this.right.countSame();
			if (left.height == right.height) {
				return new CountWrapper(left.count + right.count + 1, 1 + left.height);
			} else {
				return new CountWrapper(left.count + right.count, 1 + Math.max(left.height, right.height));
			}
		}
	}

	class CountWrapper {
		int count;
		int height;

		public CountWrapper(int count, int height) {
			this.count = count;
			this.height = height;
		}
	}
}