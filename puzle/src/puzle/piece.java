package puzle;

import java.util.*;

public class piece {
	private int idr;
	private int idc;
	private String see;
	private border myborder;
	private border seeborder;

	/*
	 * The object piece define a element of a puzzle. The String see represents a
	 * segment of a total picture of the puzzle. The idr and idc are the absolute
	 * position of the piece in the puzzle's matrix. myborder and seeborder are
	 * border object. The first represents the border's piece in the original
	 * position. While the second are the border that the user see after the shake .
	 */

	public piece() {
		idr = 0;
		idc = 0;
		see = "";
		myborder = new border();
		seeborder = new border();
	}

	public piece(int r, int c, String s) {
		idr = r;
		idc = c;
		see = s;
		myborder = new border();
		seeborder = new border();
	}

	public int getIdr() {
		return idr;
	}

	public int getIdc() {
		return idc;
	}

	public String getSee() {
		return see;
	}

	public border getMyborder() {
		return myborder;
	}

	public border getSeeborder() {
		return seeborder;
	}

	public void setIdr(int n) {
		idr = n;
	}

	public void setIdc(int n) {
		idc = n;
	}

	public void setSee(String s) {
		see = s;
	}

	public void setMyborder(border b) {
		myborder = b;
	}

	public void setSeeborder(border b) {
		seeborder = b;
	}

	public void printPiece() {
		String s = this.see;
		System.out.print("( " + s + " ");
		seeborder.printBorder();
		System.out.print(" )");
	}

	public void printSeePiece() {
		String s = this.see;
		System.out.print(s);
	}

}
