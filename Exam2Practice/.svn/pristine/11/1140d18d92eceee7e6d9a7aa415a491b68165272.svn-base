package preOrderInOrderBuild;

// This is the class you should modify for problem 2B.

public class PreorderInorderBuild {

	public static BinaryNode buildFromPreOrderInorder(String pre, String in) {
		// TODO: Fill in the details of this method,
		// and perhaps add a recursive helper method.
		// Suggestion: For the recursive calls, you probably want to pass
		// substrings of the pre and in strings.

		if (pre.length() == 0 || in.length() == 0) {
			return null;
		}
		BinaryNode root = new BinaryNode(pre.charAt(0), null, null);
		String left = "";
		String right = "";
		char rootChar = pre.charAt(0);
		for (int i = 0; i < in.length(); i++) {
			if (in.charAt(i) == rootChar) {
				left = in.substring(0, i);
				right = in.substring(i + 1);
				break;
			}
		}
		root.left = buildFromPreOrderInorder(pre.substring(1), left);
		root.right = buildFromPreOrderInorder(pre.substring(1), right);
		return root;
	}
}
