package controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Data;
import model.Field;
import view.Board;
import view.NumberBox;

public class MainController {
	
	private static Data data;
	
	@FXML
	GridPane board;
	
	@FXML
	Button startButton;
	
	@FXML
	Button topButton;
	
	@FXML
	Button leftButton; 
	
	@FXML
	Button downButton; 
	
	@FXML
	Button rightButton;
	
	@FXML
	public void initialize() {
		startGame();
	}
	
	@FXML
	private void startGame() {
		Field[][] board = MainController.data.getBoard();
		initBoardView(this.board, board);
		addBox(board, Data.INIT_FIELD_AMOUNT);
		this.startButton.setDisable(true);
		updateBoardView();
	}
	
	@FXML
	public void moveRight() {
		Field[][] board = MainController.data.getBoard();
		int move = performMoveRight(board);
		int merge = performMergeRight(board);
		performAdd(board, move, merge);
		if (checkWin(board)) {
			done();
		}
	}

	@FXML
	public void moveLeft() {
		Field[][] board = MainController.data.getBoard();
		int move = performMoveLeft(board);
		int merge = performMergeLeft(board);
		performAdd(board, move, merge);
		if (checkWin(board)) {
			done();
		}
	}
	
	@FXML
	public void moveTop() {
		Field[][] board = MainController.data.getBoard();
		int move = performMoveTop(board);
		int merge = performMergeTop(board);
		performAdd(board, move, merge);
		if (checkWin(board)) {
			done();
		}
	}
	
	@FXML
	public void moveDown() {
		Field[][] board = MainController.data.getBoard();
		int move = performMoveDown(board);
		int merge = performMergeDown(board);
		performAdd(board, move, merge);
		if (checkWin(board)) {
			done();
		}
	}
	
	@FXML
	public void handleArrowKey(KeyEvent key) {
		switch (key.getCode()) {
		case UP:
			moveTop();
			break;
		case DOWN:
			moveDown();
			break;
		case LEFT:
			moveLeft();
			break;
		case RIGHT:
			moveRight();
			break;
		default:
			break;
		}
	}
	
