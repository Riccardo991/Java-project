package blackJack;

import java.util.*;

public class gameRules {
	private ArrayList<card> Croupier;
	private ArrayList<card> Player;

	// gameRules is a object use by implement the rules of blackjack.
	public gameRules() {
		Croupier = null;
		Player = null;
	}

	public gameRules(ArrayList<card> c, ArrayList<card> p) {
		Croupier = c;
		Player = p;
	}

	public void setCroupier(ArrayList<card> l) {
		Croupier = l;
	}

	public void setPlayer(ArrayList<card> l) {
		Player = l;
	}

	// get the value of set of cards
	public static int cardsValue(ArrayList<card> l) {
		int p = 0;
		boolean ace = false;
		for (int i = 0; i < l.size(); i++) {
			card c = l.get(i);
			int x = c.getNum();
			if (x >= 2 && x < 11) {
				p = p + x;
			}
			if (x >= 11) {
				p = p + 10;
			}
			if (x == 1) {
				p = p + 11;
				ace = true;
			}
		}

		if (ace && p > 21) {
			return (p - 10);
		} else {
			return p;
		}
	}

	// decide who win: player or Croupier
	public int isWinner() {
		int p1 = cardsValue(Croupier);
		int p2 = cardsValue(Player);

		if (p1 == 21 && p2 == 21) {
			return isBlackJack();
		}
		if (p2 <= 21 && p2 > p1) {
			return -1;
		}
		if (p1 <= 21 && p1 > p2) {
			return 1;
		}
		if (p1 == p2 && p1 <= 21) {
			return 0;
		}
		if (p1 > 21 && p2 > 21) {
			return 0;
		}
		if (p1 > 21 && p2 <= 21) {
			return -1;
		}
		if (p1 <= 21 && p2 > 21) {
			return 1;
		} else {
			return 0;
		}
	}

	// if both have blackjack
	// Croupier wins if he has jack and a ace, else player wins
	public int isBlackJack() {
		int x = 0;
		for (int i = 0; i < Croupier.size(); i++) {
			card c = Croupier.get(i);
			int n = c.getNum();
			if (n == 1 || n == 11) {
				x++;
			}
		}
		if (x == 2) {
			return 1;
		} else {
			return -1;
		}
	}

}
