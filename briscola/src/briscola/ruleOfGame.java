package briscola;

import java.util.*;

public class ruleOfGame {
	private card[] vet;
	private int l;

	/*
	 * in this class i implement the rules of briscola game. in the game we can have
	 * 2, 3 or 4 players. each card has a specific value. The object ruleOfGame i
	 * use to determinate who win in each turn and calculate the points that he
	 * gets.
	 */

	public ruleOfGame() {
		vet = null;
		l = 0;
	}

	public ruleOfGame(card[] v) {
		vet = v;
		l = v.length - 1;
	}

	public void setVet(card[] tb) {
		vet = tb;
		l = tb.length - 1;
	}

	// calculate the sum of points in a set of card
	public int calcolatePoints() {
		int p = 0;
		for (int i = 1; i <= l; i++) {
			card c = vet[i];
			int val = c.getNum();
			if (val == 1) {
				p = p + 11;
			}
			if (val == 3) {
				p = p + 10;
			}
			if (val == 8) {
				p = p + 2;
			}
			if (val == 9) {
				p = p + 3;
			}
			if (val == 10) {
				p = p + 4;
			}
		}
		return p;
	}

	// implement the rules for 2 player and get the winner
	public int rulex2() {
		card bris = vet[0];
		String sb = bris.getSeed();
		card c1 = vet[1];
		String s1 = c1.getSeed();
		int n1 = c1.getNum();
		card c2 = vet[2];
		String s2 = c2.getSeed();
		int n2 = c2.getNum();
		// entrambi hanno la briscola
		if (s1.equals(sb) && s2.equals(sb)) {
			if (n1 == 1) {
				return 1;
			}
			if (n2 == 1) {
				return 2;
			}
			if (n1 == 3) {
				return 1;
			}
			if (n2 == 3) {
				return 2;
			}
			if (n1 > n2) {
				return 1;
			} else {
				return 2;
			}
		}
		// se c1 ha la briscola
		if (s1.equals(sb) && !s2.equals(sb)) {
			return 1;
		}
		// se c2 ha la briscola
		if (!s1.equals(sb) && s2.equals(sb)) {
			return 2;
		}
		// se nessuno ha la briscola
		if (!s1.equals(s2)) {
			return 1;
		}
		if (n1 == 1) {
			return 1;
		}
		if (n2 == 1 && s2.equals(s1)) {
			return 2;
		}
		if (n1 == 3) {
			return 1;
		}
		if (n2 == 3 && s2.equals(s1)) {
			return 2;
		}
		if (n1 > n2 && s1.equals(s2)) {
			return 1;
		} else {
			return 2;
		}
	}

