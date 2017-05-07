import controller.BoardController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.BoardModel;
import view.BoardView;

/**
 * Provides the Game, initializing all required objects and binding them
 * together.
 */
public class Game extends Application {

	@FXML
	private GridPane board;

	private FXMLLoader loader;

	private BoardModel boardModel;
	private BoardView boardView;
	private BoardController boardController;


	/**
	 * Initialize the Game object and glue the model, controller and view
	 * together.
	 */
	public Game() {
		this.loader = new FXMLLoader(getClass()
				.getResource("/view/BoardView.fxml"));
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("2048");

		Scene scene = new Scene((Parent) this.loader.load());
		scene.getStylesheets().add(getClass()
				.getResource("/view/stylesheet.css").toExternalForm());

		primaryStage.setScene(scene);
		primaryStage.show();

		this.boardModel = new BoardModel();
		this.boardView = new BoardView(this.board);
		
		boardModel.addObserver(boardView);
		
		this.boardController = new BoardController();
		boardController.addBoardModel(this.boardModel);
		boardController.addBoardView(this.boardView);
		boardController.initBoardModel();
	}

	public static void main(String[] args) {
		launch(args);
	}
}