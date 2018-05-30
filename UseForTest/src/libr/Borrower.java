package libr;

import java.util.ArrayList;

public class Borrower {
	private ArrayList<Media> borrowed = new ArrayList<Media>();
	private boolean allowed = true;

	public void borrowMedia(Media m) {
		if (this.allowed) {
			if (m.borrowed) {
				System.out.println("Already borrowed");
			} else {
				this.borrowed.add(m);
				m.borrowed = true;
				m.currentOwner = this;
			}
		} else {
			System.out.println("No permission");
		}
	}

	public void returnMedia(Media m) {
		if (this.borrowed.contains(m)) {
			this.borrowed.remove(m);
			m.borrowed = false;
			m.currentOwner = null;
		} else {
			System.out.println("Not a holding media");
		}
	}

}
