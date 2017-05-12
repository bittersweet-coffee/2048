package view;

import java.util.Observable;
import java.util.Observer;

import controller.BoardController;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BoardView implements Observer {

	private static final int FONT_SIZE_SMALL = 33;

	private GridPane board;
	private Parent root;
	private Button top, left, down, right;
	private VBox container;

	public BoardView(Parent root) {
		this.root = root;
		loadComponents(this.root);
		initBoard(this.board);
	}

	private void loadComponents(Parent root) {
		this.container = (VBox) root.lookup("#container");
		this.board = (GridPane) root.lookup("#board");
		this.top = (Button) root.lookup("#btn_top");
		this.left = (Button) root.lookup("#btn_left");
		this.down = (Button) root.lookup("#btn_down");
		this.right = (Button) root.lookup("#btn_right");

	}

	public void addCotroller(BoardController controller) {
		this.container
				.setOnKeyPressed(event -> controller.actionPerformed(event));
		this.container.setOnKeyPressed(event -> controller.handle(event));
		this.top.setOnAction(event -> controller.handle(event));
		this.left.setOnAction(event -> controller.actionLeft(event));
		this.down.setOnAction(event -> controller.actionDown(event));
		this.right.setOnAction(event -> controller.actionRight(event));
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

	private void initBoard(GridPane board) {
		int rows = board.getRowConstraints().size();
		int cols = board.getColumnConstraints().size();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				NumberBox nb = new NumberBox();
				nb.setLabelText("");
				board.add(nb, j, i);
			}
		}

	}

}