	private void updateBoardView() {
		Field[][] board = MainController.data.getBoard();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				for (Node node : this.board.getChildren()) {
					if (node instanceof HBox && GridPane.getRowIndex(node) == i && GridPane.getColumnIndex(node) == j) {
						if (board[i][j].getValue() != 0) {
							((NumberBox)node).setLabelText(Integer.toString(board[i][j].getValue()));
						} else {
							((NumberBox)node).setLabelText("");
						}
						
					}
				}
			}
		}
	}


	private void initBoardView(GridPane gridBoard, Field[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				gridBoard.add(new NumberBox(), i, j);
			}
		}
		
	}
	
	private void addBox(Field[][] board, int amount) {
		int counter = 0;
		while (counter != amount) {
			int row = MainController.data.getRandomRow();
			int col = MainController.data.getRandomCol();
			int fieldValue = MainController.data.getRandomValue();
			if (!board[row][col].isOccupied()) {
				board[row][col].setValue(fieldValue);
				MainController.data.setBoard(board);
				counter++;
			}
			
			
		}
		updateBoardView();
	}


	private int performMergeRight(Field[][] board) {
		int merge = 0;
		for (int row = board.length-1; row >= 0; row--) {
			for (int col = board.length-1; col >= 0; col--) {
				if (board[row][col].isOccupied() && col != 0) {
					if (board[row][col-1].isOccupied() && (board[row][col].getValue() == board[row][col-1].getValue())) {
						merge(board, row, col-1, row, col);
						merge = performMoveRight(board);
						merge++;
					}
				}
			}
		}
		return merge;
	}
	
	private int performMoveRight(Field[][] board) {
		int move = 0;
		for (int row = board.length-1; row >= 0; row--) {
			for (int col = board.length-1; col >= 0; col--) {
				if (board[row][col].isOccupied()) {
					for (int col_move = col+1; col_move < board.length; col_move++) {
						if (board[row][col_move].isOccupied()) {
							if (col != col_move-1) {
								move(board, row, col, row, col_move-1);
								move++;
							}
							col_move = board.length;
						} 
						else if (col_move == board.length-1 && !(board[row][board.length-1].isOccupied())) {
							move(board, row, col, row, board.length-1);
							move++;
						}
					}
				}
			}
		}
		return move;
	}
	
	private int performMergeLeft(Field[][] board) {
		int merge = 0;
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {
				if (board[row][col].isOccupied() && col+1 != board.length) {
					if (board[row][col+1].isOccupied() && (board[row][col].getValue() == board[row][col+1].getValue())) {
						merge(board, row, col+1, row, col);
						performMoveLeft(board);
						merge = performMoveLeft(board);
						merge++;
					}
				}
			}
		}
		return merge;
	}
	
	private int performMoveLeft(Field[][] board) {
		int move = 0;
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {
				if (board[row][col].isOccupied()) {
					for (int col_move = col-1; col_move >= 0; col_move--) {
						if (board[row][col_move].isOccupied()) {
							if (col != col_move+1) {
								move(board, row, col, row, col_move+1);
								move++;
							}
							col_move = -1;
						} else if (col_move == 0 && !(board[row][0].isOccupied())) {
							move(board, row, col, row, 0);
							move++;
						}
					}
				}
			}
		}
		return move;
	}
	
	private int performMergeTop(Field[][] board) {
		int merge = 0;
		for (int col = board.length-1; col >= 0; col--) {
			for (int row = 0; row < board.length; row++) {
				if (board[row][col].isOccupied() && row+1 != board.length) {
					if (board[row+1][col].isOccupied() && (board[row][col].getValue() == board[row+1][col].getValue())) {
						merge(board, row, col, row+1, col);
						merge = performMoveTop(board);
						merge++;
					}
				}
			}
		}
		return merge;
	}
	
	private int performMoveTop(Field[][] board) {
		int move = 0;
		for (int col = board.length-1; col >= 0; col--) {
			for (int row = 0; row < board.length; row++) {
				if (board[row][col].isOccupied()) {
					for (int row_move = row-1; row_move >= 0; row_move--) {
						if (board[row_move][col].isOccupied()) {
							if (row != row_move+1) {
								move(board, row, col, row_move+1, col);
								move++;
							}
							row_move = -1;
						} else if (row_move == 0 && !(board[0][col].isOccupied())) {
							move(board, row, col, 0, col);
							move++;
						}
					}
				}
			}
		}
		return move;
	}
	
	private int performMergeDown(Field[][] board) {
		int merge = 0;
		for (int col = 0; col < board.length; col++) {
			for (int row = board.length-1; row >= 0; row--) {
				if (board[row][col].isOccupied() && row+1 != board.length) {
					if (board[row+1][col].isOccupied() && (board[row][col].getValue() == board[row+1][col].getValue())) {
						merge(board, row, col, row+1, col);
						merge = performMoveDown(board);
						merge++;
					}
				}
			}
		}
		return merge;
	}
	
	private int performMoveDown(Field[][] board) {
		int move = 0;
		for (int col = 0; col < board.length; col++) {
			for (int row = board.length-1; row >= 0; row--) {
				if (board[row][col].isOccupied()) {
					for (int row_move = row+1; row_move < board.length; row_move++) {
						if (board[row_move][col].isOccupied()) {
							if (row != row_move-1) {
								move(board, row, col, row_move-1, col);
								move++;
							}
							row_move = board.length;
						} else if (row_move == board.length-1 && !(board[board.length-1][col].isOccupied())) {
							move(board, row, col, board.length-1, col);
							move++;
						}
					}
				}
			}
		}
		return move;
	}
	
	private void displayASCCII() {
		Field[][] board = MainController.data.getBoard();
		System.out.println("Board:");
		for (Field[] fields : board) {
			System.out.println();
			for (Field field : fields) {
				System.out.print(field.getValue() + " ");
			}
		}
		System.out.println("END");
		System.out.println();
	}

	private void merge(Field[][] board, int row_from, int col_from, int row_to, int col_to) {
		int value = board[row_to][col_to].getValue() + board[row_from][col_from].getValue();
		board[row_to][col_to].setValue(value);
		board[row_from][col_from] = new Field();
		MainController.data.setBoard(board);
		updateBoardView();
	}
	
	private void move(Field[][] board, int row_from, int col_from, int row_to, int col_to) {
		board[row_to][col_to].setValue(board[row_from][col_from].getValue());
		board[row_from][col_from] = new Field();
		MainController.data.setBoard(board);
		updateBoardView();
	}
	
	private void performAdd(Field[][] board, int move, int merge) {
		if (move != 0 || merge != 0) {
			addBox(board, Data.UPDATE_FIELD_AMOUNT);
		}		
	}
	
	private boolean checkWin(Field[][] board) {
		for (Field[] fields : board) {
			for (Field field : fields) {
				if (field.getValue() == Field.getWinFieldValue()) {
					return true;
				}
			}
		}
		return false;
		
	}
	
	private void done() {
		MainController.data.resetBoard();
		updateBoardView();
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
