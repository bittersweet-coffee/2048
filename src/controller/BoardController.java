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
			
	private void actionPerformed(String event) {
		switch (event) {
		case "Top":
			this.boardModel.moveTop();
			break;
		case "Down":
			this.boardModel.moveDown();
			break;
		case "Left":
			this.boardModel.moveLeft();
			break;
		case "Right":
			this.boardModel.moveRight();
			break;
		default:
			break;
		}
		performScoreUpdate();

	}

	private void performScoreUpdate() {
		if (this.boardModel.getScoreFlag()) {
			Integer score = this.boardModel.getScore();
			this.boardView.updateScore(score);
		}
		this.boardModel.setScoreFlag(false);
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
		if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
			actionPerformed(parseKeyEvent((KeyEvent) event));
		}
		if (event.getEventType().equals(ActionEvent.ACTION)) {
			String str = event.getSource().toString();
			actionPerformed(
					str.substring(str.indexOf("\'") + 1, str.length() - 1));
		
		}
	}

	private String parseKeyEvent(KeyEvent key) {
		String str_parsed = "";
		switch (key.getCode()) {
		case UP:
			str_parsed = "Top";
			break;
		case DOWN:
			str_parsed = "Down";
			break;
		case LEFT:
			str_parsed = "Left";
			break;
		case RIGHT:
			str_parsed = "Right";
			break;
		default:
			break;
		}
		return str_parsed;
	}

}
