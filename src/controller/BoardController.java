package controller;

import generic.GameLogic;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import model.BoardModel;
import view.BoardView;

/**
 * TODO
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
				this.boardModel
						.setModel(GameLogic.moveUp(this.boardModel.getModel()));
				break;
			case DOWN:
				this.boardModel.setModel(
						GameLogic.moveDown(this.boardModel.getModel()));
				break;
			case LEFT:
				this.boardModel.setModel(
						GameLogic.moveLeft(this.boardModel.getModel()));
				break;
			case RIGHT:
				this.boardModel.setModel(
						GameLogic.moveRight(this.boardModel.getModel()));
				break;
			default:
				break;
			}
		}

		if (event.getEventType() == ActionEvent.ACTION) {
			String target = event.getTarget().toString();
			if (target.contains("UP")) {
				this.boardModel
						.setModel(GameLogic.moveUp(this.boardModel.getModel()));
			} else if (target.contains("DOWN")) {
				this.boardModel.setModel(
						GameLogic.moveDown(this.boardModel.getModel()));
			} else if (target.contains("LEFT")) {
				this.boardModel.setModel(
						GameLogic.moveLeft(this.boardModel.getModel()));
			} else if (target.contains("RIGHT")) {
				this.boardModel.setModel(
						GameLogic.moveRight(this.boardModel.getModel()));
			} else if (target.contains("START")) {
				this.initModel();
			}
		}
	}
}
