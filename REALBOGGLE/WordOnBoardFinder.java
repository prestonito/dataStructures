
import java.util.*;

/** ADD ME! */
public class WordOnBoardFinder implements IWordOnBoardFinder {
    
    public List<BoardCell> cellsForWord(BoggleBoard board, String word) {
        // IMPLEMENT ME!!
    	// Looks in the Boggle board and figures out the board cells needed
    	// to construct a given word, using a recursive procedure.
    	// The method documentation (in the interface) provides additional details.
        
        // For now, we simply return an empty list. Thus, the program
        // will not be able to validate any of the words keyed in by
        // the human player. Replace the following return statement
        // once you complete your method definition.



    
        List<BoardCell> list = new ArrayList<BoardCell>();

        for (int row = 0; row < board.size(); row++) {
            for (int col = 0; col < board.size(); col++) {
                int index = 0;
                //if (wordArray[0] == board.getFace(row, col).toCharArray()) {
                if (wordInBoardChecker(board, row, col, index + 1, word, list)) {
                    return list;
                }
            }
        }
        return list;

    }




    private boolean wordInBoardChecker(BoggleBoard board, int row, int col, int index, String word, List<BoardCell> visitedCells) {

        word = word.substring(1);
        char [] wordArray = word.toCharArray();

        //base cases
        if (word.length() == 0) {
            return true;
        }
        if (!validMove(board, row, col)) {
            return false;
        }
        if ((visitedCells.contains(new BoardCell(row, col)))) {
            return false;
        }

        //go around the center block
        for (int i = -1; i < 2; i++){
            for (int j = -1; j < 2; j++){
                //excludes the actual block
                if (i != 0 && j != 0) {
                    //if the cell's letter matches the correct letter
                    if ((board.getFace(row + i, col + i).toCharArray()[0] == wordArray[1])) {
                        visitedCells.add(new BoardCell(row + i, col + j));
                        //looks to see if the next letter is true
                        if (wordInBoardChecker(board, row + i, col + i, index + 1, word, visitedCells)) {
                            return true;
                        }
                    }
                }    
            }
        }
        return false; 
    }   



    //checks if move is valid
    private boolean validMove(BoggleBoard board, int row, int col) {  
        if ((row >= board.size()) || (row < 0) || (col >= board.size()) || (col < 0)) {
            return false;
        }
        return true;
    }
    
}