	// implement the rules for 3 player and get the winner
	public int rulex3() {
		card bris = vet[0];
		String sb = bris.getSeed();
		card c1 = vet[1];
		String s1 = c1.getSeed();
		int n1 = c1.getNum();
		card c2 = vet[2];
		String s2 = c2.getSeed();
		int n2 = c2.getNum();
		card c3 = vet[3];
		String s3 = c3.getSeed();
		int n3 = c3.getNum();
		// se tutti hanno una briscola
		if (s1.equals(sb) && s2.equals(sb) && s3.equals(sb)) {
			// chi ha l'asso
			if (n1 == 1) {
				return 1;
			}
			if (n2 == 1) {
				return 2;
			}
			if (n3 == 1) {
				return 3;
			}
			// chi ha il tre
			if (n1 == 3) {
				return 1;
			}
			if (n2 == 3) {
				return 2;
			}
			if (n3 == 3) {
				return 3;
			}
			// altrimenti
			if (n1 > n2 && n1 > n3) {
				return 1;
			}
			if (n2 > n1 && n2 > n3) {
				return 2;
			}
			if (n3 > n1 && n3 > n2) {
				return 3;
			}
		}
		// se uno dei tre ha la briscola
		if (s1.equals(sb) && !s2.equals(sb) && !s3.equals(sb)) {
			return 1;
		}
		if (!s1.equals(sb) && s2.equals(sb) && !s3.equals(sb)) {
			return 2;
		}
		if (!s1.equals(sb) && !s2.equals(sb) && s3.equals(sb)) {
			return 3;
		}
		// se due hanno la briscola
		int y = 0;
		if (s1.equals(sb) && s2.equals(sb) && !s3.equals(sb)) {
			card[] tb2 = new card[3];
			tb2[0] = bris;
			tb2[1] = c1;
			tb2[2] = c2;
			ruleOfGame r2 = new ruleOfGame(tb2);
			y = r2.rulex2();
			return y;
		}

		if (s1.equals(sb) && s3.equals(sb) && !s2.equals(sb)) {
			card[] tb2 = new card[3];
			tb2[0] = bris;
			tb2[1] = c1;
			tb2[2] = c3;
			ruleOfGame r2 = new ruleOfGame(tb2);
			y = r2.rulex2();
			if (y == 2) {
				return 3;
			} else {
				return 1;
			}
		}

		if (s2.equals(sb) && s3.equals(sb) && !s1.equals(sb)) {
			card[] tb2 = new card[3];
			tb2[0] = bris;
			tb2[1] = c2;
			tb2[2] = c3;
			ruleOfGame r2 = new ruleOfGame(tb2);
			y = r2.rulex2();
			return y + 1;
		}
		// se nessuno ha la briscola
		// se tutti e tre hanno lo stesso seme
		if (s1.equals(s2) && s1.equals(s3)) {
			if (n1 == 1) {
				return 1;
			}
			if (n2 == 1) {
				return 2;
			}
			if (n3 == 1) {
				return 3;
			}
			if (n1 == 3 && n2 != 1 && n3 != 1) {
				return 1;
			}
			if (n2 == 3 && n1 != 1 && n3 != 1) {
				return 2;
			}
			if (n3 == 3 && n1 != 1 && n2 != 1) {
				return 3;
			}
			if (n1 > n2 && n1 > n3) {
				return 1;
			}
			if (n2 > n1 && n2 > n3) {
				return 2;
			}
			if (n3 > n1 && n3 > n2) {
				return 3;
			}
		}
		int z = 0;
		// se c1 e c2 hanno lo stesso seme
		if (s1.equals(s2) && !s1.equals(s3)) {
			card[] tb2 = new card[3];
			tb2[0] = bris;
			tb2[1] = c1;
			tb2[2] = c2;
			ruleOfGame r2 = new ruleOfGame(tb2);
			z = r2.rulex2();
			return z;
		}
		// se c1 e c3 hanno lo stesso seme
		if (s1.equals(s3) && !s1.equals(s2)) {
			card[] tb2 = new card[3];
			tb2[0] = bris;
			tb2[1] = c1;
			tb2[2] = c3;
			ruleOfGame r2 = new ruleOfGame(tb2);
			z = r2.rulex2();
			if (z == 1) {
				return 1;
			} else {
				return 3;
			}
		} else {
			return 1;
		}
	}

