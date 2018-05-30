import java.util.Stack;
/**
 * 
 * This class extends ChainManager and overrides the add, 
 * next, and isEmpty methods inorder to impliment a stack.
 *
 * @author Josh Kuhn and Yuankai Wang
 *         Created Mar 27, 2017.
 */
public class StackChainManager extends ChainManager {

	private Stack<Chain> stack = new Stack<Chain>();

	@Override
	public void add(Chain chain) {
		stack.push(chain);
		this.updateMax(this.stack.size());
	}

	@Override
	public Chain next() {
		this.incrementNumNexts();
		return stack.pop();
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

}
