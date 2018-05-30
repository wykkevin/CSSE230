import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/**
 * 
 * This class hold the methods for the chain of linked words. While 
 * chains are immutable they also support the addition of new words 
 * to the end and are iterable.
 *
 * @author Josh Kuhn and Yuankai Wang
 *         Created Mar 27, 2017.
 */
public class Chain {

	private Set<String> chainSet = new HashSet<String>();
	private ArrayList<String> chainList = new ArrayList<String>();

	public int length() {
		return chainList.size();
	}

	public boolean contains(String string) {
		return chainSet.contains(string);
	}

	public Iterator<String> iterator() {
		return chainList.iterator();
	}

	public String getLast() {
		return chainList.get(chainList.size() - 1);
	}

	public Chain addLast(String string) {
		Chain newChain = new Chain();
		newChain.chainSet.addAll(this.chainSet);
		newChain.chainList.addAll(this.chainList);
		if (newChain.chainSet.add(string)) {
			newChain.chainList.add(string);
		}
		return newChain;
	}

	@Override
	public String toString() {
		String output = "[";
		for (String s : this.chainList) {
			output += s + ", ";
		}
		output = output.substring(0, output.length() - 2);
		output += "]";
		return output;

	}
}
