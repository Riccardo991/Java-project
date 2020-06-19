package blackJack;

import java.util.*;

public class player {
	private String name;
	private ArrayList<card> cards;
	private long cash;

	/*
	 * the object player represent a player during the game, so he has a name, a set
	 * of cards, and chas. The string is for the name, the long for the cash and the
	 * list for the set of cards.
	 */

	public player() {
		name = "";
		cards = new ArrayList<card>();
		cash = 0;
	}

	public player(String s, long n) {
		name = s;
		cards = new ArrayList<card>();
		cash = n;
	}

	public String getName() {
		return name;
	}

	public long getCash() {
		return cash;
	}

	public ArrayList<card> getCards() {
		return cards;
	}

	public void setCash(long l) {
		cash = l;
	}

	public void setCards(ArrayList<card> c) {
		cards = c;
	}

	public void addCard(card c) {
		cards.add(c);
	}

	public void addCash(long l) {
		cash = cash + l;
	}

	public boolean lessCash(long l) {
		if (l < cash) {
			cash = cash - l;
			return true;
		} else {
			return false;
		}
	}

	public void printPlayer() {
		System.out.print("player: " + name + "\n");
		System.out.print("cards: ");
		for (int i = 0; i < cards.size(); i++) {
			card c = cards.get(i);
			c.printCard();
			System.out.print(", ");
		}
	}

	public void printCroupier() {
		System.out.print("croupier" + "\n");
		System.out.print("first card: ");
		card c = cards.get(0);
		c.printCard();
	}

}
