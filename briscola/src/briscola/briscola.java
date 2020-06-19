package briscola;

import java.util.*;

public class briscola {
	private int numP;
	private player[] pl;
	private card[] table;
	private deck myDeck;
	private ruleOfGame rule;

	/*
	 * the object briscola union all objects to get the game. pl is a vector of
	 * player and numP is the size. table is a vector of cards and i use when
	 * players select a card and put it on the table game. myDeck and gameRule are
	 * objects that i describe before.
	 */

	public briscola() {
		numP = 0;
		pl = null;
		table = null;
		myDeck = null;
		rule = null;
	}

	public briscola(int n) {
		numP = n;
		pl = new player[n + 1];
		table = new card[n + 1];
		rule = new ruleOfGame();
		myDeck = new deck();
	}

	// insert the players
	public void addPlayer() {
		for (int i = 1; i <= numP; i++) {
			System.out.print("digit the name of player " + i);
			Scanner sn = new Scanner(System.in);
			String s = sn.nextLine();
			player u = new player(s);
			pl[i] = u;
		}
	}

	// shake the deck and distribute 3 cards to each player
	public void startMatch() {
		myDeck.ShakeDeck();
		for (int i = 1; i <= numP; i++) {
			player u = pl[i];
			for (int j = 1; j <= 3; j++) {
				card c = myDeck.getCard();
				u.addCard(c);
			}
			pl[i] = u;
		}

		if (numP != 3) {
			card c = myDeck.seeBris();
			table[0] = c;
		} else {
			myDeck.deleteOne();
			card c = myDeck.seeBris();
			table[0] = c;
		}
	}

	// step 1: when turn starts each player choose a card and put on table game
	public void startTurn() {
		for (int i = 1; i <= numP; i++) {
			System.out.print("the cards in the table are: " + "\n");
			printTable();
			player u = pl[i];
			System.out.print("\n" + u.getName() + " what card you choose? " + "\n");
			u.seeHand();
			System.out.print("\n" + "digit the number of position to select the card ");
			Scanner sc = new Scanner(System.in);
			int p = sc.nextInt();
			if (p > 0 && p < 4) {
				card c = u.putDonw(p - 1);
				table[i] = c;
			}
		}
	}

	public void printTable() {
		if (table[0] != null) {
			card b = table[0];
			System.out.print("the briscola is ");
			b.printCard();
		}
		System.out.print("\n" + "players cards are: ");
		for (int i = 1; i <= numP; i++) {
			if (table[i] != null) {
				card c = table[i];
				c.printCard();
			}
		}
	}

	// step 2: define the winner that get the points of the cards in the table game
	public void winnerOfTurn() {
		int n = 0;
		rule.setVet(table);
		if (numP == 2) {
			n = rule.rulex2();
		}
		if (numP == 3) {
			n = rule.rulex3();
		}
		if (numP == 4) {
			n = rule.rulex4();
		}

		int p = rule.calcolatePoints();
		player u = pl[n];
		u.addPoints(p);
		System.out.print("the turn is win by " + u.getName() + "\n");
		if (n != 1) {
			player x = pl[1];
			pl[1] = u;
			pl[n] = x;
		} else {
			pl[1] = u;
		}
	}

	// step 3: when turn finish we clean the table game
	public void cleanTable() {
		for (int i = 1; i <= numP; i++) {
			table[i] = null;
		}
	}

	// step 4: before start new turn take a card from deck
	public void takeCard() {
		for (int i = 1; i <= numP; i++) {
			player u = pl[i];
			card c = myDeck.getCard();
			u.addCard(c);
			pl[i] = u;
		}
	}

	public boolean myContinue() {
		int z = myDeck.sizeDeck();
		return z > 0;
	}

	// when finisc the card of deck. we continue until player finish cards
	public void lastTurn() {
		card b = table[0];
		for (int i = 0; i < 3; i++) {
			table[0] = null;
			startTurn();
			table[0] = b;
			winnerOfTurn();
			cleanTable();
		}
	}

	public void theWinner() {
		int w = 0;
		int prep = 0;
		for (int i = 1; i <= numP; i++) {
			player u = pl[i];
			int p = u.getPoints();
			if (p > prep) {
				w = i;
			}
		}
		player a = pl[w];
		System.out.print("the winner is " + a.getName() + " with " + a.getPoints() + " points");
	}

	public static void main(String[] arg) {
		System.out.print("Welcon to Briscola game!" + "\n");
		System.out.print("digit the number  of players. ( the game use to 2, 3 or 4 players)");

		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		briscola b = new briscola(n);
		b.addPlayer();
		b.startMatch();
		while (b.myContinue()) {
			b.startTurn();
			b.winnerOfTurn();
			b.takeCard();
			b.cleanTable();
		}
		b.lastTurn();
		b.theWinner();

	}

}
