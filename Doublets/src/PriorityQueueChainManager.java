import java.util.PriorityQueue;
/**
 * 
 * This class extends the ChainManager and utalizes a 
 * priority queue to create small chains. The prority
 * is assigned based on the number of characters with 
 * the two given words which are different.  
 *
 * @author Josh Kuhn and Yuankai Wang
 *         Created Mar 27, 2017.
 */
public class PriorityQueueChainManager extends ChainManager {

	private PriorityQueue<Entry> priorityQueue = new PriorityQueue<Entry>();
	private String end;

	public PriorityQueueChainManager(String end) {
		this.end = end;
	}

	@Override
	public void add(Chain chain) {
		priorityQueue.offer(new Entry(chain));
		this.updateMax(this.priorityQueue.size());
	}

	@Override
	public Chain next() {
		Entry remove = this.priorityQueue.poll();
		this.incrementNumNexts();
		return remove.chain;
	}

	@Override
	public boolean isEmpty() {
		return priorityQueue.isEmpty();
	}

	private int numOfCharDiff(String now) {
		int diffChar = 0;
		if (now.length() == end.length()) {
			for (int i = 0; i < end.length(); i++) {
				if (!now.substring(i, i + 1).equals(end.substring(i, i + 1))) {
					diffChar++;
				}
			}
		}
		return diffChar;

	}
	
	
	/**
	 * 
	 * This inner class implements Comaparable and provides methods to get 
	 * the weight (priority) of each word. This also contains a compareTo 
	 * method returns a value in coordination with the weight of the words 
	 * being compared.
	 *
	 * @author Josh Kuhn and Yuankai Wang
	 *         Created Mar 27, 2017.
	 */
	class Entry implements Comparable<Entry> {

		private Chain chain;
		private int weight;

		public Entry(Chain chain) {
			this.chain = chain;
			this.weight = this.chain.length() + numOfCharDiff(this.chain.getLast());
		}

		public int getWeight() {
			return this.weight;
		}

		public Chain getChain() {
			return this.chain;
		}

		@Override
		public int compareTo(Entry e) {
			if (this.weight > e.weight) {
				return 1;
			} else if (this.weight == e.weight) {
				return 0;
			} else {
				return -1;
			}
		}

	}
}