	// implement the rules for 4 player and get the winner
	public int rulex4() {
		card bris = vet[0];
		String sb = bris.getSeed();
		card c1 = vet[1];
		String s1 = c1.getSeed();
		int n1 = c1.getNum();
		card c2 = vet[2];
		String s2 = c2.getSeed();
		int n2 = c2.getNum();
		card c3 = vet[3];
		String s3 = c3.getSeed();
		int n3 = c3.getNum();
		card c4 = vet[4];
		String s4 = c4.getSeed();
		int n4 = c4.getNum();
		// se tutti hanno la briscola
		if (s1.equals(sb) && s2.equals(sb) && s3.equals(sb) && s4.equals

		(sb)) {
			// chi ha l'asso
			if (n1 == 1) {
				return 1;
			}
			if (n2 == 1) {
				return 2;
			}
			if (n3 == 1) {
				return 3;
			}
			if (n4 == 1) {
				return 4;
			}
			// chi ha il 3
			if (n1 == 3) {
				return 1;
			}
			if (n2 == 3) {
				return 2;
			}
			if (n3 == 3) {
				return 3;
			}
			if (n4 == 3) {
				return 4;
			}
			// altrimenti
			if (n1 > n2 && n1 > n3 && n1 > n4) {
				return 1;
			}
			if (n2 > n1 && n2 > n3 && n2 > n4) {
				return 2;
			}
			if (n3 > n1 && n3 > n2 && n3 > n4) {
				return 3;
			}
			if (n4 > n1 && n4 > n2 && n4 > n3) {
				return 4;
			}
		}
		// se uno ha la priscola
		if (s1.equals(sb) && !s2.equals(sb) && !s3.equals(sb) && s4.equals(sb)) {

			return 1;
		}
		if (s2.equals(sb) && !s1.equals(sb) && !s3.equals(sb) && s4.equals(sb)) {

			return 2;
		}
		if (s3.equals(sb) && !s1.equals(sb) && !s2.equals(sb) && s4.equals(sb))

		{
			return 3;
		}
		if (s4.equals(sb) && !s1.equals(sb) && !s2.equals(sb) && !s3.equals(sb)

		) {
			return 4;
		}
		// se 2 hanno la priscola
		int y = 0;
		if (s1.equals(sb) && s2.equals(sb) && !s3.equals(sb) && !s4.equals(sb)) {
			card[] tb = new card[3];
			tb[0] = bris;
			tb[1] = c1;
			tb[2] = c2;
			ruleOfGame r2 = new ruleOfGame(tb);
			y = r2.rulex2();
			return y;
		}

		if (s1.equals(sb) && s3.equals(sb) && !s2.equals(sb) && !s4.equals(sb)) {
			card[] tb = new card[3];
			tb[0] = bris;
			tb[1] = c1;
			tb[2] = c3;
			ruleOfGame r2 = new ruleOfGame(tb);
			y = r2.rulex2();
			if (y == 2) {
				return 3;
			} else {
				return 1;
			}
		}

		if (s1.equals(sb) && s4.equals(sb) && !s2.equals(sb) && !s3.equals(sb)) {
			card[] tb = new card[3];
			tb[0] = bris;
			tb[1] = c1;
			tb[2] = c4;
			ruleOfGame r2 = new ruleOfGame(tb);
			y = r2.rulex2();
			if (y == 2) {
				return 4;
			} else {
				return 1;
			}
		}

		if (s2.equals(sb) && s3.equals(sb) && !s1.equals(sb) && !s4.equals(sb)) {
			card[] tb = new card[3];
			tb[0] = bris;
			tb[1] = c2;
			tb[2] = c3;
			ruleOfGame r2 = new ruleOfGame(tb);
			y = r2.rulex2();
			return y + 1;
		}

		if (s2.equals(sb) && s4.equals(sb) && !s1.equals(sb) && !s3.equals(sb)) {
			card[] tb = new card[3];
			tb[0] = bris;
			tb[1] = c2;
			tb[2] = c4;
			ruleOfGame r2 = new ruleOfGame(tb);
			y = r2.rulex2();
			return y * 2;
		}

		if (s3.equals(sb) && s4.equals(sb) && !s1.equals(sb) && !s2.equals(sb)) {
			card[] tb = new card[3];
			tb[0] = bris;
			tb[1] = c3;
			tb[2] = c4;
			ruleOfGame r2 = new ruleOfGame(tb);
			y = r2.rulex2();
			return y + 2;
		}

		// se tre hanno la briscola
		int z = 0;
		if (s1.equals(sb) && s2.equals(sb) && s3.equals(sb) && !s4.equals(sb)) {
			card[] tv = new card[4];
			tv[0] = bris;
			tv[1] = c1;
			tv[2] = c2;
			tv[3] = c3;
			ruleOfGame r3 = new ruleOfGame(tv);
			z = r3.rulex3();
			return z;
		}

		if (s1.equals(sb) && s2.equals(sb) && s4.equals(sb) && !s3.equals(sb)) {
			card[] tv = new card[4];
			tv[0] = bris;
			tv[1] = c1;
			tv[2] = c2;
			tv[3] = c4;
			ruleOfGame r3 = new ruleOfGame(tv);
			z = r3.rulex3();
			if (z == 3) {
				return 4;
			} else {
				return z;
			}
		}

		if (s2.equals(sb) && s3.equals(sb) && s4.equals(sb) && !s1.equals(sb)) {
			card[] tv = new card[4];
			tv[0] = bris;
			tv[1] = c2;
			tv[2] = c3;
			tv[3] = c4;
			ruleOfGame r3 = new ruleOfGame(tv);
			z = r3.rulex3();
			return z + 1;
		}

		if (s1.equals(sb) && s3.equals(sb) && s4.equals(sb) && !s2.equals(sb)) {
			card[] tv = new card[4];
			tv[0] = bris;
			tv[1] = c1;
			tv[2] = c4;
			tv[3] = c3;
			ruleOfGame r3 = new ruleOfGame(tv);
			z = r3.rulex3();
			if (z == 2) {
				return 4;
			} else {
				return z;
			}
		}

		// se nessuno ha la briscola
		// tutti hanno lo stesso seme
		if (s1.equals(s2) && s2.equals(s3) && s3.equals(s4)) {
			// chi ha l'asso
			if (n1 == 1) {
				return 1;
			}
			if (n2 == 1) {
				return 2;
			}
			if (n3 == 1) {
				return 3;
			}
			if (n4 == 1) {
				return 4;
			}
			// chi ha il 3
			if (n1 == 3) {
				return 1;
			}
			if (n2 == 3) {
				return 2;
			}
			if (n3 == 3) {
				return 3;
			}
			if (n4 == 3) {
				return 4;
			}
			// altrimenti
			if (n1 > n2 && n1 > n3 && n1 > n4) {
				return 1;
			}
			if (n2 > n1 && n2 > n3 && n2 > n4) {
				return 2;
			}
			if (n3 > n1 && n3 > n2 && n3 > n4) {
				return 3;
			}
			if (n4 > n1 && n4 > n2 && n4 > n3) {
				return 4;
			}
		}
		// se c1 e altri due hanno lo stesso seme
		int x = 0;
		if (s1.equals(s2) && s2.equals(s3) && !s1.equals(s4)) {
			card[] td = new card[4];
			td[0] = bris;
			td[1] = c1;
			td[2] = c2;
			td[3] = c3;
			ruleOfGame r3 = new ruleOfGame(td);
			x = r3.rulex3();
			return x;
		}

		if (s1.equals(s2) && s2.equals(s4) && !s1.equals(s3)) {
			card[] td = new card[4];
			td[0] = bris;
			td[1] = c1;
			td[2] = c2;
			td[3] = c4;
			ruleOfGame r3 = new ruleOfGame(td);
			x = r3.rulex3();
			if (x == 3) {
				return 4;
			} else {
				return x;
			}
		}

		if (s1.equals(s3) && s3.equals(s4) && !s1.equals(s2)) {
			card[] td = new card[4];
			td[0] = bris;
			td[1] = c1;
			td[2] = c4;
			td[3] = c3;
			ruleOfGame r3 = new ruleOfGame(td);
			x = r3.rulex3();
			if (x == 2) {
				return 4;
			} else {
				return x;
			}
		}

		// se c1 e un altro hanno lo stesso seme
		int k = 0;
		if (s1.equals(s2)) {
			card[] tc = new card[3];
			tc[0] = bris;
			tc[1] = c1;
			tc[2] = c2;
			ruleOfGame r2 = new ruleOfGame(tc);
			k = r2.rulex2();
			return k;
		}

		if (s1.equals(s3)) {
			card[] tc = new card[3];
			tc[0] = bris;
			tc[1] = c1;
			tc[2] = c3;
			ruleOfGame r2 = new ruleOfGame(tc);
			k = r2.rulex2();
			if (k == 2) {
				return 3;
			} else {
				return k;
			}
		}

		if (s1.equals(s4)) {
			card[] tc = new card[3];
			tc[0] = bris;
			tc[1] = c1;
			tc[2] = c4;
			ruleOfGame r2 = new ruleOfGame(tc);
			k = r2.rulex2();
			if (k == 2) {
				return 4;
			} else {
				return k;
			}
		} else {
			return 1;
		}
	}

}
