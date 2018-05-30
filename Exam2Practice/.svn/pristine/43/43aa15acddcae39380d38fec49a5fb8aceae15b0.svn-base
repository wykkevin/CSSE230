package averagenodevalue;

/**
 * @author You!
 * @param <T>
 */
public class BinarySearchTree {

	BinaryNode root;

	private final BinaryNode NULL_NODE = new BinaryNode(null);

	public BinarySearchTree() {
		root = NULL_NODE;
	}

	/**
	 * Finds the average numeric value of every node in this tree. For example,
	 * a tree with root = 10 and children = 5 and 20 would return (10+5+20)/3 =
	 * 11.666666.
	 * 
	 * See spec for hints and efficiency requirements.
	 * 
	 * @return The average value of the nodes in this tree.
	 */
	public double averageValue() {
		if (this.root == NULL_NODE) {
			return 0.0;
		}
		ValueWrapper output = this.root.averageValue();
		return output.sum / output.num;
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

		public Integer data;
		public BinaryNode left;
		public BinaryNode right;

		public BinaryNode(Integer data) {
			this.data = data;
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

		@Override
		public String toString() {
			if (this == NULL_NODE) {
				return "";
			}
			return left.toString() + this.data.toString() + right.toString();
		}

		public String toIndentString(String indent) {
			if (this == NULL_NODE) {
				return indent + "NULL\n";
			}

			String myInfo = indent + String.format("%c\n", this.data);

			return myInfo + this.left.toIndentString(indent + "  ") + this.right.toIndentString(indent + "  ");
		}

		public ValueWrapper averageValue() {
			if (this == NULL_NODE) {
				return new ValueWrapper(0, 0);
			}
			ValueWrapper temp1 = this.left.averageValue();
			ValueWrapper temp2 = this.right.averageValue();
			return new ValueWrapper(temp1.sum + temp2.sum + this.data, temp1.num + temp2.num + 1);
		}
	}

	class ValueWrapper {
		int num;
		double sum;

		public ValueWrapper(double sum, int num) {
			this.sum = sum;
			this.num = num;
		}
	}
}