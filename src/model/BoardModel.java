package model;

import java.util.Observable;
import java.util.Random;

/**
 * Provides a Data object, which stores all necessary game data and states. This
 * class does not keep track of the game score.
 */
public final class BoardModel extends Observable {

	public static final int ROW = 4;
	public static final int COL = 4;
	public static final int INIT_FIELD_AMOUNT = 2;
	public static final int UPDATE_FIELD_AMOUNT = 1;
	public static final int INIT_FIELD_VALUE = 0;
	public static final int INIT_SCORE_VALUE = 0;
	public static final int WIN_VALUE = 2048;

	private static final int RAND_RATIO_MAX = 50;
	private static final int BIG_FIELD_VALUE = 4;
	private static final int SMALL_FIELS_VALUE = 2;
	private Boolean gameOver = false;
	private Integer score;

	private Integer[][] boardModel = new Integer[ROW][COL];
	private Random random;
	private boolean gameWin;

	/**
	 * Initialize the initial Data object and state.
	 */
	public BoardModel() {
		this.random = new Random();
	}

	/**
	 * TODO
	 */
	public void resetBoardModel() {
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				this.boardModel[i][j] = INIT_FIELD_VALUE;
			}
		}
		setChanged();
		notifyObservers(this);
	}

	/**
	 * TODO
	 */
	public void setBoardModel(Integer[][] boardModel) {
		this.boardModel = boardModel;

		setChanged();
		notifyObservers(this);
	}

	/**
	 * Returns a random integer (default <= 0 && >= ROW). Used to determine the
	 * next row to place a new number.
	 * 
	 * @return random integer between 0 and ROW
	 */
	public int getRandomRow() {
		return this.random.nextInt(ROW);
	}

	/**
	 * Returns a random integer (default <= 0 && >= COL). Used to determine the
	 * next column to place a new number.
	 * 
	 * @return random integer between 0 and COL
	 */
	public int getRandomCol() {
		return this.random.nextInt(COL);
	}

	/**
	 * Returns a random int (dafault 2 || 4). Used to set the values on the
	 * Board when game starts and move is performed.
	 */
	private int getRandomValue() {
		if (this.random.nextInt(RAND_RATIO_MAX) <= 10) {
			return BIG_FIELD_VALUE;
		} else {
			return SMALL_FIELS_VALUE;
		}
	}

	/**
	 * Determines if a certain field on the board is occupied (returns true is
	 * it's occupied and false otherwise.
	 * 
	 * @param row
	 * @param col
	 * @return true if row - col is occupied
	 */
	private boolean isOccupied(int row, int col) {
		if (this.boardModel[row][col].equals(INIT_FIELD_VALUE)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Adds a certain amount of new fields to a possible place on the board.
	 * 
	 * @param amount
	 */
	private void addValue(int amount) {
		int counter = 0;
		while (counter != amount) {
			int row = getRandomRow();
			int col = getRandomCol();
			int fieldValue = getRandomValue();
			if (!isOccupied(row, col)) {
				this.boardModel[row][col] = fieldValue;
				counter++;
			}

		}
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Checks if there are possible fields on the board which can be merged. If
	 * a merge is possible a further move has to be done.
	 * 
	 * @return int > 0 if merge was performed
	 */
	public int performMergeRight() {
		int merge = 0;
		for (int row = this.boardModel.length - 1; row >= 0; row--) {
			for (int col = this.boardModel.length - 1; col >= 0; col--) {
				if (isOccupied(row, col) && col != 0) {
					if (isOccupied(row, col - 1) && (boardModel[row][col]
							.equals(boardModel[row][col - 1]))) {
						merge(boardModel, row, col - 1, row, col);
						merge = performMoveRight();
						merge++;
					}
				}
			}
		}
		return merge;
	}

	/**
	 * Checks if there are possible fields on the board which can be moved.
	 * 
	 * @return int > 0 if move was performed
	 */
	public int performMoveRight() {
		if (!checkMovePossibility()) {
			lose();
		}
		int move = 0;
		for (int row = this.boardModel.length - 1; row >= 0; row--) {
			for (int col = this.boardModel.length - 1; col >= 0; col--) {
				if (isOccupied(row, col)) {
					for (int col_move = col
							+ 1; col_move < this.boardModel.length; col_move++) {
						if (isOccupied(row, col_move)) {
							if (col != col_move - 1) {
								move(this.boardModel, row, col, row,
										col_move - 1);
								move++;
							}
							col_move = this.boardModel.length;
						} else if (col_move == this.boardModel.length - 1
								&& !isOccupied(row,
										this.boardModel.length - 1)) {
							move(this.boardModel, row, col, row,
									this.boardModel.length - 1);
							move++;
						}
					}
				}
			}
		}
		return move;
	}

	private void lose() {
		setGameOver(true);
		resetBoardModel();
	}

	public int performMergeLeft() {
		int merge = 0;
		for (int row = 0; row < this.boardModel.length; row++) {
			for (int col = 0; col < this.boardModel.length; col++) {
				if (isOccupied(row, col) && col + 1 != this.boardModel.length) {
					if (isOccupied(row, col + 1) && (this.boardModel[row][col]
							.equals(this.boardModel[row][col + 1]))) {
						merge(this.boardModel, row, col + 1, row, col);
						merge = performMoveLeft();
						merge++;
					}
				}
			}
		}
		return merge;
	}

	public int performMoveLeft() {
		if (!checkMovePossibility()) {
			lose();
		}
		int move = 0;
		for (int row = 0; row < this.boardModel.length; row++) {
			for (int col = 0; col < this.boardModel.length; col++) {
				if (isOccupied(row, col)) {
					for (int col_move = col - 1; col_move >= 0; col_move--) {
						if (isOccupied(row, col_move)) {
							if (col != col_move + 1) {
								move(this.boardModel, row, col, row,
										col_move + 1);
								move++;
							}
							col_move = -1;
						} else if (col_move == 0 && !isOccupied(row, 0)) {
							move(this.boardModel, row, col, row, 0);
							move++;
						}
					}
				}
			}
		}
		return move;
	}

	public int performMergeTop() {
		int merge = 0;
		for (int col = this.boardModel.length - 1; col >= 0; col--) {
			for (int row = 0; row < this.boardModel.length; row++) {
				if (isOccupied(row, col) && row + 1 != this.boardModel.length) {
					if (isOccupied(row + 1, col) && (this.boardModel[row][col]
							.equals(this.boardModel[row + 1][col]))) {
						merge(this.boardModel, row, col, row + 1, col);
						merge = performMoveTop();
						merge++;
					}
				}
			}
		}
		return merge;
	}

	public int performMoveTop() {
		if (!checkMovePossibility()) {
			lose();
		}
		int move = 0;
		for (int col = this.boardModel.length - 1; col >= 0; col--) {
			for (int row = 0; row < this.boardModel.length; row++) {
				if (isOccupied(row, col)) {
					for (int row_move = row - 1; row_move >= 0; row_move--) {
						if (isOccupied(row_move, col)) {
							if (row != row_move + 1) {
								move(this.boardModel, row, col, row_move + 1,
										col);
								move++;
							}
							row_move = -1;
						} else if (row_move == 0 && !(isOccupied(0, col))) {
							move(this.boardModel, row, col, 0, col);
							move++;
						}
					}
				}
			}
		}
		return move;
	}

	public int performMergeDown() {
		int merge = 0;
		for (int col = 0; col < boardModel.length; col++) {
			for (int row = boardModel.length - 1; row >= 0; row--) {
				if (isOccupied(row, col) && row + 1 != boardModel.length) {
					if (isOccupied(row + 1, col) && (boardModel[row][col]
							.equals(boardModel[row + 1][col]))) {
						merge(boardModel, row, col, row + 1, col);
						merge = performMoveDown();
						merge++;
					}
				}
			}
		}
		return merge;
	}

	public int performMoveDown() {
		if (!checkMovePossibility()) {
			lose();
		}
		int move = 0;
		for (int col = 0; col < this.boardModel.length; col++) {
			for (int row = this.boardModel.length - 1; row >= 0; row--) {
				if (isOccupied(row, col)) {
					for (int row_move = row
							+ 1; row_move < this.boardModel.length; row_move++) {
						if (isOccupied(row_move, col)) {
							if (row != row_move - 1) {
								move(this.boardModel, row, col, row_move - 1,
										col);
								move++;
							}
							row_move = this.boardModel.length;
						} else if (row_move == this.boardModel.length - 1
								&& !isOccupied(this.boardModel.length - 1,
										col)) {
							move(this.boardModel, row, col,
									this.boardModel.length - 1, col);
							move++;
						}
					}
				}
			}
		}
		return move;
	}

	/**
	 * merges certain field on the board and checks whether the player has won
	 * the game.
	 * 
	 * @param board
	 * @param row_from
	 * @param col_from
	 * @param row_to
	 * @param col_to
	 */
	private void merge(Integer[][] board, int row_from, int col_from,
			int row_to, int col_to) {
		int value = board[row_to][col_to] + board[row_from][col_from];
		board[row_to][col_to] = value;
		board[row_from][col_from] = INIT_FIELD_VALUE;
		this.setScore(value);
		if (value == WIN_VALUE) {
			win();
		}
		setChanged();
		notifyObservers(this);

	}

	private void setScore(int value) {
		Integer current_score = this.getScore();
		Integer new_score = current_score + value;
		this.score = new_score;
	}

	public int getScore() {
		return this.score;
	}

	private void win() {
		setGameWin(true);
		resetBoardModel();

	}

	/**
	 * moves certain field on the board.
	 * @param board
	 * @param row_from
	 * @param col_from
	 * @param row_to
	 * @param col_to
	 */
	private void move(Integer[][] board, int row_from, int col_from, int row_to,
			int col_to) {
		board[row_to][col_to] = board[row_from][col_from];
		board[row_from][col_from] = INIT_FIELD_VALUE;
		setChanged();
		notifyObservers(this);
	}

	private void performAdd(int move, int merge) {
		if ((move != 0 || merge != 0) && !getOameOver() && !getGameWin()) {
			addValue(BoardModel.UPDATE_FIELD_AMOUNT);
		}
	}

	private boolean checkMovePossibility() {
		for (int row = 0; row < boardModel.length; row++) {
			for (int col = 0; col < boardModel.length; col++) {
				if (row == boardModel.length - 1
						&& col != boardModel.length - 1) {
					if (boardModel[row][col] == boardModel[row][col + 1]) {
						return true;
					}
				}
				if (row != boardModel.length - 1
						&& col == boardModel.length - 1) {
					if (boardModel[row][col] == boardModel[row + 1][col]) {
						return true;
					}
				}
				if (row < boardModel.length - 1
						&& col < boardModel.length - 1) {
					if (boardModel[row][col] == boardModel[row + 1][col]
							|| boardModel[row][col] == boardModel[row][col
									+ 1]) {
						return true;
					}
				}
				if (!isOccupied(row, col)) {
					return true;
				}
			}
		}
		return false;

	}

	/**
	 * Initializes the board and sets game to not won nor lose.
	 */
	public void initModel() {
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				this.boardModel[i][j] = INIT_FIELD_VALUE;
			}
		}
		this.score = INIT_SCORE_VALUE;
		addValue(INIT_FIELD_AMOUNT);
		setGameOver(false);
		setGameWin(false);
		setChanged();
		notifyObservers(this);
	}

	public void moveRight() {
		int move = performMoveRight();
		int merge = performMergeRight();
		performAdd(move, merge);
	}

	public void moveLeft() {
		int move = performMoveLeft();
		int merge = performMergeLeft();
		performAdd(move, merge);
	}

	public void moveTop() {
		int move = performMoveTop();
		int merge = performMergeTop();
		performAdd(move, merge);
	}

	public void moveDown() {
		int move = performMoveDown();
		int merge = performMergeDown();
		performAdd(move, merge);
	}

	public Boolean getOameOver() {
		return this.gameOver;
	}

	public void setGameOver(Boolean gameOver) {
		this.gameOver = gameOver;
	}

	public Integer[][] getBoardModel() {
		return boardModel;
	}

	public void setGameWin(boolean gameWin) {
		this.gameWin = gameWin;

	}

	public Boolean getGameWin() {
		return this.gameWin;
	}
}
