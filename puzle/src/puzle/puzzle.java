package puzle;

import java.util.*;

public class puzzle {
	private ArrayList<piece> pieces;
	private String picture;
	private piece[][] space;
	private int dimRow;
	private int dimColon;
	private int num;

	/*
	 * the object puzzle define the matrix of pieces. The matrix of pieces space is
	 * the game-space and dimRow and dimColon are its dimension. The string picture
	 * is the representation of the picture of puzzle. the list pieces contains the
	 * pieces that user has not yet put in the matrix. num is a index of the list
	 * pieces.
	 */

	public puzzle() {
		pieces = new ArrayList<piece>();
		space = new piece[1][1];
		picture = "";
		dimRow = 0;
		dimColon = 0;
		num = 0;
	}

	public puzzle(int r, int c) {
		pieces = new ArrayList<piece>();
		picture = "";
		dimRow = r;
		dimColon = c;
		num = 0;
		space = new piece[r + 1][c + 1];
		space[0][0] = new piece(0, 0, "/");
		for (int i = 1; i <= dimColon; i++) {
			if (i < 10) {
				char a = (char) (char) (48 + i);
				String s = String.valueOf(a);
				piece p = new piece(0, i, s);
				space[0][i] = p;
			} else {
				int m = 48 + (i / 10);
				int q = 48 + (i % 10);
				char c1 = (char) (char) m;
				char c2 = (char) (char) q;
				String s1 = String.valueOf(c1);
				String s2 = String.valueOf(c2);
				String s3 = s1 + s2;
				piece p = new piece(0, i, s3);
				space[0][i] = p;
			}
		}
		for (int i = 1; i <= dimRow; i++) {
			if (i < 10) {
				char a = (char) (char) (48 + i);
				String s = String.valueOf(a);
				piece p = new piece(i, 0, s);
				space[i][0] = p;
			} else {
				int m = 48 + (i / 10);
				int q = 48 + (i % 10);
				char c1 = (char) (char) m;
				char c2 = (char) (char) q;
				String s1 = String.valueOf(c1);
				String s2 = String.valueOf(c2);
				String s3 = s1 + s2;
				piece p = new piece(i, 0, s3);
				space[i][0] = p;
			}
		}
		Random rand = new Random();
		for (int i = 1; i <= c; i++) {
			for (int j = 1; j <= r; j++) {
				int a = rand.nextInt(25) + 97;
				int b = rand.nextInt(25) + 97;
				char a1 = (char) (char) a;
				char b1 = (char) (char) b;
				String a2 = String.valueOf(a1);
				String b2 = String.valueOf(b1);
				String s = a2 + b2;
				piece p = new piece(i, j, s);
				space[i][j] = p;
			}
		}
	}

	// define the value of border for each pieces
	public void preparePuzzle() {
		for (int i = 1; i <= dimColon; i++) {
			piece p = space[1][i];
			border b = p.getMyborder();
			b.setTop(0);
			p.setMyborder(b);
			space[1][i] = p;
		}
		for (int i = 1; i <= dimColon; i++) {
			piece p = space[dimRow][i];
			border b = p.getMyborder();
			b.setDonw(0);
			p.setMyborder(b);
			space[dimRow][i] = p;
		}
		for (int i = 1; i <= dimRow; i++) {
			piece p = space[i][1];
			border b = p.getMyborder();
			b.setLeft(0);
			p.setMyborder(b);
			space[i][1] = p;
		}
		for (int i = 1; i <= dimRow; i++) {
			piece p = space[i][dimColon];
			border b = p.getMyborder();
			b.setRight(0);
			p.setMyborder(b);
			space[i][dimColon] = p;
		}
		Random rand = new Random();
		for (int i = 1; i < dimColon; i++) {
			for (int j = 1; j <= dimRow; j++) {
				piece p1 = space[i][j];
				piece p2 = space[i + 1][j];
				int v1 = rand.nextInt(8) + 1;
				border b1 = p1.getMyborder();
				border b2 = p2.getMyborder();
				b1.setRight(v1);
				int v2 = 0 - v1;
				b2.setLeft(v2);
				p1.setMyborder(b1);
				p2.setMyborder(b2);
				space[i][j] = p1;
				space[i + 1][j] = p2;
			}
		}
		for (int i = 1; i <= dimColon; i++) {
			for (int j = 1; j < dimRow; j++) {
				piece p1 = space[i][j];
				piece p2 = space[i][j + 1];
				border b1 = p1.getMyborder();
				border b2 = p2.getMyborder();
				int v1 = rand.nextInt(8) + 1;
				b1.setDonw(v1);
				int v2 = 0 - v1;
				b2.setTop(v2);
				p1.setMyborder(b1);
				p2.setMyborder(b2);
				space[i][j] = p1;
				space[i][j + 1] = p2;
			}
		}
	}

