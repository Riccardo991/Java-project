package minefield;

import java.util.*;

class space {
	private elem[][] square;
	private int dimRow;
	private int dimColon;
	private int numBomb;
	private int numSeeCell;

	/*
	 * The object space represents the game-space of minefield. The matrix square is
	 * composed by objects elem and is the game-space. The integer dimRow and
	 * dimColon is use to define the dimension of the matrix. the integer numBombs
	 * is use to save the number of all bombs during a match. The integer seeCell is
	 * use to save the number of cells that user turn on during the match.
	 */

	public space() {
		square = null;
		dimRow = 0;
		dimColon = 0;
		numBomb = 0;
		numSeeCell = 0;
	}

	public space(int r, int c) {
		dimRow = r;
		dimColon = c;
		square = new elem[r + 1][c + 1];
		square[0][0] = new elem("/");
		for (int i = 1; i <= r; i++) {
			if (i < 10) {
				char a = (char) (char) (48 + i);
				String s = String.valueOf(a);
				elem e = new elem(s);
				square[i][0] = e;
			} else {
				int m = 48 + (i / 10);
				int q = 48 + (i % 10);
				char c1 = (char) (char) m;
				char c2 = (char) (char) q;
				String s1 = String.valueOf(c1);
				String s2 = String.valueOf(c2);
				String s3 = s1 + s2;
				elem e = new elem(s3);
				square[i][0] = e;
			}
		}
		for (int i = 1; i <= c; i++) {
			if (i < 10) {
				char a = (char) (char) (48 + i);
				String s = String.valueOf(a);
				elem e = new elem(s);
				square[0][i] = e;
			} else {
				int m = 48 + (i / 10);
				int q = 48 + (i % 10);
				char c1 = (char) (char) m;
				char c2 = (char) (char) q;
				String s1 = String.valueOf(c1);
				String s2 = String.valueOf(c2);
				String s3 = s1 + s2;
				elem e = new elem(s3);
				square[0][i] = e;
			}
		}
		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				elem e = new elem("-", i, j, false);
				square[i][j] = e;
			}
		}
		numBomb = 0;
		numSeeCell = r * c;
	}

	// insert the bombs in the game-space
	public void putBomb(int b) {
		numBomb = b;
		Random rand = new Random();
		int j = 0;
		while (j < b) {
			int r = rand.nextInt(dimRow - 1) + 1;
			int c = rand.nextInt(dimColon - 1) + 1;
			elem e = square[r][c];
			if (e.getBomb() == false) {
				e.setBomb(true);
				square[r][c] = e;
				j++;
			}
		}
	}

	// get the danger (number of bombs) in the neighbors cell for each cell
	public void calcolateDanger() {
		for (int i = 1; i <= dimRow; i++) {
			for (int j = 1; j <= dimColon; j++) {
				elem e = square[i][j];
				if (e.getBomb()) {
					ArrayList<elem> q = getNeighbors(i, j);
					while (!q.isEmpty()) {
						elem e1 = q.get(0);
						if (!e1.getBomb()) {
							e1.addDanger(1);
							int r1 = e1.getRow();
							int c1 = e1.getColon();
							square[r1][c1] = e1;
						}
						q.remove(0);
					}
				}
			}
		}
	}

	// turn on a set of neighbors cells when the user select a good cell
	public void turnOnCell(int r, int c) {
		ArrayList<elem> q = getNeighbors(r, c);
		int k = dimRow / 3;
		;

		while (!q.isEmpty()) {
			elem e = q.get(0);
			int n = e.getDanger();
			int re = e.getRow();
			int ce = e.getColon();
			String w = e.getSee();

			if (e.getBomb() == false && w.equals("-")) {
				if (n >= 1) {
					char a = (char) (char) (48 + n);
					String s = String.valueOf(a);
					e.setSee(s);
					square[re][ce] = e;
					numSeeCell--;
				}
				if (n == 0 && k >= 0) {
					e.setSee("0");
					square[re][ce] = e;
					numSeeCell--;
					k++;
					ArrayList<elem> q1 = getNeighbors(re, ce);
					while (!q1.isEmpty()) {
						elem e1 = q1.get(0);
						q.add(e1);
						q1.remove(0);
					}
				}
			}
			q.remove(0);
		}
	}

	public ArrayList<elem> getNeighbors(int r, int c) {
		ArrayList<elem> neighbors = new ArrayList<elem>();
		int y = r;
		int x = c;
		if (r > 1 && c > 1) {
			y = r - 1;
			x = c - 1;
			elem e1 = square[y][x];
			neighbors.add(e1);
		}
		if (r > 1) {
			y = r - 1;
			x = c;
			elem e2 = square[y][x];
			neighbors.add(e2);
		}
		if (r > 1 && c < dimColon) {
			y = r - 1;
			x = c + 1;
			elem e3 = square[y][x];
			neighbors.add(e3);
		}
		if (c < dimColon) {
			y = r;
			x = c + 1;
			elem e4 = square[y][x];
			neighbors.add(e4);
		}
		if (r < dimRow && c < dimColon) {
			y = r + 1;
			x = c + 1;
			elem e5 = square[y][x];
			neighbors.add(e5);
		}
		if (r < dimRow) {
			y = r + 1;
			x = c;
			elem e6 = square[y][x];
			neighbors.add(e6);
		}
		if (r < dimRow && c > 1) {
			y = r + 1;
			x = c - 1;
			elem e7 = square[y][x];
			neighbors.add(e7);
		}
		if (c > 1) {
			y = r;
			x = c - 1;
			elem e8 = square[y][x];
			neighbors.add(e8);
		}
		return neighbors;
	}

	public elem getElem(int r, int c) {
		elem e = square[r][c];
		return e;
	}

	public void setElem(elem e, int r, int c) {
		square[r][c] = e;
	}

	public int getDimRow() {
		return dimRow;
	}

	public int getDimColon() {
		return dimColon;
	}

	public int getNumBomb() {
		return numBomb;
	}

	public int getNumSeeCell() {
		return numSeeCell;
	}

	public void setNumSeeCell(int n) {
		numSeeCell = n;
	}

	public void printSpace() {
		System.out.print("campo minato " + "\n");
		for (int i = 0; i <= dimRow; i++) {
			for (int j = 0; j <= dimColon; j++) {
				elem e = square[i][j];
				System.out.print(e.getSee() + " ");
			}
			System.out.print("\n");
		}
	}

}
