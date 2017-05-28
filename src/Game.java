
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import controller.BoardController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import model.BoardModel;
import model.GameModel;
import model.GameScreenModel;
import model.KiModel;
import model.ScoreModel;
import model.StatsModel;
import view.GameView;
import view.BoardView;
import view.GameScreenView;
import view.HighScoreView;
import view.KiView;
import view.ScoreView;

/**
 * Provides the Game, initializing all required objects and binding them
 * together.
 */
public class Game extends Application {

	private FXMLLoader loader;

	private BoardController boardController;
	private GameModel boardModel;
	private GameModel scoreModel;
	private GameModel gameScreenModel;
	private GameModel statsModel;
	private GameModel kiModel;
	private GameView boardView;
	private GameView scoreView;
	private GameView highScoreView;
	private GameView gameScreenView;
	private GameView kiView;
	private String path;
	private String file;

	/**private FXMLLoader loader2;
	private GameView boardView2;
	private GameView scoreView2;
	private GameView highScoreView2;
	private GameView gameScreenView2;**/

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
		this.path = System.getProperty("user.dir") + "\\src\\model";
		this.file = "\\stats.xml";
		this.loader = new FXMLLoader(
				getClass().getResource("/view/GameView.fxml"));

		Parent root = this.loader.load();

		this.boardView = new BoardView(root);
		this.scoreView = new ScoreView(root);
		this.highScoreView = new HighScoreView(root);
		this.gameScreenView = new GameScreenView(root);
		this.kiView = new KiView(root);

		this.boardModel = new BoardModel();
		this.scoreModel = new ScoreModel();
		this.statsModel = setStatsModel(path, file);
		this.gameScreenModel = new GameScreenModel();
		this.kiModel = new KiModel();

		this.boardModel.addObserver(boardView);
		this.scoreModel.addObserver(scoreView);
		this.statsModel.addObserver(highScoreView);
		this.gameScreenModel.addObserver(gameScreenView);
		this.kiModel.addObserver(kiView);

		this.scoreView.initializeView();
		this.gameScreenView.initializeView();
		this.highScoreView.initializeView();
		this.boardView.initializeView();
		this.kiView.initializeView();

		this.boardController = new BoardController();

		this.boardController.addModel(boardModel);
		this.boardController.addModel(scoreModel);
		this.boardController.addModel(gameScreenModel);
		this.boardController.addModel(statsModel);
		this.boardController.addModel(kiModel);
		((BoardView) this.boardView).addCotroller(boardController);
		((KiView) this.kiView).addController(boardController);

		
		/**
		 // ADD A SECOND VIEW 
		this.loader2 = new FXMLLoader(
				getClass().getResource("/view/GameView.fxml")); Parent root2 =
		 this.loader2.load(); this.boardView2 = new BoardView(root2);
		 this.scoreView2 = new ScoreView(root2); this.highScoreView2 = new
		 HighScoreView(root2); this.gameScreenView2 = new
		 GameScreenView(root2);
		 
		 this.scoreView2.initializeView();
		 this.gameScreenView2.initializeView();
		 this.highScoreView2.initializeView();
		 this.boardView2.initializeView();
		 
		 this.boardModel.addObserver(boardView2);
		this.scoreModel.addObserver(scoreView2);
		 this.statsModel.addObserver(highScoreView2);
		 this.gameScreenModel.addObserver(gameScreenView2); ((BoardView)
		 this.boardView2).addCotroller(boardController);**/
		
		// this.boardController.init();
	}

	private StatsModel setStatsModel(String path, String file) {
		StatsModel statsModel = null;
		try {
			File fileCheck = new File(path, file);

			if (fileCheck.exists()) {
				JAXBContext context = JAXBContext.newInstance(StatsModel.class);
				Unmarshaller unMarshaller = context.createUnmarshaller();
				statsModel = (StatsModel) unMarshaller
						.unmarshal(new FileReader(path + file));
				statsModel.set(statsModel.getCurrentHighscore());
			} else {
				statsModel = new StatsModel(path, file);
				statsModel.set(0);
				statsModel.setName("Jan");
			}
		} catch (JAXBException e) {
			System.out.println("JAXBContext could not load Model.");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage() + "File does not exist!");
			e.printStackTrace();
		}
		return statsModel;

	}

	/**
	 * Main entry point, root of all evil.
	 * 
	 * @param args
	 *            Program arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
