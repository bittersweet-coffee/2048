
import controller.BoardController;
import controller.StatisticController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.BoardModel;
import model.Statistic;
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
	private Statistic statistic;
	private StatisticController statController;

	/**
	 * Initialize the Game object and glue the model, controller and view
	 * together.
	 */
	public Game() {
		this.loader = new FXMLLoader(
				getClass().getResource("/view/view_Board.fxml"));
		this.boardController = new BoardController();
		this.loader.setController(this.boardController);
		//this.statistic = new Statistic();
		//this.statController = new StatisticController();

	}

	/**
	 * TODO
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("2048");
		Parent root = this.loader.load();
		this.boardView = new BoardView(root);
		this.boardModel = new BoardModel();
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
	 * TODO
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
