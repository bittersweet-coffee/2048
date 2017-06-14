package view;

import java.util.Observable;

import generic.GameLogic;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class GameScreenView extends GameView {

	private GridPane board;
	private Button start;

	/**
	 * 
	 * gets the root fxml file where all the view stuff is placed on.
	 * 
	 * @param root
	 */
	public GameScreenView(Parent root) {
		super(root);
	}

	/**
	 * loads the components form the fxml file which are important for this
	 * view.
	 * 
	 * @param root
	 */
	@Override
	protected void loadComponents(Parent root) {
		this.board = (GridPane) root.lookup("#board");
		this.start = (Button) root.lookup("#btn_start");
	}

	/**
	 * this view has nothing to initialize. The method has to be overridden
	 * because this view extends GameView class.
	 */
	@Override
	protected void init() {
	}

	/**
	 * Displays the winning text on the board when a game is won.
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
	 * Displays the loosing text on the board when a game is lost.
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
	 * Updates the board with a winning/loosing text when its over.
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
