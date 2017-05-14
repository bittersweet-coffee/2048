package view;

import java.util.Observable;
import java.util.Observer;

import controller.BoardController;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * TODO
 */
public class BoardView implements Observer {

	private static final int FONT_SIZE_SMALL = 33;
	private static final int FONT_SIZE_NORMAL = 40;
	private static final String FONT_STYLE = "Arial";
	
	private Parent root;
	private GridPane board;
	private Button up, down, left, right;
	private VBox container;
	private Label lbl_score;
	private Label lbl_score_value;

	/**
	 * TODO
	 * @param root
	 */
	public BoardView(Parent root) {
		this.root = root;
		loadComponents(this.root);
		initBoard(this.board);
	}

	/**
	 * TODO
	 * @param board
	 */
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

		this.lbl_score.setFont(new Font(FONT_STYLE, FONT_SIZE_NORMAL));
		this.lbl_score_value.setFont(new Font(FONT_STYLE, FONT_SIZE_NORMAL));
	}

	/**
	 * TODO
	 * @param root
	 */
	private void loadComponents(Parent root) {
		this.container = (VBox) root.lookup("#container");
		this.board = (GridPane) root.lookup("#board");
		this.up = (Button) root.lookup("#btn_up");
		this.down = (Button) root.lookup("#btn_down");
		this.left = (Button) root.lookup("#btn_left");
		this.right = (Button) root.lookup("#btn_right");
		this.lbl_score = (Label) root.lookup("#score");
		this.lbl_score_value = (Label) root.lookup("#score_value");
	}

	/**
	 * TODO
	 * @param controller
	 */
	public void addCotroller(BoardController controller) {
		this.container.setOnKeyPressed(event -> controller.handle(event));

		this.up.setOnAction(event -> controller.handle(event));
		this.left.setOnAction(event -> controller.handle(event));
		this.down.setOnAction(event -> controller.handle(event));
		this.right.setOnAction(event -> controller.handle(event));
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

	public void updateScore(Integer score) {
		if (score < 10) {
			this.lbl_score_value
					.setText("000" + Integer.toString(score));
		} else if (score < 100) {
			this.lbl_score_value
					.setText("00" + Integer.toString(score));
		} else if (score < 1000) {
			this.lbl_score_value.setText("0" + Integer.toString(score));
		} else {
			this.lbl_score_value.setText(Integer.toString(score));
		}
	}
}