	// get the picture of puzzle
	public void originalPicture() {
		String s = "";
		for (int i = 0; i <= dimColon; i++) {
			for (int j = 0; j <= dimRow; j++) {
				piece p = space[i][j];
				s = s + p.getSee() + ", ";
			}
			s = s + "\n";
		}
		picture = s;
	}

	// rotate randomly the piece and insert in the list and shake it.
	public void shakePieces() {
		ArrayList<piece> l = new ArrayList<piece>();
		for (int i = 1; i <= dimColon; i++) {
			for (int j = 1; j <= dimRow; j++) {
				piece p = space[i][j];
				l.add(p);
				space[i][j] = null;
			}
		}
		Random rand = new Random();
		for (int i = 0; i < l.size(); i++) {
			piece p = l.get(i);
			int r = rand.nextInt(3);
			if (r == 0) {
				border b = p.getMyborder();
				p.setSeeborder(b);
				pieces.add(p);
			}
			if (r == 1) {
				border b = p.getMyborder();
				int u = b.getTop();
				b.setTop(b.getLeft());
				b.setLeft(b.getDonw());
				b.setDonw(b.getRight());
				b.setRight(u);
				p.setSeeborder(b);
				pieces.add(p);
			}
			if (r == 2) {
				border b = p.getMyborder();
				int u = b.getTop();
				b.setTop(b.getDonw());
				b.setDonw(u);
				int v = b.getLeft();
				b.setLeft(b.getRight());
				b.setRight(v);
				p.setSeeborder(b);
				pieces.add(p);
			}
			if (r == 3) {
				border b = p.getMyborder();
				int u = b.getTop();
				b.setTop(b.getRight());
				b.setRight(b.getDonw());
				b.setDonw(b.getLeft());
				b.setLeft(u);
				p.setSeeborder(b);
				pieces.add(p);
			}
		}
		Collections.shuffle(pieces);
	}

	// the program helps user to insert t a piece in the correct position
	public void masterRescyou(piece p) {
		border b = p.getMyborder();
		p.setSeeborder(b);
		int r = p.getIdr();
		int c = p.getIdc();
		space[r][c] = p;
		delete(p);
	}

	public boolean isGood(piece p, int r, int c) {
		int y = p.getIdr();
		int x = p.getIdc();
		border b1 = p.getMyborder();
		border b2 = p.getSeeborder();
		if (r == y && c == x && b1.isEquals(b2)) {
			space[r][c] = p;
			delete(p);
			return true;
		} else {
			return false;
		}
	}

	public void delete(piece p) {
		for (int i = 0; i < pieces.size(); i++) {
			piece p1 = pieces.get(i);
			if (p1.getIdr() == p.getIdr() && p1.getIdc() == p.getIdc()) {
				pieces.remove(i);
				break;
			}
		}
	}

	// the program simulates the user an complete the puzzle starting to the
	// external borders and arriving in the midle
	public void doPuzzle() {
		int l = 0;
		if (dimRow % 2 == 0) {
			l = dimRow / 2;
		} else {
			l = (dimRow / 2) + 1;
		}
		for (int i = 0; i <= l; i++) {
			if (i == 0) {
				doAngol();
				printPuzle();
			} else if (i == 1) {
				doBorder();
				printPuzle();
			} else {
				doCorner(i);
				printPuzle();
			}
		}
	}

	public void doAngol() {
		ArrayList<piece> l = new ArrayList<piece>();
		for (int i = 0; i < pieces.size(); i++) {
			piece p = pieces.get(i);
			int r = p.getIdr();
			int c = p.getIdc();
			if (r == 1 && c == 1) {
				border b = p.getMyborder();
				p.setSeeborder(b);
				;
				space[1][1] = p;
				l.add(p);
			}
			if (r == 1 && c == dimColon) {
				border b = p.getMyborder();
				p.setSeeborder(b);
				;
				space[1][dimColon] = p;
				l.add(p);
			}
			if (r == dimRow && c == 1) {
				border b = p.getMyborder();
				p.setSeeborder(b);
				;
				space[dimRow][1] = p;
				l.add(p);
			}
			if (r == dimRow && c == dimColon) {
				border b = p.getMyborder();
				p.setSeeborder(b);
				space[dimRow][dimColon] = p;
				l.add(p);
			}
		}
		for (int j = 0; j < l.size(); j++) {
			piece p = l.get(j);
			delete(p);
		}
	}

