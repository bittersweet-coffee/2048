package view;

import java.util.Observable;
import java.util.Observer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class BoardView implements Observer {

	private GridPane board;

	private static final int FONT_SIZE = 40;
	private static final String FONT_STYLE = "Arial";

	public BoardView(GridPane board) {
		this.board = board;
		initializeBoardView();
	}
	
	/**
	 * TODO
	 */
	public void initializeBoardView() {
		int rows = this.board.getRowConstraints().size();
		int cols = this.board.getColumnConstraints().size();
		
		Label lbl = new Label("");
		lbl.setFont(new Font(FONT_STYLE, FONT_SIZE));
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; i++) {
				this.board.add(lbl, j, i);
			}
		}
	}
	
	/**
	 * TODO
	 */
	public void update(Observable obs, Object obj) {
		Integer[][] boardModel = (Integer[][]) obj;
		for (int i = 0; i < boardModel.length - 1; i++) {
			for (int j = 0; j < boardModel.length - 1; i++) {
				if (boardModel[i][j] != 0) {
					Label lbl = new Label(boardModel[i][j].toString());
					lbl.setFont(new Font(FONT_STYLE, FONT_SIZE));
					board.add(lbl, j, i);
				} else {
					Label lbl = new Label("");
					lbl.setFont(new Font(FONT_STYLE, FONT_SIZE));
					board.add(lbl, j, i);
				}
			}
		}
	}
}