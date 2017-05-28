package controller;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import generic.GameLogic;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import model.BoardModel;
import model.GameModel;
import model.GameScreenModel;
import model.ScoreModel;
import model.StatsModel;

/**
 * TODO
 */
public class BoardController implements EventHandler<Event> {
	private ArrayList<GameModel> modelList = new ArrayList<GameModel>();

	/**
	 * TODO
	 */
	public BoardController() {
	}

	/**
	 * Initializes the board on the Model to start a new Game
	 */
	public void init() {
		GameLogic.setGameOver(false);
		GameLogic.setGameWin(false);
		for (GameModel gameModel : modelList) {
			gameModel.initializeModel();
		}
	}

	/**
	 * TODO
	 * 
	 * @param boardModel
	 */
	public void addModel(GameModel model) {
		this.modelList.add(model);
	}

	/**
	 * Handle the arrow keys and the buttons of the View. It connects the View
	 * with the Model.
	 */
	@Override
	public void handle(Event event) {
		Integer[][] board = null;
		int score = 0;
		for (GameModel gameModel : modelList) {
			if (gameModel instanceof BoardModel) {
				board = ((BoardModel) gameModel).getModel();
			}
			if (gameModel instanceof ScoreModel) {
				score = ((ScoreModel) gameModel).getScore();
			}
		}
		if (event.getEventType() == KeyEvent.KEY_PRESSED) {
			switch (((KeyEvent) event).getCode()) {
			case UP:
				performMoveUp(board, score);
				break;
			case DOWN:
				performMoveDown(board, score);
				break;
			case LEFT:
				performMoveLeft(board, score);
				break;
			case RIGHT:
				performMoveRight(board, score);
				break;
			default:
				break;
			}
		}

		if (event.getEventType() == ActionEvent.ACTION) {
			String target = event.getTarget().toString();
			if (target.contains("UP")) {
				performMoveUp(board, score);
			} else if (target.contains("DOWN")) {
				performMoveDown(board, score);
			} else if (target.contains("LEFT")) {
				performMoveLeft(board, score);
			} else if (target.contains("RIGHT")) {
				performMoveRight(board, score);
			} else if (target.contains("START") || target.contains("RESTART")) {
				this.init();
			} else if (target.contains("KI")) {
				for (GameModel gameModel : this.modelList) {
					gameModel.set(true);
				}
			} else if (target.contains("RANDOM")) {
				performRandomKiGame();
			} else if (target.contains("GREEDY")) {
				performGreedyKiGame();
			} else if (target.contains("TRUMP")) {
				performTrumpKiGame();
			}
		}

		if (GameLogic.getGameOver()) {
			for (GameModel gameModel : this.modelList) {
				if (gameModel instanceof BoardModel) {
					((BoardModel) gameModel).resetModel();
				}
				gameModel.set(false, true);
			}
			if (GameLogic.getGameWin()) {
				for (GameModel gameModel : this.modelList) {
					gameModel.set(false, true);
				}
			}
			for (GameModel gameModel : this.modelList) {
				if (gameModel instanceof GameScreenModel) {
					((GameScreenModel) gameModel).state();
				}
			}
			try {
				generateXML();
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
	}

	private void performMoveUp(Integer[][] board, int score) {
		if (boardIsSet(board)) {
			board = GameLogic.moveUp(board, score);
			for (GameModel gameModel : this.modelList) {
				gameModel.set(board);
				gameModel.set(GameLogic.getScore());
			}
		}
	}

	private void performMoveDown(Integer[][] board, int score) {
		if (boardIsSet(board)) {
			board = GameLogic.moveDown(board, score);
			for (GameModel gameModel : this.modelList) {
				gameModel.set(board);
				gameModel.set(GameLogic.getScore());
			}
		}
	}

	private void performMoveLeft(Integer[][] board, int score) {
		if (boardIsSet(board)) {
			board = GameLogic.moveLeft(board, score);
			for (GameModel gameModel : this.modelList) {
				gameModel.set(board);
				gameModel.set(GameLogic.getScore());
			}
		}
	}

	private void performMoveRight(Integer[][] board, int score) {
		if (boardIsSet(board)) {
			board = GameLogic.moveRight(board, score);
			for (GameModel gameModel : modelList) {
				gameModel.set(board);
				gameModel.set(GameLogic.getScore());
			}
		}
	}

	private void generateXML() throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(StatsModel.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		for (GameModel gameModel : this.modelList) {
			if (gameModel instanceof StatsModel) {
				marshaller.marshal((StatsModel) gameModel,
						new File(((StatsModel) gameModel).getPath(),
								((StatsModel) gameModel).getFile()));
			}
		}
	}

	private boolean boardIsSet(Integer[][] board) {
		for (Integer[] integers : board) {
			for (Integer integer : integers) {
				if (integer == null) {
					return false;
				}
			}
		}
		return true;
	}

	private void performRandomKiGame() {
		for (GameModel gameModel : this.modelList) {
			gameModel.set(false);
		}

	}

	private void performTrumpKiGame() {
		System.out.println("TRUMP");
		for (GameModel gameModel : this.modelList) {
			gameModel.set(false);
		}
	}

	private void performGreedyKiGame() {
		System.out.println("GREEDY");
		for (GameModel gameModel : this.modelList) {
			gameModel.set(false);
		}
	}
}
