package controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import model.Data;
import view.Board;
import view.NumberBox;

public class MainController {
	
	private static Data data;
	
	@FXML
	GridPane board;
	
	@FXML
	Button startButton;
	
	@FXML
	private void startGame() {
		System.out.println("Start");
		for (int i = 0; i < 2; i++) {
			int row = MainController.data.getRandomRow();
			int col = MainController.data.getRandomCol();
			int[][] board = MainController.data.getBoard();
			if (checkBoardisEmpty(board, col, row)) {
				board[col][row] = Data.INIT_FIELD_SCORE;
				this.board.add(new NumberBox(Data.INIT_FIELD_SCORE), col, row);
			} else {
				data.resetBoard();
				startGame();
			}
		}
		this.startButton.setDisable(true);		
	}

	
	private boolean checkBoardisEmpty(int[][] board, int col, int row) {
		return board[col][row] == 0;
	}

	/**
	 * Starts a new game by launch the JavaFx Board of the game.
	 * Gets the data class form the model where the game hosts its data.
	 * @param data
	 */
	public void startNewGame(Data data) {
		setGame(data);
		Application.launch(Board.class);
		
	}


	private void setGame(Data data) {
		MainController.data = data;
		
	}
}
