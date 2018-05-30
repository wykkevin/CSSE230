import java.util.ArrayList;
import java.util.Collections;

public class SortHandOfCards {

	public static void main(String[] args) {
		ArrayList<Card> test = new ArrayList<Card>();
		test.add(new Card('A', "Heart"));
		test.add(new Card('J', "Diamond"));
		test.add(new Card('A', "Diamond"));
		System.out.println(sortHandOfCards(test).get(0).value);
		System.out.println(sortHandOfCards(test).get(0).suit);
		System.out.println(sortHandOfCards(test).get(1).value);
		System.out.println(sortHandOfCards(test).get(1).suit);
		System.out.println(sortHandOfCards(test).get(2).value);
		System.out.println(sortHandOfCards(test).get(2).suit);
	}

	public static ArrayList<Card> sortHandOfCards(ArrayList<Card> input) {
		Collections.sort(input);
		return input;
	}

	public static class Card implements Comparable {
		char value;
		String suit;
		int transValue;

		public Card(char value, String suit) {
			this.value = value;
			this.suit = suit;
			if (this.value == 'A') {
				this.transValue = 10;
			} else if (this.value == '0') {
				this.transValue = 100;
			} else if (this.value == 'J') {
				this.transValue = 110;
			} else if (this.value == 'Q') {
				this.transValue = 120;
			} else if (this.value == 'K') {
				this.transValue = 130;
			} else {
				this.transValue = (((int) this.value) - 48) * 10;
			}
			if (this.suit.equals("Heart")) {
				this.transValue += 1;
			} else if (this.suit.equals("Diamond")) {
				this.transValue += 2;
			} else if (this.suit.equals("Club")) {
				this.transValue += 3;
			} else if (this.suit.equals("Spade")) {
				this.transValue += 4;
			}
		}

		@Override
		public int compareTo(Object arg0) {
			if (this.transValue < ((Card) arg0).transValue) {
				return -1;
			}
			return 1;
		}
	}
}
