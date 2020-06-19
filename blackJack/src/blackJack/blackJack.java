package blackJack;

import java.util.*;

public class blackJack {
	private deck gameDeck;
	private player[] gentleman;
	private int np;
	private long[] bet;
	private gameRules rule;

	/*
	 * the object blackjack union all object to get a blackjack game. gentleman is a
	 * vector of players,the first is the Croupier and np is the number of players.
	 * the vector bet represents the table where the players bet their cash. rule:
	 * is the object that determinate who win. gamedeck is the set of cards
	 */

	public blackJack() {
		gameDeck = null;
		gentleman = null;
		np = 0;
		bet = null;
		rule = null;
	}

	public blackJack(int n, int j) {
		gameDeck = new deck(n);
		np = j + 1;
		player p = new player("croupier", 1000);
		gentleman = new player[np];
		gentleman[0] = p;
		bet = new long[np];
		bet[0] = 1000;
		rule = null;
	}

	// insert the players and shake cards
	public void startGame() {
		gameDeck.ShakeDeck();
		for (int i = 1; i < np; i++) {
			System.out.print(
					"welcom to black jack game! " + "\n" + "insert name and with how  many money you want start.");
			System.out.print("\n" + "digit name");
			Scanner sc = new Scanner(System.in);
			String s = sc.nextLine();
			System.out.print("\n" + "digint number of money");
			Scanner sk = new Scanner(System.in);
			long l = sk.nextLong();
			player p = new player(s, l);
			gentleman[i] = p;
		}
	}

	// deal 2 cards to each player
	public void startMatch() {
		for (int i = 0; i < np; i++) {
			if (gentleman[i] != null) {
				ArrayList<card> l = gameDeck.get2Cards();
				player p = gentleman[i];
				p.setCards(l);
				gentleman[i] = p;
			}
		}
	}

	public void printTable() {
		player c = gentleman[0];
		c.printCroupier();
		System.out.print("\n");
		for (int i = 1; i < np; i++) {
			if (gentleman[i] != null) {
				player p = gentleman[i];
				p.printPlayer();
				System.out.print("\n");
			}
		}
	}

	// player decides if bet or leave
	public void firstAction() {
		player c = gentleman[0];
		for (int i = 1; i < np; i++) {
			if (gentleman[i] != null) {
				player p = gentleman[i];
				p.printPlayer();
				long lp = p.getCash();
				System.out.print("\n" + "your cash is: " + lp + "\n");
				c.printCroupier();
				System.out.print("\n"
						+ "if  you want leave digit 0, or if you want  play digit  the number of money  taht you  want  bet");
				while (true) {
					Scanner sb = new Scanner(System.in);
					long l = sb.nextLong();
					if (p.lessCash(l)) {
						bet[i] = l;
						break;
					} else {
						System.out.print(" error, you have less money, digit again");
					}
				}
				gentleman[i] = p;
			}
		}
	}

	// player can get more cards
	public void moreCards() {
		player c = gentleman[0];
		ArrayList<card> cl = c.getCards();
		int v = rule.cardsValue(cl);
		while (v <= 14) {
			card u = gameDeck.getOneCard();
			cl.add(u);
			v = rule.cardsValue(cl);
		}
		int z = cl.size();
		c.setCards(cl);
		gentleman[0] = c;

		for (int i = 1; i < np; i++) {
			if (bet[i] != 0) {
				player p = gentleman[i];
				System.out.print("the croupier has " + z + " cards \n");
				p.printPlayer();
				System.out.print("\n" + "if you want more cards digit 1, else digit 0");
				while (true) {
					Scanner sc = new Scanner(System.in);
					int x = sc.nextInt();
					if (x == 0) {
						break;
					} else {
						card c1 = gameDeck.getOneCard();
						p.addCard(c1);
						p.printPlayer();
					}
				}
				gentleman[i] = p;
			}
		}
	}

