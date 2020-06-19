package blackJack;

import java.util.*;

public class deck {
	private ArrayList<card> lDeck;
	private int nc;

	/*
	 * the object deck is the deck of cards . in blackjack there are 52 cards, from
	 * 1 to 13 and 4 different seeds. we put all cards in lDeck list.
	 */

	public deck(int nd) {
		lDeck = new ArrayList<card>();
		while (nd > 0) {
			for (int i = 1; i <= 13; i++) {
				card c1 = new card(i, "Club ");
				lDeck.add(c1);
			}
			for (int i = 1; i <= 13; i++) {
				card c2 = new card(i, "Diamond ");
				lDeck.add(c2);
			}
			for (int i = 1; i <= 13; i++) {
				card c3 = new card(i, "Heart ");
				lDeck.add(c3);
			}
			for (int i = 1; i <= 13; i++) {
				card c4 = new card(i, "Spade ");
				lDeck.add(c4);
			}
			nd--;
		}
		nc = lDeck.size() - 1;
	}

	public void ShakeDeck() {
		Collections.shuffle(lDeck);
	}

	public card getOneCard() {
		card c = lDeck.get(0);
		lDeck.remove(0);
		nc--;
		return c;
	}

	public ArrayList<card> get2Cards() {
		ArrayList<card> l = new ArrayList<card>();
		if (nc > 2) {
			for (int i = 0; i < 2; i++) {
				card c = lDeck.get(i);
				l.add(c);
				nc--;
			}
			for (int j = 0; j < 2; j++) {
				lDeck.remove(0);
			}
		}
		return l;
	}

	public void setCard(card c) {
		lDeck.add(c);
		nc++;
	}

	public int getSize() {
		return nc;
	}

	public void printDeck() {
		for (int i = 0; i <= nc; i++) {
			card c = lDeck.get(i);
			c.printCard();
			System.out.print(", ");
		}
	}

}
