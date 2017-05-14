
import controller.BoardController;
import controller.StatsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.BoardModel;
import model.StatsModel;
import view.BoardView;

/**
 * Provides the Game, initializing all required objects and binding them
 * together.
 */
public class Game extends Application {

	private FXMLLoader loader;

	private BoardController boardController;
	private BoardModel boardModel;
	private BoardView boardView;
	private StatsModel statsModel;
	private StatsController statsController;

	/**
	 * Initialize the Game object and glue the models, controllers and views
	 * together.
	 */
	public Game() {
	}

	/**
	 * TODO
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.loader = new FXMLLoader(
				getClass().getResource("/view/GameView.fxml"));

		primaryStage.setTitle("2048");
		Parent root = this.loader.load();

		this.boardView = new BoardView(root);

		this.boardModel = new BoardModel();
		this.statsModel = new StatsModel();

		this.boardController = new BoardController();
		this.statsController = new StatsController();

		this.boardModel.addObserver(boardView);

		this.boardController.addBoardModel(boardModel);
		this.boardController.addBoardView(boardView);
		this.boardView.addCotroller(boardController);
		this.boardController.initModel();

		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass()
				.getResource("/view/stylesheet.css").toExternalForm());

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Main entry point, root of all evil.
	 * @param args Program arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
