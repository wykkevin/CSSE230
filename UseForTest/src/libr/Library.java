package libr;

import java.util.ArrayList;

public class Library {

	public ArrayList<Media> inventory = new ArrayList<>();
	public ArrayList<Borrower> borrowers = new ArrayList<>();

	public void sortByIndex() {

	}

	public void addMedia(Media m) {
		this.inventory.add(m);
	}

	public void addBorrower(Borrower b) {
		this.borrowers.add(b);
	}

	public static void main(String[] args) {

	}

}
