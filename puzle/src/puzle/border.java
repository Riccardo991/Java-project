package puzle;

public class border {
	private int top;
	private int right;
	private int donw;
	private int left;

	/*
	 * The object border define the border of a piece . Top, down, right and left
	 * are the sides of a piece. If the value is positive mean that the border has a
	 * outer fit. If it is negative mean that the border has a inner fit and if
	 * value is 0 the border is flat.
	 */

	public border() {
		top = 0;
		right = 0;
		donw = 0;
		left = 0;
	}

	public border(int a, int b, int c, int d) {
		top = a;
		right = b;
		donw = c;
		left = d;
	}

	public int getTop() {
		return top;
	}

	public int getRight() {
		return right;
	}

	public int getDonw() {
		return donw;
	}

	public int getLeft() {
		return left;
	}

	public void setTop(int n) {
		top = n;
	}

	public void setRight(int n) {
		right = n;
	}

	public void setDonw(int n) {
		donw = n;
	}

	public void setLeft(int n) {
		left = n;
	}

	public boolean isEquals(border b) {
		int t1 = this.top;
		int t2 = b.getTop();
		int r1 = this.right;
		int r2 = b.getRight();
		int d1 = this.donw;
		int d2 = b.getDonw();
		int l1 = this.left;
		int l2 = b.getLeft();
		if (t1 == t2 && r1 == r2 && d1 == d2 && l1 == l2) {
			return true;
		} else {
			return false;
		}
	}

	public void printBorder() {
		System.out.print("border: " + "top = " + top + " right = " + right + " donw = " + donw + " left = " + left);
	}

}