	// player can bet again
	public void moreBet() {
		player c = gentleman[0];
		for (int i = 1; i < np; i++) {
			if (bet[i] != 0) {
				player p = gentleman[i];
				p.printPlayer();
				long n = p.getCash();
				System.out.print("\n" + "you bet is: " + bet[i] + "and you have " + n + "money" + "\n");
				c.printCroupier();
				System.out.print("\n" + "if you want bet again  digit the value of noney, else digit 0");
				while (true) {
					Scanner sb = new Scanner(System.in);
					long l = sb.nextLong();
					if (l == 0) {
						break;
					}
					if (p.lessCash(l)) {
						bet[i] = bet[i] + l;
						break;
					} else {
						System.out.print("error, you have less money, digit again");
					}
				}
				gentleman[i] = p;
			}
		}
	}

	// decide who win
	public void winnerMatch() {
		player c = gentleman[0];
		ArrayList<card> cl = c.getCards();
		for (int i = 1; i < np; i++) {
			if (bet[i] != 0) {
				player p = gentleman[i];
				ArrayList<card> pl = p.getCards();
				rule = new gameRules(cl, pl);
				c.printPlayer();
				System.out.print("\n");
				p.printPlayer();
				System.out.print("\n");

				if (rule.isWinner() == 1) {
					c.addCash(bet[i]);
					bet[i] = 0;
					System.out.print(p.getName() + " lose \n");
				} else if (rule.isWinner() == -1) {
					long x = bet[i] * 2;
					System.out.print(p.getName() + " win, and take " + x + " money \n");
					if (c.lessCash(x)) {
						p.addCash(x);
						bet[i] = 0;
					} else {
						c.addCash(x * 10);
						p.addCash(x);
						bet[i] = 0;
					}
				} else {
					System.out.print("the value of cards is even, so you receive only " + bet[i] + " money \n");
					p.addCash(bet[i]);
					bet[i] = 0;
				}
				gentleman[i] = p;
			}
		}
	}

	public void endMatch() {
		for (int i = 0; i < np; i++) {
			if (gentleman[i] != null) {
				player p = gentleman[i];
				p.setCards(null);
				gentleman[i] = p;
			}
		}
	}

	// player can leave match
	public void doContinue() {
		for (int i = 1; i < np; i++) {
			player p = gentleman[i];
			String s = p.getName();
			long l = p.getCash();
			System.out.print("player " + s + "your total cash is " + l + "\n" + "do you want continue? " + "\n"
					+ "digit 1 for yes or 0 for no, or 2 if you want add money to play");
			Scanner sn = new Scanner(System.in);
			int n = sn.nextInt();
			if (n == 0) {
				gentleman[i] = null;
				bet[i] = 0;
			}
			if (n == 2) {
				System.out.print("how many money do you want add ? " + "\n" + "digit number ");
				Scanner sl = new Scanner(System.in);
				long k = sl.nextLong();
				p.addCash(k);
				gentleman[i] = p;
			}
		}
	}

	public boolean stopGame() {
		int x = 0;
		for (int i = 1; i < np; i++) {
			if (gentleman[i] != null) {
				x++;
			}
		}
		return (x != 0 && gameDeck.getSize() >= (x + 1) * 2);
	}

	public static void main(String[] arg) {
		System.out.print("welcon  on Trixy casino " + "+\n"
				+ " if you want play black jak  insert the number of  players and the   with how many card'deck do you want play"
				+ "\n" + "digit number of players");
		Scanner sc = new Scanner(System.in);
		int j = sc.nextInt();
		System.out.print("\n" + "digit the number of decks ");
		int n = sc.nextInt();

		blackJack bj = new blackJack(n, j);
		bj.startGame();
		while (bj.stopGame()) {
			bj.startMatch();
			bj.printTable();
			bj.firstAction();
			bj.moreCards();
			bj.moreBet();
			bj.winnerMatch();
			bj.endMatch();
			bj.doContinue();
		}

		if (bj.gameDeck.getSize() <= (j + 1) * 2) {
			System.out.print(" we finish the game deck");
		} else {
			System.out.print("game other");
			;
		}

	}

}