	public void doBorder() {
		ArrayList<piece> l = new ArrayList<piece>();
		for (int i = 0; i < pieces.size(); i++) {
			piece p = pieces.get(i);
			int r = p.getIdr();
			int c = p.getIdc();
			if (r == 1) {
				border b = p.getMyborder();
				p.setSeeborder(b);
				space[r][c] = p;
				l.add(p);
			}
			if (c == dimColon) {
				border b = p.getMyborder();
				p.setSeeborder(b);
				space[r][c] = p;
				l.add(p);
			}
			if (r == dimRow) {
				border b = p.getMyborder();
				p.setSeeborder(b);
				space[r][c] = p;
				l.add(p);
			}
			if (c == 1) {
				border b = p.getMyborder();
				p.setSeeborder(b);
				space[r][c] = p;
				l.add(p);
			}
		}
		for (int j = 0; j < l.size(); j++) {
			piece p = l.get(j);
			delete(p);
		}
	}

	public void doCorner(int n) {
		int rt = n;
		int cl = n;
		int rd = dimRow + 1 - n;
		int cr = dimColon + 1 - n;
		ArrayList<piece> l = new ArrayList<piece>();
		if (pieces != null) {
			for (int i = 0; i < pieces.size(); i++) {
				piece p = pieces.get(i);
				int r = p.getIdr();
				int c = p.getIdc();
				if (r == rt || c == cr || r == rd || c == cl) {
					border b = p.getMyborder();
					p.setSeeborder(b);
					space[r][c] = p;
					l.add(p);
				}
			}
		}
		for (int j = 0; j < l.size(); j++) {
			piece p = l.get(j);
			delete(p);
		}
	}

	// the program does the puzzle putting pieces one by one in the matrix
	public void doAutomatically() {
		while (!pieces.isEmpty()) {
			piece p = pieces.get(0);
			border b = p.getMyborder();
			p.setSeeborder(b);
			int r = p.getIdr();
			int c = p.getIdc();
			space[r][c] = p;
			printPuzle();
			pieces.remove(0);
		}
	}

	public void printPuzle() {
		for (int i = 0; i <= dimColon; i++) {
			for (int j = 0; j <= dimRow; j++) {
				piece p = space[i][j];
				if (p == null) {
					System.out.print("- ");
				} else {
					p.printSeePiece();
					System.out.print(", ");
				}
			}
			System.out.print("\n");
		}
	}

	public void printBuildPuzle() {
		for (int i = 0; i <= dimColon; i++) {
			piece p = space[0][i];
			p.printSeePiece();
			System.out.print(", ");
		}
		System.out.print("\n");
		;
		for (int i = 1; i <= dimColon; i++) {
			for (int j = 0; j <= dimRow; j++) {
				piece p = space[i][j];
				if (i == 0) {
					p.printSeePiece();
					System.out.print(", ");
				}
				if (p == null) {
					System.out.print("- ");
				} else {
					printBorderPiece(p);
					System.out.print(", ");
				}
			}
			System.out.print("\n");
			;
		}
	}

	public void printBorderPiece(piece p) {
		int r = p.getIdr();
		int c = p.getIdc();
		border b = p.getMyborder();
		int[] l = new int[4];
		if (r > 1) {
			if (space[r - 1][c] == null) {
				l[0] = b.getTop();
			}
		}
		if (c < dimColon) {
			if (space[r][c + 1] == null) {
				l[1] = b.getRight();
			}
		}
		if (r < dimRow) {
			if (space[r + 1][c] == null) {
				l[2] = b.getDonw();
			}
		}
		if (c > 1) {
			if (space[r][c - 1] == null) {
				l[3] = b.getLeft();
			}
		}
		int z = 0;
		for (int j = 0; j < 4; j++) {
			if (l[j] != 0) {
				z++;
			}
		}
		if (z == 0) {
			p.printSeePiece();
			System.out.print(", ");
		} else {
			System.out.print("( ");
			p.printSeePiece();
			System.out.print(", border: ");
			if (l[0] != 0) {
				System.out.print("top " + l[0] + ", ");
			}
			if (l[1] != 0) {
				System.out.print("right " + l[1] + ", ");
			}
			if (l[2] != 0) {
				System.out.print("down " + l[2] + ", ");
			}
			if (l[3] != 0) {
				System.out.print("left " + l[3]);
			}
			System.out.print(" )");
		}
	}

	public ArrayList<piece> getPieces() {
		return pieces;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int n) {
		num = n;
	}

	public String getPicture() {
		return picture;
	}

}
