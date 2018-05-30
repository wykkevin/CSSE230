import java.util.Scanner;
import java.util.Set;

/**
 * 
 * This class conains the main method and handles the user interface. 
 * Yuankai and I recieved some help with the the scanner sytntax 
 * from Yuqi Zhou.  
 *
 * @author Josh Kuhn and Yuankai Wang
 *         Created Mar 27, 2017.
 */
public class Doublets {
	private LinksInterface links;
	private Chain chain;

	public Doublets(LinksInterface links) {
		this.links = links;
		this.chain = new Chain();
	}

	public static void main(String[] args) {
		System.out.println("Welcome to Doublets, a game of verbal torture.");
		Links link = new Links("english.cleaned.all.35.txt");
		Doublets doublets = new Doublets(link);
		Scanner scanner = new Scanner(System.in);
		while (true) {

			System.out.print("Enter starting word: ");
			String start = scanner.nextLine();
			System.out.print("Enter ending word: ");
			String end = scanner.nextLine();
			System.out.print("Enter chain manager (s: stack, q: queue, p: priority queue, x: exit):");
			String managerString = scanner.nextLine();
			ChainManager manager;
			if (managerString.equals("s")) {
				manager = new StackChainManager();
			} else if (managerString.equals("q")) {
				manager = new QueueChainManager();
			} else if (managerString.equals("p")) {
				manager = new PriorityQueueChainManager(end);
			} else if (managerString.equals("x")) {
				System.out.println("Goodbye!");
				break;
			} else {
				System.out.println("wrong manager");
				continue;
			}
			if (!link.exists(start)) {
				System.out.println("The word " + start + " is not valid. Please try again.");
			} else if (start.length() != end.length()) {
				System.out.println("different length");
			} else {
				Chain chain = doublets.findChain(start, end, manager);
				System.out.println(chain.toString());
				System.out.println("Length: " + chain.length());
				System.out.println("Candidates: " + manager.getNumberOfNexts());
				System.out.println("Max size: " + manager.maxSize());
			}
		}
	}

	public Chain findChain(String start, String end, ChainManager manager) {
		if (start.length() != end.length()) {
			return null;
		}
		chain = new Chain();
		chain = chain.addLast(start);
		manager.add(chain);
		while (true) {
			chain = manager.next();
			if (chain.getLast().equals(end)) {
				break;
			}
			Set<String> tempCandidates = links.getCandidates(chain.getLast());
			if (tempCandidates != null) {
				for (String s : tempCandidates) {
					if (!this.chain.contains(s)) {
						Chain pushChain = this.chain.addLast(s);
						manager.add(pushChain);
					}
				}
			}
			if (manager.isEmpty()) {
				System.out.println("No doublet chain exists from owner to bribe.");
				return null;
			}
		}
		return chain;
	}
}
