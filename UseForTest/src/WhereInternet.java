import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class WhereInternet {

	public static void main(String[] args) {
		HashMap<Integer, ArrayList<Integer>> connections = new HashMap<>();
		ArrayList<Integer> visited = new ArrayList<>();
		visited.add(1);
		ArrayList<Integer> connected = new ArrayList<>();
		connected.add(1);
		Scanner scanner = new Scanner(System.in);
		int houses = scanner.nextInt();
		int cables = scanner.nextInt();
		for (int i = 0; i < cables; i++) {
			int first = scanner.nextInt();
			int second = scanner.nextInt();
			ArrayList<Integer> temp = new ArrayList<Integer>();
			if (connections.containsKey(first)) {
				temp = connections.get(first);
				temp.add(second);
			} else {
				temp.add(second);
				connections.put(first, temp);
			}
		}
		scanner.close();
		ArrayList<Integer> onesConnection = connections.get(1);
		for (int i = 0; i < onesConnection.size(); i++) {
			findConnections(i, connections, visited, connected);
		}

	}

	public static ArrayList<Integer> findConnections(int looking, HashMap<Integer, ArrayList<Integer>> connections,
			ArrayList<Integer> visited, ArrayList<Integer> connected) {
		if (!visited.contains(looking)) {

		}
		return connected;

	}

}
