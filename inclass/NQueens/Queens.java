/**
 *  This program solves the N queens problem
 * 
 * @author ???
 */

import java.util.*;

public class Queens {

	/**
	 *  Main method
	 */ 
	public static void main(String[] args) {
		Board board = new Board(8);

		solveAndPrint(board);
	}

	/** Prints the result of searching for a solution to the N Queens problem
	 * 
	 * @param solution the Board on which we wish to place the queens.
	 */
	public static void solveAndPrint(Board board) {
		// COMPLETE ME!
		if(!explore(board, 0)) {
			System.out.println("No solution!");
		}
		else {
			System.out.println("Solution: ");
			System.out.println(board);
		}
	}


	/** Searches the given board for a solution starting at the specified column.
	 * 
	 * If a solution is found, then it is written to the Board parameter.
	 * 
	 * @param b the current partial solution that we are trying to improve.
	 * @param col the current column on which we wish to place a queen.
	 * 
	 * @return true iff a solution was found.
	 */
	public static boolean explore(Board b, int col) {
		// COMPLETE ME!

		for (int row = 0; row < b.size(); row ++) {
			if (b.safePlaceQueen(row, col)) {
				b.placeQueen(row, col);
				if (col + 1 == b.size()) {
					return true;
				}
				if (explore(b, col + 1)) {
					return true;
				}
				else {
					b.removeQueen(row, col);
				}
			}
		}
		
		return false;
	}
}
