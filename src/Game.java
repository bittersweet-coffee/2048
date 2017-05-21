
import controller.BoardController;
import controller.StatsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import model.BoardModel;
import model.GameScreenModel;
import model.ScoreModel;
import model.StatsModel;
import view.BoardView;
import view.GameScreenView;
import view.ScoreView;

/**
 * Provides the Game, initializing all required objects and binding them
 * together.
 */
public class Game extends Application {

	private FXMLLoader loader;

	private BoardController boardController;
	private BoardModel boardModel;
	private ScoreModel scoreModel;
	private GameScreenModel gameScreenModel;
	private BoardView boardView;
	private ScoreView scoreView;
	private GameScreenView gameScreenView;
	private StatsModel statsModel;
	private StatsController statsController;


	private FXMLLoader loader2;
	private BoardView boardView2;
	
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
		
		Parent root = this.loader.load();

		this.boardModel = new BoardModel();
		this.scoreModel = new ScoreModel();
		this.gameScreenModel = new GameScreenModel();
		this.boardView = new BoardView(root);
		this.scoreView = new ScoreView(root);
		this.gameScreenView = new GameScreenView(root);
		this.statsModel = new StatsModel();

		this.boardController = new BoardController();
		//this.statsController = new StatsController();

		this.boardModel.addObserver(boardView);
		this.scoreModel.addObserver(scoreView);
		this.gameScreenModel.addObserver(gameScreenView);
		this.boardController.addBoardModel(boardModel);
		this.boardController.addScoreModel(scoreModel);
		this.boardController.addGameScreenModel(gameScreenModel);
		this.boardView.addCotroller(boardController);
		
		/**
		 * 
		// ADD A SECOND VIEW
		this.loader2= new FXMLLoader(
				getClass().getResource("/view/GameView.fxml"));
		Parent root2 = this.loader2.load();
		this.boardView2 = new BoardView(root2);
		this.boardModel.addObserver(boardView2);
		this.boardView2.addCotroller(boardController);
		**/
		
		this.boardController.init();
	}

	/**
	 * Main entry point, root of all evil.
	 * @param args Program arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
