import javax.swing.JFrame;

/**
 * Sudoku player.
 * 
 * @author Dr. Locke
 *
 */
public class Sudoku extends JFrame {
	private SudokuPanel panel;

	// A 9x9 Sudoku board; 0 indicates empty position
	private int[][] board;

	// Number of times you tried a number
	private int count;

	// Window title
	String title;

	/**
	 * Constructor to initialize the display panel for the Sudoku board.
	 * 
	 * @param Board the 9X9 integer array containing the initial Sudoku board.
	 * 
	 * @param title the title of this Sudoku game.
	 */
	public Sudoku(int[][] board, String title) {
		panel = new SudokuPanel();

		this.board = board;
		this.title = title;

		count = 0;

		setContentPane(panel);
		setTitle(title);
		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();
		setVisible(false);
	}

	/**
	 * Method to see whether the current Sudoku board is complete.
	 * 
	 * @return true if the board is full; false otherwise.
	 */
	public boolean solved() {
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				// If there's an empty entry, the board is incomplete
				if (board[r][c] == 0) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Method to determine whether the current Sudoku board is legal at the given row/col.
	 * 
	 * @param row row to be checked for legality with the rest of the board.
	 * @param col column to be checked for legality with the rest of the board.
	 * @return true if the character at row/col is legal with respect to the rest of the board.
	 */
	public boolean legal(int row, int col) {
		// Check this column
		for (int r = 0; r < 9; r++) {
			if (r != row && board[r][col] == board[row][col]) { // Invalid move
				return false;
			}
		}

		// Check this row
		for (int c = 0; c < 9; c++) {
			if (c != col && board[row][c] == board[row][col]) { // Invalid move
				return false;
			}
		}

		// Check this 3x3 Block
		for (int r = 3*(row/3); r < 3*(row/3)+3; r++) {
			for (int c = 3*(col/3); c < 3*(col/3)+3; c++) {
				if (r != row && c != col && board[r][c] == board[row][col]) { // Invalid move
					return false;
				}
			}
		}

		// All went OK, so return true
		return true;
	}

	/**
	 * Method to play the remaining moves on the current Sudoku board
	 * @return true if the Sudoku board is successfully completed; false otherwise
	 */
	public boolean solveSudoku() {

		if (solved()) {
			return true;
		}	
		//traverses
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col ++) {
				if (board[row][col] == 0) {
					for (int guess = 1; guess < 10; guess++) {
						board[row][col] = guess;
						if (legal(row,col)) {
							if (solveSudoku()) {
								return true;
							}
						}
					}
					board[row][col] = 0;
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * Returns the number of moves to solve the game.
	 * 
	 * @return The number of moves to solve the game.
	 */
	public int getCount() {
		return count;
	}
	/**
	 * Method to display the current Sudoku board in a panel
	 */
	public void printBoard() {
		panel.draw(board);
		setVisible(true);
	}

	/**
	 * Test method for several Sudoku puzzles
	 * @param args not used in this program
	 */
	public static void main(String[] args) {
		int[][] board1 = {{8,0,0,0,0,0,0,0,3},
				{5,6,0,0,7,0,0,0,0},
				{7,0,0,0,0,6,0,2,0},
				{0,0,0,0,0,5,9,0,8},
				{0,4,0,0,9,0,0,5,0},
				{2,0,5,4,0,0,0,0,0},
				{0,5,0,2,0,0,0,0,9},
				{0,0,0,0,1,0,0,8,4},
				{3,0,0,0,0,0,0,0,2}};

		Sudoku game1 = new Sudoku(board1, "Board 1");
		System.out.println("PlaySudoku() returned " + game1.solveSudoku() + " in " + game1.getCount() + " iterations.");
		game1.printBoard();

		int[][] board2 = {{3,0,7,0,2,0,0,4,5},
				{0,1,8,6,4,0,0,0,7},
				{2,5,0,0,0,0,0,0,0},
				{0,0,0,9,5,0,0,1,8},
				{0,8,0,4,0,1,0,9,0},
				{1,9,0,0,3,6,0,0,0},
				{0,0,0,0,0,0,0,7,6},
				{4,0,0,0,6,8,5,3,0},
				{6,3,0,0,1,0,9,0,2}};

		Sudoku game2 = new Sudoku(board2, "Board 2");
		System.out.println("PlaySudoku() returned " + game2.solveSudoku() + " in " + game2.getCount() + " iterations.");
		game2.printBoard();

		int[][] board3 = {{0,0,0,0,0,7,3,0,6},
				{0,0,0,1,0,0,0,0,0},
				{0,0,0,0,9,0,0,2,7},
				{0,2,4,0,0,0,7,0,0},
				{0,0,5,4,2,3,1,0,0},
				{0,0,8,0,0,0,5,4,0},
				{2,4,0,0,8,0,0,0,0},
				{0,0,0,0,0,5,0,0,0},
				{1,0,9,6,0,0,0,0,0}};

		Sudoku game3 = new Sudoku(board3, "Board 3");
		System.out.println("PlaySudoku() returned " + game3.solveSudoku() + " in " + game3.getCount() + " iterations.");
		game3.printBoard();
	}
}
