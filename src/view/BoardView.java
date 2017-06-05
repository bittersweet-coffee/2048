package view;

import java.util.Observable;
import java.util.Observer;

import controller.BoardController;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * TODO
 */
public class BoardView extends GameView implements Observer {

	private GridPane board;
	private Button up, down, left, right, start, restart;
	private VBox container;

	/**
	 * TODO
	 * 
	 * @param root
	 */
	public BoardView(Parent root) {
		super(root);
	}

	/**
	 * TODO
	 * 
	 * @param board
	 */
	@Override
	protected void init() {
		int rows = this.board.getRowConstraints().size();
		int cols = this.board.getColumnConstraints().size();

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				NumberBox nb = new NumberBox();
				nb.setLabelText("");
				this.board.add(nb, j, i);
			}
		}
		Scene scene = new Scene(super.getRoot());
		scene.getStylesheets().add(getClass()
				.getResource("/view/stylesheet.css").toExternalForm());
		this.setTitle("2048");
		this.setScene(scene);
		this.show();
	}

	/**
	 * TODO
	 * 
	 * @param root
	 */
	@Override
	protected void loadComponents(Parent root) {
		this.container = (VBox) root.lookup("#container");
		this.board = (GridPane) root.lookup("#board");
		this.up = (Button) root.lookup("#btn_up");
		this.down = (Button) root.lookup("#btn_down");
		this.left = (Button) root.lookup("#btn_left");
		this.right = (Button) root.lookup("#btn_right");
		this.start = (Button) root.lookup("#btn_start");
		this.restart = (Button) root.lookup("#btn_restart");
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
		this.restart.setOnAction(event -> controller.handle(event));
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
							((NumberBox) node).setStyle(board[i][j]);
							((NumberBox) node).setLabelText(
									Integer.toString(board[i][j]));

						} else {
							((NumberBox) node).setStyle(0);
							((NumberBox) node).setLabelText("");
						}
					}
				}
			}
		}
	}
}
