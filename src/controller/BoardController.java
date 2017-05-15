package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import model.BoardModel;
import view.BoardView;

/**
 * 
 * @author Henzi & Hofer This controller handels all stuff from the 2048 Game
 */
public class BoardController implements EventHandler<Event> {
	private BoardModel boardModel;
	private BoardView boardView;

	/**
	 * TODO
	 */
	public BoardController() {
	}

	/**
	 * Initializes the board on the Model to start a new Game
	 */
	public void initModel() {
		this.boardModel.initModel();
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
	 * @param boardView
	 */
	public void addBoardView(BoardView boardView) {
		this.boardView = boardView;
	}

	/**
	 * this methode is used to handle the arrow keys and the buttons from the
	 * view. It connects the View with the Modle
	 */
	@Override
	public void handle(Event event) {
		if (event.getEventType() == KeyEvent.KEY_PRESSED) {
			switch (((KeyEvent) event).getCode()) {
			case UP:
				this.boardModel.moveTop();
				break;
			case DOWN:
				this.boardModel.moveDown();
				break;
			case LEFT:
				this.boardModel.moveLeft();
				break;
			case RIGHT:
				this.boardModel.moveRight();
				break;
			default:
				break;
			}
		}

		if (event.getEventType() == ActionEvent.ACTION) {
			String target = event.getTarget().toString();
			if (target.contains("UP")) {
				this.boardModel.moveTop();
			} else if (target.contains("DOWN")) {
				this.boardModel.moveDown();
			} else if (target.contains("LEFT")) {
				this.boardModel.moveLeft();
			} else if (target.contains("RIGHT")) {
				this.boardModel.moveRight();
			} else if (target.contains("START")) {
				this.initModel();
			}
		}
	}
}
