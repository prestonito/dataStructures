
/**
 *  Class for representing and manipulating the state of a Chess board.
 * 
 * Only stores information relevant to solving the N Queens problem.
 * 
 * @author Raghu Ramanujan
 * @author Hammurabi Mendes
 */

public class Board {
	private int[] board;

	// Indicator for unassigned queen position
	private static final int UNASSIGNED = Integer.MAX_VALUE;

	/**
	 * Constructs an empty size-by-size board.
	 *
	 * @param  size the size of the board.
	 * @throws IllegalArgumentException if size is less than 1.
	 */
	public Board(int size) {
		if(size < 0) {
			throw new IllegalArgumentException();
		}

		board = new int[size];
		for (int i = 0; i < size; i++) {
			board[i] = UNASSIGNED;
		}
	}

	/**
	 *  Returns whether it is safe to place a queen at position (row, col).
	 *
	 * @param row row of position to check.
	 * @param col column of position to check.
	 * 
	 * @return true< iff it is safe to place a queen at position (row, col).
	 * @throws IllegalArgumentException if row and col do not represent a
	 *         legal board position.
	 */
	public boolean safePlaceQueen(int row, int col) {
		// First check if row and column are within bounds
		if(!isWithinBoardBounds(row, col)) {
			throw new IllegalArgumentException();
		}

		// Next check that the current column is empty
		if(board[col] != UNASSIGNED) {
			return false;
		}

		// Now check for conflicts with other columns
		for(int currCol = 0; currCol < board.length; currCol++) {
			int distance = col - currCol;

			// Check for diagonal conflict
			if(board[currCol] == row - distance)
				return false;

			// Check for conflict in this row
			if(board[currCol] == row)
				return false;

			//Check for other diagonal conflict
			if (board[currCol] == row + distance)
				return false;
		}

		return true;
	}

	/**
	 *  Places a queen at position (row, col).
	 *
	 * @param row row of position to check.
	 * @param col column of position to check.
	 * 
	 * @throws IllegalArgumentException if it is not safe to place a queen
	 * at the given position.
	 */
	public void placeQueen(int row, int col) {
		// Exception if out of bounds (tested in safePlaceQueen() or unsafe to place
		if(!safePlaceQueen(row, col)) {
			throw new IllegalArgumentException();
		}

		board[col] = row;
	}

	/**
	 *  Removes the queen at position (row, col).
	 *
	 * @param row row of position to check.
	 * @param col column of position to check.
	 * @throws IllegalArgumentException if there is no queen at the given 
	 * position.
	 */
	public void removeQueen(int row, int col) {
		// Exception if out of bounds or queen is not there
		if(!isWithinBoardBounds(row, col) || board[col] != row) {
			throw new IllegalArgumentException();
		}

		board[col] = UNASSIGNED;
	}

	/** Returns the size of the board.
	 *
	 * @return the size of the board.
	 */
	public int size() {
		return board.length;
	}

	@Override
	public String toString() {
		String boardRep = "";
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {
				if (board[col] == row)
					boardRep += " Q ";
				else
					boardRep += " - ";
			}
			boardRep += "\n";
		}

		return boardRep;
	}

	/**
	 *  Returns true iff row and column are legal for this board.
	 * 
	 * @param row index of row.
	 * @param col index of column.
	 * @return true iff row and column are in bounds.
	 */
	private boolean isWithinBoardBounds(int row, int col) {
		return row >= 0 && row < board.length && col >= 0 && col < board.length;
	}
}
