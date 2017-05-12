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

	/**
	 * 
	 */
	private BoardModel boardModel;
	private BoardView boardView;
	
	/**
	 * TODO
	 */
	public BoardController() {
		
	}

	public void actionPerformed(KeyEvent key) {
		switch (key.getCode()) {
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
	
	
	public void actionTop(ActionEvent event) {
		this.boardModel.moveTop();
	}

	public void actionLeft(ActionEvent event) {
		this.boardModel.moveLeft();
	}

	public void actionDown(ActionEvent event) {
		this.boardModel.moveDown();
	}

	public void actionRight(ActionEvent event) {
		this.boardModel.moveRight();
	}
	
	public void addBoardModel(BoardModel boardModel) {
		this.boardModel = boardModel;
	}
	
	public void addBoardView(BoardView boardView) {
		this.boardView = boardView;
	}

	public void initModel() {
		this.boardModel.initModel();
		
	}

	@Override
	public void handle(Event event) {
		System.out.println(event.getEventType().equals(KeyEvent.KEY_PRESSED));
	}
	
}
