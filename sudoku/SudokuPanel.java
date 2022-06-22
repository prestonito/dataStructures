import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 * Panel to show a Sudoku game.
 * 
 * @author HM
 */
public class SudokuPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JTextField[][] fields;
	private JPanel[][] panels;

	public SudokuPanel() {
		fields = new JTextField[9][9];
		panels = new JPanel[3][3];

		setLayout(new GridLayout(3, 3, 2, 2));
		setBorder(new LineBorder(Color.WHITE));

		for(int row = 0; row < 3; row++) {
			for(int col = 0; col < 3; col++) {
				JPanel panel = new JPanel();

				panel.setLayout(new GridLayout(3, 3, 2, 2));
				panel.setBorder(new LineBorder(Color.BLACK));
				add(panel);

				panels[row][col] = panel;
			}
		}


		for(int row = 0; row < 9; row++) {
			for(int col = 0; col < 9; col++) {
				fields[row][col] = new JTextField(1);
				fields[row][col].setText(" ");

				panels[row / 3][col / 3].add(fields[row][col]);
			}
		}

		setFocusable(true);
		setVisible(true);
	}

	public void draw(int[][] board) {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if(board[row][col] != 0) {
					fields[row][col].setText(Integer.toString(board[row][col]));
				}
			}
		}
	}
}
