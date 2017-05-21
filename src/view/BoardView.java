package view;

import java.util.Observable;
import java.util.Observer;

import controller.BoardController;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * TODO
 */
public class BoardView extends Stage implements Observer {

	private static final int FONT_SIZE_SMALL = 33;
	private static final int FONT_SIZE_NORMAL = 40;
	private static final String FONT_STYLE = "Arial";

	private Parent root;
	private GridPane board;
	private Button up, down, left, right, start;
	private VBox container;
	private Label labelScore;
	private Label labelScoreView;

	/**
	 * TODO
	 * 
	 * @param root
	 */
	public BoardView(Parent root) {
		this.root = root;
		loadComponents(this.root);
		initBoard(this.board);
		Scene scene = new Scene(this.root);
		scene.getStylesheets().add(getClass()
				.getResource("/view/stylesheet.css").toExternalForm());
		this.setTitle("2048");
		this.setScene(scene);
		this.show();
	}

	/**
	 * TODO
	 * 
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

		this.labelScore.setFont(new Font(FONT_STYLE, FONT_SIZE_NORMAL));
		this.labelScoreView.setFont(new Font(FONT_STYLE, FONT_SIZE_NORMAL));
	}

	/**
	 * TODO
	 * 
	 * @param root
	 */
	private void loadComponents(Parent root) {
		this.container = (VBox) root.lookup("#container");
		this.board = (GridPane) root.lookup("#board");
		this.up = (Button) root.lookup("#btn_up");
		this.down = (Button) root.lookup("#btn_down");
		this.left = (Button) root.lookup("#btn_left");
		this.right = (Button) root.lookup("#btn_right");
		this.start = (Button) root.lookup("#btn_start");
		this.labelScore = (Label) root.lookup("#score");
		this.labelScoreView = (Label) root.lookup("#score_value");
	}

	/**
	 * TODO
	 * 
	 * @param controller
	 */
	public void addCotroller(BoardController controller) {
		this.container.setOnKeyPressed(event -> controller.handle(event));
		this.up.setOnAction(event -> controller.handle(event));
		this.left.setOnAction(event -> controller.handle(event));
		this.down.setOnAction(event -> controller.handle(event));
		this.right.setOnAction(event -> controller.handle(event));
		this.start.setOnAction(event -> controller.handle(event));
	}

	/**
	 * TODO
	 */
	public void update(Observable obs, Object obj) {
		this.start.setDisable(true);
		this.start.setText("START");
		Integer[][] boardModel = (Integer[][]) obj;
		updateBoard(boardModel);
	}

	private void updateBoard(Integer[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				for (Node node : this.board.getChildren()) {
					if (node instanceof HBox && GridPane.getRowIndex(node) == i
							&& GridPane.getColumnIndex(node) == j) {
						if (board[i][j] != 0) {
							if (board[i][j] > 1000) {
								((NumberBox) node)
										.setLabelFontSize(FONT_SIZE_SMALL);
								((NumberBox) node).setLabelText(
										Integer.toString(board[i][j]));
							}
							((NumberBox) node).setLabelText(
									Integer.toString(board[i][j]));
						} else {
							((NumberBox) node).setLabelText("");
						}
					}
				}
			}
		}
	}
}
