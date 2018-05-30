package Problems;

import java.util.Scanner;
import java.util.Stack;

public class NetXtern {

	/*
	 * We will several inputs in different types to control the url. We will
	 * need to output every website we visited.
	 */
	public static void main(String[] args) throws Exception {
		// I use two stacks to store the backs and forwards. I just divide input
		// into several cases and deal with them individually.
		String output = "";
		Stack<String> backToNow = new Stack<String>();
		Stack<String> forward = new Stack<String>();
		Scanner scanner = new Scanner(System.in);
		String t = scanner.nextLine();
		String currentMain = "";
		while (scanner.hasNextLine() && !t.isEmpty()) {
			if (t.equals("BACK")) {
				if (backToNow.size() == 1) {
					throw new Exception("Nothing to back");
				}
				String pre = backToNow.pop();
				forward.push(pre);
				String before = backToNow.peek();
				output += before + "\n";
			} else if (t.equals("FORWARD")) {
				if (forward.isEmpty()) {
					throw new Exception("Nothing to forward");
				}
				String forw = forward.pop();
				output += forw + "\n";
				backToNow.push(forw);
			} else if (t.equals("query")) {
				String addr = backToNow.pop();
				addr += "/query";
				backToNow.push(addr);
				output += addr + "\n";
			} else if (t.substring(0, 1).equals("/")) {
				if (currentMain.equals("")) {
					throw new Exception("No relative address available");
				}
				String addr = currentMain + t;
				output += addr;
				backToNow.push(addr);
				output += "\n";
			} else if (t.length() > 8 && t.substring(0, 8).equals("https://")) {
				backToNow.push(t);
				currentMain = t;
				output += t + "\n";
			} else {
				String addr = backToNow.peek();
				addr += "/" + t;
				output += addr + "\n";
				backToNow.push(addr);
			}

			t = scanner.nextLine();
			if (t.isEmpty()) {
				break;
			}
		}
		scanner.close();
		System.out.println(output.substring(0, output.length() - 1));
	}
}
