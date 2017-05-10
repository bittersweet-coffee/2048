package model;

import java.util.Observable;
import java.util.Random;

import view.ScoreView;

/**
 * Provides a Data object, which stores all necessary game data and states. This
 * class does not keep track of the game score.
 */
public final class BoardModel extends Observable implements IGame {

	private static final int RAND_RATIO_MAX = 50;

	private Integer[][] boardModel = new Integer[ROW][COL];
	private Random random;

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
		ScoreView.RESET = true;
		setChanged();
		notifyObservers(this.boardModel);
	}

	/**
	 * TODO
	 */
	public void setBoardModel(Integer[][] boardModel) {
		this.boardModel = boardModel;

		setChanged();
		notifyObservers(this.boardModel);
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
	 * TODO
	 */
	public int getRandomValue() {
		if (this.random.nextInt(RAND_RATIO_MAX) <= 10) {
			return 4;
		} else {
			return 2;
		}
	}

	/**
	 * TODO
	 */
	public boolean isOccupied(int row, int col) {
		if (this.boardModel[row][col].equals(INIT_FIELD_VALUE)) {
			return false;
		} else {
			return true;
		}
	}

	public void addValue(int amount) {
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
		notifyObservers(this.boardModel);
	}

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

	public int performMoveRight() {
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

	private void merge(Integer[][] board, int row_from, int col_from,
			int row_to, int col_to) {
		int value = board[row_to][col_to] + board[row_from][col_from];
		board[row_to][col_to] = value;
		board[row_from][col_from] = INIT_FIELD_VALUE;
		ScoreView.SCORE = value;
		ScoreView.SCORE_SET = true;
		if (value == WIN_VALUE) {
			win();
		}
		setChanged();
		notifyObservers(this.boardModel);

	}

	private void win() {
		resetBoardModel();
		
	}

	private void move(Integer[][] board, int row_from, int col_from, int row_to,
			int col_to) {
		board[row_to][col_to] = board[row_from][col_from];
		board[row_from][col_from] = INIT_FIELD_VALUE;
		setChanged();
		notifyObservers(this.boardModel);
	}

	@Override
	public void init() {
		System.out.println("init Model");
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				this.boardModel[i][j] = INIT_FIELD_VALUE;
			}
		}
		addValue(INIT_FIELD_AMOUNT);
		setChanged();
		notifyObservers(this.boardModel);
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

	private void performAdd(int move, int merge) {
		if ((move != 0 || merge != 0) && !ScoreView.RESET) {
			addValue(BoardModel.UPDATE_FIELD_AMOUNT);
		} else if ((move != 0 || merge != 0) && ScoreView.RESET) {
			addValue(BoardModel.INIT_FIELD_AMOUNT);
			ScoreView.RESET = false;
		}
	}

}
