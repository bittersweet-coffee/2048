package view;

import java.util.Observable;
import java.util.Observer;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.IGame;

public class BoardView implements Observer, IGame {

	private GridPane board;

	public BoardView(GridPane board) {
		this.board = board;
	}

	/**
	 * TODO
	 */
	public void update(Observable obs, Object obj) {
		Integer[][] boardModel = (Integer[][]) obj;
		for (int i = 0; i < boardModel.length; i++) {
			for (int j = 0; j < boardModel.length; j++) {
				for (Node node : board.getChildren()) {
					if (node instanceof HBox && GridPane.getRowIndex(node) == i
							&& GridPane.getColumnIndex(node) == j) {
						if (boardModel[i][j] != 0) {
							if (boardModel[i][j] > 1000) {
								((NumberBox) node)
										.setLabelFontSize(FONT_SIZE_SMALL);
								((NumberBox) node).setLabelText(
										Integer.toString(boardModel[i][j]));
							}
							((NumberBox) node).setLabelText(
									Integer.toString(boardModel[i][j]));
						} else {
							((NumberBox) node).setLabelText("");
						}
					}
				}
			}
		}
	}

	@Override
	public void init() {
		System.out.println("init View");
		int rows = this.board.getRowConstraints().size();
		int cols = this.board.getColumnConstraints().size();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				NumberBox nb = new NumberBox();
				nb.setLabelText("");
				this.board.add(nb, j, i);
			}
		}

	}

}
