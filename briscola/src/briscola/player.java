package briscola;

import java.util.*;

public class player {
	private String name;
	private ArrayList<card> hand;
	private int points;

	/*
	 * object player represents a player during the game. it has a name, a set of
	 * cards and during the match it can improve its points.
	 */

	public player(String s) {
		name = s;
		hand = new ArrayList<card>();
		points = 0;
	}

	public player(String s, ArrayList<card> l) {
		name = s;
		hand = l;
		points = 0;
	}

	public String getName() {
		return name;
	}

	public ArrayList<card> getHand() {
		return hand;
	}

	public int getPoints() {
		return points;
	}

	public void setHand(ArrayList<card> c) {
		hand = c;
	}

	public void setPoint(int n) {
		points = n;
	}

	public void addCard(card c) {
		if (hand.size() < 4) {
			hand.add(c);
		}
	}

	public void addPoints(int n) {
		points = points + n;
	}

	public card putDonw(int n) {
		card c = hand.get(n);
		hand.remove(n);
		return c;
	}

	public void seeHand() {
		for (int i = 0; i < hand.size(); i++) {
			card c = hand.get(i);
			c.printCard();
			System.out.print(", ");
		}
	}

}
