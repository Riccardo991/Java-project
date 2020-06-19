package briscola;

import java.util.*;

public class deck {
	private ArrayList<card> lDeck;
	private int nc;

	/*
	 * in briscola game we have 40 cards with 4 seeds. the card range goes from 1 to
	 * 10. Ldeck is the list of all cards.
	 */

	public deck() {
		lDeck = new ArrayList<card>();

		for (int i = 1; i <= 10; i++) {
			card c1 = new card(i, "cap");
			lDeck.add(c1);
		}
		for (int i = 1; i <= 10; i++) {
			card c2 = new card(i, "baton");
			lDeck.add(c2);
		}
		for (int i = 1; i <= 10; i++) {
			card c3 = new card(i, "swords");
			lDeck.add(c3);
		}
		for (int i = 1; i <= 10; i++) {
			card c4 = new card(i, "suns");
			lDeck.add(c4);
		}
		nc = lDeck.size() - 1;
	}

	public void ShakeDeck() {
		Collections.shuffle(lDeck);
	}

	public card getCard() {
		card c = lDeck.get(0);
		lDeck.remove(0);
		nc--;
		return c;
	}

	public void setCard(card c) {
		lDeck.add(c);
		nc++;
	}

	public card seeBris() {
		card c = lDeck.get(nc);
		return c;
	}

	public void deleteOne() {
		lDeck.remove(nc);
		nc--;
	}

	public int sizeDeck() {
		return nc;
	}

	public void printDeck() {
		for (int i = 0; i < nc; i++) {
			card c = lDeck.get(i);
			c.printCard();
			System.out.print(", ");
		}
	}

}
