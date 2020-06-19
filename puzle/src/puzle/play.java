package puzle;

import java.util.*;

public class play {

	/*
	 * in this class there are the methods that define the user's actions. seeFirst
	 * : user takes a piece nextPiece: user changes piece. insertPiece : user puts
	 * the piece in the puzzle. rotatePiece: user rotate the current piece.
	 */

	public static piece seeFirst(puzzle p) {
		ArrayList<piece> l = p.getPieces();
		int i = p.getNum();
		piece u = l.get(i);
		p.setNum(i + 1);
		return u;
	}

	public static piece nextPiece(puzzle p) {
		ArrayList<piece> l = p.getPieces();
		int n = p.getNum();
		if (n < l.size()) {
			piece u = l.get(n);
			p.setNum(n + 1);
			return u;
		} else {
			piece u = l.get(0);
			p.setNum(1);
			return u;
		}
	}

	public static void insertPiece(puzzle p, piece u, int r, int c) {
		if (p.isGood(u, r, c)) {
			System.out.print("good, the piece is in the right position. ");
			p.printPuzle();
		} else {
			System.out.print("error, the piece is in  a wrong position. ");
		}
	}

	public static piece rotatePiece(piece p, int n) {
		if (n == 1) {
			border b = p.getMyborder();
			int u = b.getTop();
			b.setTop(b.getLeft());
			b.setLeft(b.getDonw());
			b.setDonw(b.getRight());
			b.setRight(u);
			p.setSeeborder(b);
		}
		if (n == 2) {
			border b = p.getMyborder();
			int u = b.getTop();
			b.setTop(b.getDonw());
			b.setDonw(u);
			int v = b.getLeft();
			b.setLeft(b.getRight());
			b.setRight(v);
			p.setSeeborder(b);
		}
		if (n == 3) {
			border b = p.getMyborder();
			int u = b.getTop();
			b.setTop(b.getRight());
			b.setRight(b.getDonw());
			b.setDonw(b.getLeft());
			b.setLeft(u);
			p.setSeeborder(b);
		}
		return p;
	}

	public static void main(String[] arg) {
		System.out.print("welcom to puzzle game! " + "\n" + "insert the dimension of puzzle: " + "\n");
		System.out.print("digit number of rows " + "\n");
		Scanner s = new Scanner(System.in);
		int dr = s.nextInt();
		System.out.print("digit the number of colons ");
		int dc = s.nextInt();

		puzzle p = new puzzle(dr, dc);
		p.preparePuzzle();
		p.originalPicture();
		String sp = p.getPicture();
		System.out.print("the original picture is " + "\n" + sp);
		p.shakePieces();

		piece currentPiece = seeFirst(p);
		int k = 0;
		do {
			System.out.print("digit 1 to  coose next piece " + "\n" + "digit 2 to insert the piece in the puzzle "
					+ "\n" + "digit 3 to ask the master's  help for curent piece  " + "\n"
					+ "digit 4  to ask master's help for complete all puzle " + "\n"
					+ "digit  5 to   complete automatically the puzzle " + "\n" + "digit 6 to see original picture "
					+ "\n" + "digit 7 to rotate the current piece " + "\n" + "digit 8 to end game");
			Scanner sk = new Scanner(System.in);
			k = sk.nextInt();

			switch (k) {
			case 1:
				currentPiece = nextPiece(p);
				System.out.print(" the new piece is: ");
				currentPiece.printPiece();
				break;
			case 2:
				System.out.print("insert the coordinate where you want put the piece " + "\n" + "digit row: ");
				Scanner x = new Scanner(System.in);
				int r = x.nextInt();
				System.out.print("digit colon");
				int c = x.nextInt();
				insertPiece(p, currentPiece, r, c);
				p.printBuildPuzle();
				currentPiece = nextPiece(p);
				currentPiece.printPiece();
				break;
			case 3:
				p.masterRescyou(currentPiece);
				p.printBuildPuzle();
				currentPiece = nextPiece(p);
				currentPiece.printPiece();
				break;
			case 4:
				p.doPuzzle();
				break;
			case 5:
				p.doAutomatically();
				break;
			case 6:
				System.out.print(sp);
				break;
			case 7:
				System.out.print(" digit 1 to rotate 90° the piece " + "\n" + "digit 2 to rotate of 180° the piece "
						+ "\n" + "digit 3 to rotate 270° the piece ");
				Scanner as = new Scanner(System.in);
				int a = as.nextInt();
				currentPiece = rotatePiece(currentPiece, a);
				currentPiece.printPiece();
				break;
			}
		} while (k < 8);

	}

}
