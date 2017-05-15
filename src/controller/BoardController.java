package controller;

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
	 * TODO
	 */
	public void initModel() {
		this.boardModel.initModel();
	}

	/**
	 * TODO
	 * @param boardModel
	 */
	public void addBoardModel(BoardModel boardModel) {
		this.boardModel = boardModel;
	}
	
	/**
	 * TODO
	 * @param boardView
	 */
	public void addBoardView(BoardView boardView) {
		this.boardView = boardView;
	}

	/**
	 * TODO
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
