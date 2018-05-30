import java.util.LinkedList;
import java.util.Queue;
/**
 * 
 * This class extends the ChainManager and overrides
 * the add, next, and isEmpty method inorder to implement
 * a queue.
 *
 * @author Josh Kuhn and Yuankai Wang
 *         Created Mar 27, 2017.
 */
public class QueueChainManager extends ChainManager {

	Queue<Chain> queue = new LinkedList<Chain>();

	@Override
	public void add(Chain chain) {
		queue.add(chain);
		this.updateMax(this.queue.size());
	}

	@Override
	public Chain next() {
		this.incrementNumNexts();
		return queue.remove();
	}

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

}
