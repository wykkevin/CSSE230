package mirror;

/**
 * A simple BinaryNode class.
 *
 * @author Matt Boutell. Created May 24, 2011.
 */
public class BinaryNode {
	/** Data for this node */
	int element;

	/** Left child */
	BinaryNode left;

	/** Right child */
	BinaryNode right;

	/** Right child */
	public static final BinaryNode NULL_NODE = new BinaryNode();

	/**
	 * Creates a binary node from the given arguments.
	 * 
	 * @param element
	 * @param left
	 * @param right
	 */
	public BinaryNode(int element, BinaryNode left, BinaryNode right) {
		this.element = element;
		this.left = NULL_NODE;
		this.right = NULL_NODE;
	}

	public BinaryNode() {
		this.left = null;
		this.right = null;
	}

	@Override
	public String toString() {
		return (this.left == null ? "" : this.left.toString()) + this.element
				+ (this.right == null ? "" : this.right.toString());
	}

	/**
	 * Creates a mirror reflection (left to right) of the whole tree rooted at
	 * node t.
	 *
	 * @param t
	 *            A binaryNode that is the root of a tree.
	 * @return A mirror reflection of the tree rooted at node t.
	 */
	public static BinaryNode mirror(BinaryNode t) {
		if (t == null) {
			return t;
		}
		if (t == NULL_NODE) {
			return t;
		}
		mirror(t.left);
		mirror(t.right);
		BinaryNode temp = t.left;
		t.left = t.right;
		t.right = temp;
		return t;
	}
}
