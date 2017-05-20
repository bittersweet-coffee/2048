package controller;

import generic.GameLogic;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import model.BoardModel;
import model.ScoreModel;
import view.BoardView;

/**
 * TODO
 */
public class BoardController implements EventHandler<Event> {
	private BoardModel boardModel;
	private ScoreModel scoreModel;

	/**
	 * TODO
	 */
	public BoardController() {
	}

	/**
	 * Initializes the board on the Model to start a new Game
	 */
	public void init() {
		this.boardModel.initModel();
		this.scoreModel.initScore();
	}

	/**
	 * 
	 * @param boardModel
	 */
	public void addBoardModel(BoardModel boardModel) {
		this.boardModel = boardModel;
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

		if (event.getEventType() == ActionEvent.ACTION)

		{
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
			} else if (target.contains("START")) {
				this.init();
			}
		}
	}

	public void addScoreModel(ScoreModel scoreModel) {
		this.scoreModel = scoreModel;

	}
}
