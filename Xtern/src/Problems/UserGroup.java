package Problems;

import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class UserGroup {

	/*
	 * This class take several lines of input which format looks like
	 * "name group". We output several lines of group look like
	 * "groupName,namesInGroup".
	 */

	public static void main(String[] args) {
		// I use trees to store both groups and in group names. So when I add
		// them, they will be ordered.
		String output = "";
		TreeMap<String, TreeSet> groups = new TreeMap<String, TreeSet>();
		Scanner scanner = new Scanner(System.in);
		String t = scanner.nextLine();
		while (scanner.hasNextLine() && !t.isEmpty()) {
			for (int i = 0; i < t.length(); i++) {
				if (t.charAt(i) == ' ') {
					String name = t.substring(0, i);
					String group = t.substring(i + 1);
					if (groups.containsKey(group)) {
						TreeSet<String> temp = groups.get(group);
						temp.add(name);
						groups.put(group, temp);
					} else {
						TreeSet<String> newTree = new TreeSet<String>();
						newTree.add(name);
						groups.put(group, newTree);
					}
				}
			}
			t = scanner.nextLine();
			if (t.isEmpty()) {
				break;
			}
		}
		scanner.close();

		// Then just use iterator to get these things in sequence and add them
		// to output.
		Iterator<String> groupIter = groups.keySet().iterator();
		while (groupIter.hasNext()) {
			String g = groupIter.next();
			output += g + ",";
			TreeSet<String> inGroupNames = groups.get(g);
			Iterator nameIter = inGroupNames.iterator();
			while (nameIter.hasNext()) {
				output += nameIter.next() + ",";
			}
			output = output.substring(0, output.length() - 1);
			output += "\n";
		}
		System.out.println(output.substring(0, output.length() - 1));
	}
}
