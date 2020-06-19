package briscola;

public class card {
	private int num;
	private String seed;

	// object card represents a card's game. it has a number and a seed
	public card() {
		num = 0;
		seed = "";
	}

	public card(int n, String s) {
		num = n;
		seed = s;
	}

	public int getNum() {
		return num;
	}

	public String getSeed() {
		return seed;
	}

	public void setNum(int n) {
		num = n;
	}

	public void setSeed(String s) {
		seed = s;
	}

	public boolean isEquals(card c) {
		String s = this.seed;
		String sc = c.getSeed();
		int n = this.num;
		if (n == c.getNum() && s.equals(sc)) {
			return true;
		} else {
			return false;
		}
	}

	public void printCard() {
		String s = this.seed;
		int n = this.num;
		System.out.print("(" + n + " " + s + ")");
	}

}
