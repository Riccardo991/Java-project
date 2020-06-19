package minefield;

import java.util.*;

class game {

	/*
	 * in this class there are a methods use by user during the minefield match. .
	 * CooseSquare: user select a cell in the game-space live: verify if user select
	 * a good cell putFlag: user select a flag in the game-space removeFlag: user
	 * delete a flag in the game-space myContinue: a method that control when the
	 * match finish
	 */

	public static void cooseSquare(space z, int r, int c) {
		elem e = z.getElem(r, c);
		String w = e.getSee();
		if (w.equals("-") || w.equals("f")) {
			int d = e.getDanger();
			char a = (char) (char) (48 + d);
			String s = String.valueOf(a);
			e.setSee(s);
			int nsc = z.getNumSeeCell();
			z.setNumSeeCell((nsc - 1));
			z.setElem(e, r, c);
			z.turnOnCell(r, c);
		}
	}

	public static boolean live(space z, int r, int c) {
		elem e = z.getElem(r, c);
		return e.getBomb() == false;
	}

	public static void putFlag(space z, int r, int c) {
		elem e = z.getElem(r, c);
		if (e.getSee() == "-") {
			e.setSee("f");
		}
	}

	public static void removeFlag(space z, int r, int c) {
		elem e = z.getElem(r, c);
		if (e.getSee() == "f") {
			e.setSee("-");
		}
	}

	public static boolean myContinue(space z) {
		int nb = z.getNumBomb();
		int nsc = z.getNumSeeCell();
		return nsc > nb;
	}

	public static void main(String[] arg) {
		System.out.print("Welcom to Minefield game! " + "\n"
				+ "if you want to play insert the dimension of gamespace and the number of bomb" + "\n");
		System.out.print(" digit number of rows " + "\n");
		int dr = 0;
		Scanner sdr = new Scanner(System.in);
		dr = sdr.nextInt();
		System.out.print("digit the number of colons " + "\n");
		int dc = 0;
		Scanner sdc = new Scanner(System.in);
		dc = sdc.nextInt();
		System.out.print("digit the number of bombs ");
		int b = 0;
		Scanner sb = new Scanner(System.in);
		b = sb.nextInt();

		space z = new space(dr, dc);
		z.putBomb(b);
		z.calcolateDanger();
		System.out.print("\n" + "if you digit 1, you   play" + "\n" + " if you digit 2 you  put a flag" + "\n"
				+ "if you digit 3 you remove a flag" + "\n" + "if you digit 4 you stop the game");

		int k = 0;
		int e = 0;
		while (myContinue(z)) {
			z.printSpace();
			System.out.print("digit numper to select what you want to do");
			Scanner sk = new Scanner(System.in);
			k = sk.nextInt();
			switch (k) {
			case 1:
				System.out.print("insert the coordinate of cell");
				System.out.print(" row:");
				Scanner sr1 = new Scanner(System.in);
				int r1 = sr1.nextInt();
				System.out.print("colon:");
				Scanner sc1 = new Scanner(System.in);
				int c1 = sc1.nextInt();
				if (live(z, r1, c1)) {
					cooseSquare(z, r1, c1);
				} else {
					e = 1;
				}
				break;
			case 2:
				System.out.print("digit the coordinate for put a flag");
				System.out.print(" row:");
				Scanner sr2 = new Scanner(System.in);
				int r2 = sr2.nextInt();
				System.out.print("colon:");
				Scanner sc2 = new Scanner(System.in);
				int c2 = sc2.nextInt();
				putFlag(z, r2, c2);
				break;
			case 3:
				System.out.print("digit the coordinate  where you want remove the  flag");
				System.out.print(" row:");
				Scanner sr3 = new Scanner(System.in);
				int r3 = sr3.nextInt();
				System.out.print("colon:");
				Scanner sc3 = new Scanner(System.in);
				int c3 = sc3.nextInt();
				removeFlag(z, r3, c3);
				break;
			}
			if (k == 4 || e == 1) {
				System.out.print(" game over!");
				break;
			}
		}

		if (myContinue(z) == false) {
			System.out.print(" WIN!! ");
		}

	}

}
