
import controller.BoardController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.BoardModel;
import view.BoardView;
import view.ScoreView;

/**
 * Provides the Game, initializing all required objects and binding them
 * together.
 */
public class Game extends Application {

	private FXMLLoader loader;
	private BoardController boardController;
	private BoardModel boardModel;
	private BoardView boardView;
	private ScoreView scoreView;

	/**
	 * Initialize the Game object and glue the model, controller and view
	 * together.
	 */
	public Game() {
		this.loader = new FXMLLoader(
				getClass().getResource("/view/view_Board.fxml"));
		this.boardController = new BoardController();
		this.loader.setController(this.boardController);

	}

	/**
	 * TODO
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("2048");
		Parent root = this.loader.load();
		GridPane board = (GridPane) root.lookup("#board");
		Label lbl_score = (Label) root.lookup("#score");
		Label lbl_score_value = (Label) root.lookup("#score_value");
		this.boardView = new BoardView(board);
		this.boardModel = new BoardModel();
		this.scoreView = new ScoreView(lbl_score, lbl_score_value);
		this.boardModel.addObserver(boardView);
		this.boardModel.addObserver(scoreView);
		this.boardController.add(boardView);
		this.boardController.add(boardModel);
		this.boardController.add(scoreView);
		this.boardController.initStart();
		VBox container = (VBox) root.lookup("#container");
		container.setOnKeyReleased(event -> this.boardController
				.handleArrowKey(event, this.boardModel));
		Button top = (Button) root.lookup("#btn_top");
		Button left = (Button) root.lookup("#btn_left");
		Button down = (Button) root.lookup("#btn_down");
		Button right = (Button) root.lookup("#btn_right");
		top.setOnAction(
				event -> this.boardController.actionTop(event, boardModel));
		left.setOnAction(
				event -> this.boardController.actionLeft(event, boardModel));
		down.setOnAction(
				event -> this.boardController.actionDown(event, boardModel));
		right.setOnAction(
				event -> this.boardController.actionRight(event, boardModel));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass()
				.getResource("/view/stylesheet.css").toExternalForm());

		primaryStage.setScene(scene);
		primaryStage.show();

	}

	/**
	 * TODO
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
