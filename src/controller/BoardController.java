package controller;

import generic.GameLogic;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import model.BoardModel;
import model.GameScreenModel;
import model.ScoreModel;

/**
 * TODO
 */
public class BoardController implements EventHandler<Event> {
	private BoardModel boardModel;
	private ScoreModel scoreModel;
	private GameScreenModel gameScreenModel;

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
		this.boardModel.initModel();
		this.scoreModel.initScore();
		this.gameScreenModel.initGameScreen();
	}

	/**
	 * 
	 * @param boardModel
	 */
	public void addBoardModel(BoardModel boardModel) {
		this.boardModel = boardModel;
	}

	/**
	 * 
	 * @param scoreModel
	 */
	public void addScoreModel(ScoreModel scoreModel) {
		this.scoreModel = scoreModel;

	}

	/**
	 * 
	 * @param gameScreenModel
	 */
	public void addGameScreenModel(GameScreenModel gameScreenModel) {
		this.gameScreenModel = gameScreenModel;

	}

	/**
	 * this methode is used to handle the arrow keys and the buttons from the
	 * view. It connects the View with the Modle
	 */
	@Override
	public void handle(Event event) {
		Integer[][] board = this.boardModel.getModel();
		int score = this.scoreModel.getScore();
		if (event.getEventType() == KeyEvent.KEY_PRESSED) {
			switch (((KeyEvent) event).getCode()) {
			case UP:
				this.boardModel.setModel(GameLogic.moveUp(board, score));
				this.scoreModel.setScore(GameLogic.getScore());
				break;
			case DOWN:
				this.boardModel.setModel(GameLogic.moveDown(board, score));
				this.scoreModel.setScore(GameLogic.getScore());
				break;
			case LEFT:
				this.boardModel.setModel(GameLogic.moveLeft(board, score));
				this.scoreModel.setScore(GameLogic.getScore());
				break;
			case RIGHT:
				this.boardModel.setModel(GameLogic.moveRight(board, score));
				this.scoreModel.setScore(GameLogic.getScore());
				break;
			default:
				break;
			}
		}

		if (event.getEventType() == ActionEvent.ACTION) {
			String target = event.getTarget().toString();
			if (target.contains("UP")) {
				this.boardModel.setModel(GameLogic.moveUp(board, score));
				if (GameLogic.getScore() != 0) {
					this.scoreModel.setScore(GameLogic.getScore());
				}
			} else if (target.contains("DOWN")) {
				this.boardModel.setModel(GameLogic.moveDown(board, score));
				if (GameLogic.getScore() != 0) {
					this.scoreModel.setScore(GameLogic.getScore());
				}
			} else if (target.contains("LEFT")) {
				this.boardModel.setModel(GameLogic.moveLeft(board, score));
				if (GameLogic.getScore() != 0) {
					this.scoreModel.setScore(GameLogic.getScore());
				}
			} else if (target.contains("RIGHT")) {
				this.boardModel.setModel(GameLogic.moveRight(board, score));
				if (GameLogic.getScore() != 0) {
					this.scoreModel.setScore(GameLogic.getScore());
				}
			} else if (target.contains("START") || target.contains("RESTART")) {
				this.init();
			}
		}
		if (GameLogic.getGameOver()) {
			this.boardModel.resetModel();
			this.gameScreenModel.setGameOver(true);
			if (GameLogic.getGameWin()) {
				this.gameScreenModel.setGameWin(true);
			}
			this.gameScreenModel.state();
		}
	}
}
