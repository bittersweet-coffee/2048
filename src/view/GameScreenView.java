package view;

import java.util.Observable;
import java.util.Observer;

import generic.GameLogic;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * TODO
 */
public class GameScreenView implements Observer {

	private Parent root;
	private GridPane board;
	private Button start;
	
	/**
	 * TODO
	 * @param root
	 */
	public GameScreenView(Parent root) {
		this.root = root;
		loadComponents(this.root);
	}

	/**
	 * TODO
	 * @param root
	 */
	private void loadComponents(Parent root) {
		this.board = (GridPane) root.lookup("#board");
		this.start = (Button) root.lookup("#btn_start");
	}

	/**
	 * TODO
	 */
	private void performGameWinView() {
		for (int i = 0; i < GameLogic.ROW; i++) {
			for (int j = 0; j < GameLogic.COL; j++) {
				for (Node node : this.board.getChildren()) {
					if (node instanceof HBox && GridPane.getRowIndex(node) == i
							&& GridPane.getColumnIndex(node) == j) {
						if (i == 1 && j == 0) {
							((NumberBox) node).setLabelText("Y");
						}
						if (i == 1 && j == 1) {
							((NumberBox) node).setLabelText("O");
						}
						if (i == 1 && j == 2) {
							((NumberBox) node).setLabelText("U");
						}
						if (i == 2 && j == 1) {
							((NumberBox) node).setLabelText("W");
						}
						if (i == 2 && j == 2) {
							((NumberBox) node).setLabelText("O");
						}
						if (i == 2 && j == 3) {
							((NumberBox) node).setLabelText("N");
						}
					}
				}
			}
		}
		this.start.setText("RESTART");
		this.start.setDisable(false);
	}

	/**
	 * TODO
	 */
	private void performGameOverView() {
		for (int i = 0; i < GameLogic.ROW; i++) {
			for (int j = 0; j < GameLogic.COL; j++) {
				for (Node node : this.board.getChildren()) {
					if (node instanceof HBox && GridPane.getRowIndex(node) == i
							&& GridPane.getColumnIndex(node) == j) {
						if (i == 1 && j == 0) {
							((NumberBox) node).setLabelText("G");
						}
						if (i == 1 && j == 1) {
							((NumberBox) node).setLabelText("A");
						}
						if (i == 1 && j == 2) {
							((NumberBox) node).setLabelText("M");
						}
						if (i == 1 && j == 3) {
							((NumberBox) node).setLabelText("E");
						}
						if (i == 2 && j == 0) {
							((NumberBox) node).setLabelText("O");
						}
						if (i == 2 && j == 1) {
							((NumberBox) node).setLabelText("V");
						}
						if (i == 2 && j == 2) {
							((NumberBox) node).setLabelText("E");
						}
						if (i == 2 && j == 3) {
							((NumberBox) node).setLabelText("R");
						}
					}
				}
			}
		}
		this.start.setText("RESTART");
		this.start.setDisable(false);
	}

	/**
	 * TODO
	 */
	@Override
	public void update(Observable obs, Object obj) {
		boolean state = (boolean) obj;
		if (state) {
			performGameWinView();
		} else {
			performGameOverView();
		}
	}
}
