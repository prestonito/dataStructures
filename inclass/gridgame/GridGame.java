/**
 * Grid Game
 * 
 * In a simple game, two players take turns placing 'X's in a 4x4 grid. 
 * 
 * Players may place 'X's in any available location ('.' in the input) 
 * that is not horizontally or vertically adjacent to another 'X'. The player 
 * who places the last 'X' wins the game. 
 * 
 * @author Preston Ito
 */
public class GridGame {
	/**
	 * Assuming it is your turn, return how many of the moves you can make
	 * guarantee you will win the game, assuming you play perfectly.
	 * 
	 * @param grid - the current grid board
	 * @return - the number of moves you could make that guarantee you win
	 */
	public int winningMoves(char[][] grid){
		int winningCounter = 0;
		for (int row = 0; row < grid.length; row ++) {
			for (int col = 0; col < grid.length; col ++) {
				if (canPlace(grid, row, col)) {
					grid[row][col] = 'X';
					if (winningMoves(grid) == 0) {
						winningCounter++; 
					}
					grid[row][col] = '.';
				}
			}
		}
		return 0 + winningCounter; 
	}

	/**
	 * Returns true if an X can be placed on the current board
	 * at location row r, column c.
	 * 
	 * 
	 * @param grid - The current board
	 * @param r - The row location on the board
	 * @param c - The column location on the board
	 * @return True if an X can be placed on the current board at
	 *         row r, column c.
	 */
	private boolean canPlace(char[][] grid, int r, int c){
		if(grid[r][c] == 'X') {
			return false;
		}

		if(r > 0 && grid[r-1][c] == 'X') {
			return false;
		}
		if(r < 3 && grid[r+1][c] == 'X') {
			return false;
		}
		if(c > 0 && grid[r][c-1] == 'X') {
			return false;
		}
		if(c < 3 && grid[r][c+1] == 'X') {
			return false;
		}

		return true;
		
	}

	public static void main(String[] args){
		GridGame game = new GridGame();

		// Test boards
		String[][] boards = {{"....", "....", "....", "...."}, 
				             {"....", "....", ".X..", "...."}, 
				             {".X.X", "..X.", ".X..", "...."}, 
				             {".X.X", "..X.", "X..X", "..X."}, 
				             {"X..X", "..X.", ".X..", "X..X"}};

		// Answers are: 0, 11, 1, 0, 0

		for(int i = 0; i < boards.length; i++){
			System.out.println("****Testing Board****\n");

			for(int j = 0; j < boards[i].length; j++){
				System.out.println(boards[i][j]);
			}

			char[][] boardCharGrid = game.convertToCharGrid(boards[i]);

			System.out.println("Wining moves: " + game.winningMoves(boardCharGrid)); 

			System.out.println("*********************\n");
		}
	}
	
	/**
	 * Convert board game passed as an array of four strings,
	 * each containing 4 characters among 'X' and '.'.
	 * 
	 * @param The board game.
	 * 
	 * @return The board game converted to a bi-dimensional character array.
	 */
	private char[][] convertToCharGrid(String[] grid){
		// Convert to a character grid so that the board is easier to work with
		char[][] charGrid = new char[grid.length][grid[0].length()];

		for(int i = 0; i < grid.length; i++){
			charGrid[i] = grid[i].toCharArray();
		}

		return charGrid;
	}
}
