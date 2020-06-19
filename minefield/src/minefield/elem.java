package minefield;

class elem {
	private String see;
	private int danger;
	private int row;
	private int colon;
	private boolean bomb;

	/*
	 * The object elem is the representation of a cell of space game. The String see
	 * is use to define what user can see during the game. The integer row and colon
	 * are use to define the position of the element in the space game. The integer
	 * danger is use to indicate the number of bombs that are in the near cells.
	 */

	public elem() {
		see = "-";
		danger = 0;
		row = 0;
		colon = 0;
		bomb = false;
	}

	public elem(String a) {
		see = a;
		danger = 0;
		row = 0;
		colon = 0;
		bomb = false;
	}

	public elem(String a, int r, int c, boolean b) {
		see = a;
		row = r;
		colon = c;
		if (b) {
			danger = -1;
		} else {
			danger = 0;
		}
		bomb = b;
	}

	public void addDanger(int n) {
		danger = danger + n;
	}

	public String getSee() {
		return see;
	}

	public boolean getBomb() {
		return bomb;
	}

	public int getDanger() {
		return danger;
	}

	public int getRow() {
		return row;
	}

	public int getColon() {
		return colon;
	}

	public void setSee(String a) {
		see = a;
	}

	public void setBomb(boolean b) {
		bomb = b;
	}

	public void setDanger(int n) {
		danger = n;
	}

	public void setRow(int r) {
		row = r;
	}

	public void setColon(int c) {
		colon = c;
	}

}
