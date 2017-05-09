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
	
	private FXMLLoader loader;

	private BoardModel boardModel;
	private BoardView boardView;
	private BoardController boardController;


	/**
	 * Initialize the Game object and glue the model, controller and view
	 * together.
	 */
	public Game() {
		System.out.println("Controller");
		this.boardModel = new BoardModel();
		this.boardController = new BoardController();
		this.boardView = new BoardView();
		boardController.addBoardModel(this.boardModel);
		boardController.addBoardView(this.boardView);
		
		this.loader = new FXMLLoader(getClass()
				.getResource("/view/BoardView.fxml"));
	}

	/**
	 * TODO
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("start");
		primaryStage.setTitle("2048");

		Scene scene = new Scene((Parent) this.loader.load());
		scene.getStylesheets().add(getClass()
				.getResource("/view/stylesheet.css").toExternalForm());

		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		boardModel.addObserver(boardView);
		//boardController.initBoardModel();
	}

	/**
	 * TODO
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Main");
		launch(args);
	}
}
