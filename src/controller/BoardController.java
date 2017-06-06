package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

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
import model.KiModel;
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
		Integer score = 0;
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
					if (gameModel instanceof KiModel) {
						((KiModel) gameModel).set(true);
					}
				}
			} else if (target.contains("RANDOM")) {
				performRandomKiGame(board, score);
			} else if (target.contains("GREEDY")) {
				performGreedyKiGame();
			} else if (target.contains("PLAYER")) {
				for (GameModel gameModel : this.modelList) {
					if (gameModel instanceof StatsModel) {
						((StatsModel) gameModel).set(true);
					}
				}
			} else if (target.contains("ABORT")) {
				for (GameModel gameModel : this.modelList) {
					if (gameModel instanceof StatsModel) {
						gameModel.set(true, false);
						gameModel.set(false);
					}
				}
			} else if (target.contains("SAVE")) {
				for (GameModel gameModel : this.modelList) {
					if (gameModel instanceof StatsModel) {
						gameModel.set(false, true);
						gameModel.set(false);
					}
				}
			}
		}

		performGameOver(GameLogic.getGameOver());
	}

	private void performMoveUp(Integer[][] board, Integer score) {
		if (boardIsSet(board)) {
			board = GameLogic.moveUp(board, score);
			for (GameModel gameModel : this.modelList) {
				gameModel.set(board);
				gameModel.set(GameLogic.getScore());
			}
		}
	}

	private void performMoveDown(Integer[][] board, Integer score) {
		if (boardIsSet(board)) {
			board = GameLogic.moveDown(board, score);
			for (GameModel gameModel : this.modelList) {
				gameModel.set(board);
				gameModel.set(GameLogic.getScore());
			}
		}
	}

	private void performMoveLeft(Integer[][] board, Integer score) {
		if (boardIsSet(board)) {
			board = GameLogic.moveLeft(board, score);
			for (GameModel gameModel : this.modelList) {
				gameModel.set(board);
				gameModel.set(GameLogic.getScore());
			}
		}
	}

	private void performMoveRight(Integer[][] board, Integer score) {
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
						new File(((StatsModel) gameModel).getPath()));
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

	private void performGameOver(boolean gameOver) {
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

	private void performRandomKiGame(Integer[][] board, Integer score) {
		if (boardIsSet(board)) {
			for (GameModel gameModel : this.modelList) {
				if (gameModel instanceof StatsModel) {
					((StatsModel) gameModel).setName("RANDOM-KI");
				}
				if (gameModel instanceof KiModel) {
					((KiModel) gameModel).set(false);
				}
			}
			Random random = new Random();
			if (boardIsSet(board)) {
				while (!GameLogic.getGameOver()) {
					for (GameModel gameModel : modelList) {
						if (gameModel instanceof BoardModel) {
							board = ((BoardModel) gameModel).getModel();
						}
						if (gameModel instanceof ScoreModel) {
							score = ((ScoreModel) gameModel).getScore();
						}
					}
					int value = random.nextInt(4);
					if (value == 0) {
						performMoveUp(board, score);
					} else if (value == 1) {
						performMoveDown(board, score);
					} else if (value == 2) {
						performMoveLeft(board, score);
					} else if (value == 3) {
						performMoveRight(board, score);
					}
				}
			}
		}
	}

	private void performGreedyKiGame() {
		for (GameModel gameModel : this.modelList) {
			gameModel.set(false);
		}
		ScoreModel scoreModel = null;
		StatsModel statsModel = null;
		Integer[][] tmpBoard = null;
		int tmpScore = 0;
		for (GameModel gameModel : modelList) {
			if (gameModel instanceof BoardModel) {
				tmpBoard = createBoradCopy(((BoardModel) gameModel).getModel());
			}
			if (gameModel instanceof ScoreModel) {
				scoreModel = (ScoreModel) gameModel;
				if (scoreModel.getScore() == null) {
					tmpScore = 0;
				} else {
					tmpScore = ((ScoreModel) gameModel).getScore();
				}
			}
			if (gameModel instanceof StatsModel) {
				((StatsModel) gameModel).setName("GREEDY-KI");
				statsModel = (StatsModel) gameModel;
			}
			if (gameModel instanceof KiModel) {
				((KiModel) gameModel).set(false);
			}
		}
		if (boardIsSet(tmpBoard)) {
			while (!GameLogic.getGameOver()) {
				performKIMove(tmpBoard, tmpScore, GameLogic.getGameOver(),
						scoreModel, statsModel);
			}
		}
	}

	private Integer[][] performKIMove(Integer[][] board, Integer score,
			boolean gameOver, ScoreModel scoreModel, StatsModel statsModel) {
		if (gameOver) {
			GameLogic.setGameOver(gameOver);
			scoreModel.set(score);
			statsModel.set(score);
			return board;
		}
		Integer[][] tmpBoardUp = createBoradCopy(board);
		Integer[][] tmpBoardLeft = createBoradCopy(board);
		Integer[][] tmpBoardRight = createBoradCopy(board);
		Integer[][] tmpBoardDown = createBoradCopy(board);
		Integer tmpScoreUp = score;
		Integer tmpScoreLeft = score;
		Integer tmpScoreRight = score;
		Integer tmpScoreDown = score;
		boolean tmpGameOverUp = gameOver;
		boolean tmpGameOverLeft = gameOver;
		boolean tmpGameOverRight = gameOver;
		boolean tmpGameOverDown = gameOver;

		tmpBoardUp = GameLogic.moveUp(tmpBoardUp, tmpScoreUp);
		tmpScoreUp = GameLogic.getScore();
		tmpGameOverUp = GameLogic.getGameOver();

		if (tmpScoreUp > score) {
			return performKIMove(tmpBoardUp, tmpScoreUp, tmpGameOverUp,
					scoreModel, statsModel);
		}

		tmpBoardLeft = GameLogic.moveLeft(tmpBoardLeft, tmpScoreLeft);
		tmpScoreLeft = GameLogic.getScore();
		tmpGameOverLeft = GameLogic.getGameOver();

		if (tmpScoreLeft > score) {
			return performKIMove(tmpBoardLeft, tmpScoreLeft, tmpGameOverLeft,
					scoreModel, statsModel);
		}

		tmpBoardRight = GameLogic.moveRight(tmpBoardRight, tmpScoreRight);
		tmpScoreRight = GameLogic.getScore();
		tmpGameOverRight = GameLogic.getGameOver();

		if (tmpScoreRight > score) {
			return performKIMove(tmpBoardRight, tmpScoreRight, tmpGameOverRight,
					scoreModel, statsModel);
		}
		tmpBoardDown = GameLogic.moveDown(tmpBoardDown, tmpScoreDown);
		tmpScoreDown = GameLogic.getScore();
		tmpGameOverDown = GameLogic.getGameOver();
		if (tmpScoreDown > score) {
			return performKIMove(tmpBoardDown, tmpScoreDown, tmpGameOverDown,
					scoreModel, statsModel);
		}
		Random random = new Random();
		int value = random.nextInt(40);
		if (value <= 17) {
			return performKIMove(tmpBoardUp, score, tmpGameOverUp, scoreModel,
					statsModel);
		} else if (value > 17 && value <= 27) {
			return performKIMove(tmpBoardLeft, score, tmpGameOverLeft,
					scoreModel, statsModel);
		} else if (value > 27 && value <= 37) {
			return performKIMove(tmpBoardRight, score, tmpGameOverRight,
					scoreModel, statsModel);
		} else if (value == 40 || value == 39 || value == 38) {
			return performKIMove(tmpBoardDown, score, tmpGameOverDown,
					scoreModel, statsModel);
		}
		return null;

	}

	private Integer[][] createBoradCopy(Integer[][] board) {
		Integer[][] boardCopy = new Integer[GameLogic.ROW][GameLogic.COL];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				boardCopy[i][j] = board[i][j];
			}
		}
		return boardCopy;
	}
}
