package generic;

import java.util.Random;

/**
 * Provides the actual GameLogic of 2048
 */
public class GameLogic {

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

	private static Integer scoreValue = 0;
	private static Integer score = 0;
	private static boolean gameOver = false;
	private static boolean gameWin = false;

	/**
	 * Initialize the initial Data object and state.
	 */
	public GameLogic() {
	}

	/**
	 * Perform a move right
	 * 
	 * @param board
	 * @param score
	 * @return (Integer[][]) The newly calculated board
	 */
	public static Integer[][] moveRight(Integer[][] board, Integer score) {
		GameLogic.setScoreValue(0);
		GameLogic.setScore(score);
		Integer[][] oldBoard = new Integer[ROW][COL];
		for (int i = 0; i < oldBoard.length; i++) {
			for (int j = 0; j < oldBoard.length; j++) {
				oldBoard[i][j] = board[i][j];
			}
		}
		board = performMoveRight(board);
		board = performMergeRight(board);
		Integer newScore = GameLogic.getScore() + GameLogic.getScoreValue();
		GameLogic.setScore(newScore);
		checkWin(board);
		return performAdd(oldBoard, board);
	}

	/**
	 * Perform a move left
	 * 
	 * @param board
	 * @param score
	 * @return (Integer[][]) The newly calculated board
	 */
	public static Integer[][] moveLeft(Integer[][] board, Integer score) {
		GameLogic.setScoreValue(0);
		GameLogic.setScore(score);
		Integer[][] oldBoard = new Integer[ROW][COL];
		for (int i = 0; i < oldBoard.length; i++) {
			for (int j = 0; j < oldBoard.length; j++) {
				oldBoard[i][j] = board[i][j];
			}
		}
		board = performMoveLeft(board);
		board = performMergeLeft(board);
		Integer newScore = GameLogic.getScore() + GameLogic.getScoreValue();
		GameLogic.setScore(newScore);
		checkWin(board);
		return performAdd(oldBoard, board);
	}

	/**
	 * Perform a move up
	 * 
	 * @param board
	 * @param score
	 * @return (Integer[][]) The newly calculated board
	 */
	public static Integer[][] moveUp(Integer[][] board, Integer score) {
		GameLogic.setScoreValue(0);
		GameLogic.setScore(score);
		Integer[][] oldBoard = new Integer[ROW][COL];
		for (int i = 0; i < oldBoard.length; i++) {
			for (int j = 0; j < oldBoard.length; j++) {
				oldBoard[i][j] = board[i][j];
			}
		}
		board = performMoveUp(board);
		board = performMergeUp(board);
		Integer newScore = GameLogic.getScore() + GameLogic.getScoreValue();
		GameLogic.setScore(newScore);
		checkWin(board);
		return performAdd(oldBoard, board);
	}

	/**
	 * Perform a move down
	 * 
	 * @param board
	 * @param score
	 * @return (Integer[][]) The newly calculated board
	 */
	public static Integer[][] moveDown(Integer[][] board, Integer score) {
		GameLogic.setScoreValue(0);
		GameLogic.setScore(score);
		Integer[][] oldBoard = new Integer[ROW][COL];
		for (int i = 0; i < oldBoard.length; i++) {
			for (int j = 0; j < oldBoard.length; j++) {
				oldBoard[i][j] = board[i][j];
			}
		}
		board = performMoveDown(board);
		board = performMergeDown(board);
		Integer newScore = GameLogic.getScore() + GameLogic.getScoreValue();
		GameLogic.setScore(newScore);
		checkWin(board);
		return performAdd(oldBoard, board);
	}

	/**
	 * Adds a certain amount of new fields to a possible place on the board.
	 * 
	 * @param board
	 * @param amount
	 * @return (Integer[][]) The newly calculcated board
	 */
	public static Integer[][] addValue(Integer[][] board, int amount) {
		int counter = 0;
		while (counter != amount) {
			int row = getRandomRow();
			int col = getRandomCol();
			int fieldValue = getRandomValue();
			if (!isOccupied(board, row, col)) {
				board[row][col] = fieldValue;
				counter++;
			}
		}
		return board;
	}

	/**
	 * Check if the game is already won and perform an action if required
	 * 
	 * @param board
	 */
	private static void checkWin(Integer[][] board) {
		for (Integer[] integers : board) {
			for (Integer integer : integers) {
				if (integer == GameLogic.WIN_VALUE) {
					setGameWin(true);
					setGameOver(true);
				}
			}
		}
	}

	/**
	 * Returns a random integer (default <= 0 && >= ROW). Used to determine the
	 * next row to place a new number.
	 * 
	 * @return (int) random integer between 0 and ROW
	 */
	private static int getRandomRow() {
		Random random = new Random();
		return random.nextInt(ROW);
	}

	/**
	 * Returns a random integer (default <= 0 && >= COL). Used to determine the
	 * next column to place a new number.
	 * 
	 * @return (int) random integer between 0 and COL
	 */
	private static int getRandomCol() {
		Random random = new Random();
		return random.nextInt(COL);
	}

	/**
	 * Returns a random int (dafault 2 || 4). Used to set the values on the
	 * Board when game starts and move is performed.
	 * 
	 * @return (int) random integer
	 */
	private static int getRandomValue() {
		Random random = new Random();
		if (random.nextInt(RAND_RATIO_MAX) <= 10) {
			return BIG_FIELD_VALUE;
		} else {
			return SMALL_FIELS_VALUE;
		}
	}

	/**
	 * Determines if a certain field on the board is occupied (returns true is
	 * it's occupied and false otherwise.
	 * 
	 * @param board
	 * @param row
	 * @param col
	 * @return (boolean) true if row - col is occupied
	 */
	private static boolean isOccupied(Integer[][] board, int row, int col) {
		if (board[row][col].equals(INIT_FIELD_VALUE)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Checks if there are possible fields on the board which can be merged. If
	 * a merge is possible a further move has to be done.
	 * 
	 * @return (Integer[][]) The newly calculated board
	 */
	private static Integer[][] performMergeRight(Integer[][] board) {
		for (int row = board.length - 1; row >= 0; row--) {
			for (int col = board.length - 1; col >= 0; col--) {
				if (isOccupied(board, row, col) && col != 0) {
					if (isOccupied(board, row, col - 1)
							&& (board[row][col].equals(board[row][col - 1]))) {
						board = merge(board, row, col - 1, row, col);
						board = performMoveRight(board);
					}
				}
			}
		}
		return board;
	}

	/**
	 * Checks if there are possible fields on the board which can be moved.
	 * 
	 * @return (Integer[][]) The newly calculated board
	 */
	private static Integer[][] performMoveRight(Integer[][] board) {
		if (!checkMovePossibility(board)) {
			setGameOver(true);
		}
		for (int row = board.length - 1; row >= 0; row--) {
			for (int col = board.length - 1; col >= 0; col--) {
				if (isOccupied(board, row, col)) {
					for (int col_move = col
							+ 1; col_move < board.length; col_move++) {
						if (isOccupied(board, row, col_move)) {
							if (col != col_move - 1) {
								board = move(board, row, col, row,
										col_move - 1);
							}
							col_move = board.length;
						} else if (col_move == board.length - 1
								&& !isOccupied(board, row, board.length - 1)) {
							board = move(board, row, col, row,
									board.length - 1);
						}
					}
				}
			}
		}
		return board;
	}

	/**
	 * Checks if there are possible fields on the board which can be merged. If
	 * a merge is possible a further move has to be done.
	 * 
	 * @return (Integer[][]) The newly calculated board
	 */
	private static Integer[][] performMergeLeft(Integer[][] board) {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {
				if (isOccupied(board, row, col) && col + 1 != board.length) {
					if (isOccupied(board, row, col + 1)
							&& (board[row][col].equals(board[row][col + 1]))) {
						board = merge(board, row, col + 1, row, col);
						board = performMoveLeft(board);
					}
				}
			}
		}
		return board;
	}

	/**
	 * Checks if there are possible fields on the board which can be moved.
	 * 
	 * @return (Integer[][]) The newly calculated board
	 */
	private static Integer[][] performMoveLeft(Integer[][] board) {
		if (!checkMovePossibility(board)) {
			setGameOver(true);
		}
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {
				if (isOccupied(board, row, col)) {
					for (int col_move = col - 1; col_move >= 0; col_move--) {
						if (isOccupied(board, row, col_move)) {
							if (col != col_move + 1) {
								board = move(board, row, col, row,
										col_move + 1);
							}
							col_move = -1;
						} else if (col_move == 0
								&& !isOccupied(board, row, 0)) {
							board = move(board, row, col, row, 0);
						}
					}
				}
			}
		}
		return board;
	}

	/**
	 * Checks if there are possible fields on the board which can be merged. If
	 * a merge is possible a further move has to be done.
	 * 
	 * @return (Integer[][]) The newly calculated board
	 */
	private static Integer[][] performMergeUp(Integer[][] board) {
		for (int col = board.length - 1; col >= 0; col--) {
			for (int row = 0; row < board.length; row++) {
				if (isOccupied(board, row, col) && row + 1 != board.length) {
					if (isOccupied(board, row + 1, col)
							&& (board[row][col].equals(board[row + 1][col]))) {
						board = merge(board, row, col, row + 1, col);
						board = performMoveUp(board);
					}
				}
			}
		}
		return board;
	}

	/**
	 * Checks if there are possible fields on the board which can be moved.
	 * 
	 * @return (Integer[][]) The newly calculated board
	 */
	private static Integer[][] performMoveUp(Integer[][] board) {
		if (!checkMovePossibility(board)) {
			setGameOver(true);
		}
		for (int col = board.length - 1; col >= 0; col--) {
			for (int row = 0; row < board.length; row++) {
				if (isOccupied(board, row, col)) {
					for (int row_move = row - 1; row_move >= 0; row_move--) {
						if (isOccupied(board, row_move, col)) {
							if (row != row_move + 1) {
								board = move(board, row, col, row_move + 1,
										col);
							}
							row_move = -1;
						} else if (row_move == 0
								&& !(isOccupied(board, 0, col))) {
							board = move(board, row, col, 0, col);
						}
					}
				}
			}
		}
		return board;
	}

	/**
	 * Checks if there are possible fields on the board which can be merged. If
	 * a merge is possible a further move has to be done.
	 * 
	 * @return (Integer[][]) The newly calculated board
	 */
	private static Integer[][] performMergeDown(Integer[][] board) {
		for (int col = 0; col < board.length; col++) {
			for (int row = board.length - 1; row >= 0; row--) {
				if (isOccupied(board, row, col) && row + 1 != board.length) {
					if (isOccupied(board, row + 1, col)
							&& (board[row][col].equals(board[row + 1][col]))) {
						board = merge(board, row, col, row + 1, col);
						board = performMoveDown(board);
					}
				}
			}
		}
		return board;
	}

	/**
	 * Checks if there are possible fields on the board which can be moved.
	 * 
	 * @return (Integer[][]) The newly calculated board
	 */
	private static Integer[][] performMoveDown(Integer[][] board) {
		if (!checkMovePossibility(board)) {
			setGameOver(true);
		}
		for (int col = 0; col < board.length; col++) {
			for (int row = board.length - 1; row >= 0; row--) {
				if (isOccupied(board, row, col)) {
					for (int row_move = row
							+ 1; row_move < board.length; row_move++) {
						if (isOccupied(board, row_move, col)) {
							if (row != row_move - 1) {
								board = move(board, row, col, row_move - 1,
										col);
							}
							row_move = board.length;
						} else if (row_move == board.length - 1
								&& !isOccupied(board, board.length - 1, col)) {
							board = move(board, row, col, board.length - 1,
									col);
						}
					}
				}
			}
		}
		return board;
	}

	/**
	 * Merges certain field on the board and checks whether the player has won
	 * the game.
	 * 
	 * @param board
	 * @param row_from
	 * @param col_from
	 * @param row_to
	 * @param col_to
	 * @return (Integer[][]) The newly calculcated board
	 */
	private static Integer[][] merge(Integer[][] board, int row_from,
			int col_from, int row_to, int col_to) {
		int value = board[row_to][col_to] + board[row_from][col_from];
		board[row_to][col_to] = value;
		setScoreValue(value);
		board[row_from][col_from] = INIT_FIELD_VALUE;
		return board;
	}

	/**
	 * Moves certain fields on the board.
	 * 
	 * @param board
	 * @param row_from
	 * @param col_from
	 * @param row_to
	 * @param col_to
	 * @return (Integer[][]) The newly calculcated board
	 */
	private static Integer[][] move(Integer[][] board, int row_from,
			int col_from, int row_to, int col_to) {
		board[row_to][col_to] = board[row_from][col_from];
		board[row_from][col_from] = INIT_FIELD_VALUE;
		return board;
	}

	/**
	 * Add two different boards together and check if the game is lost
	 * 
	 * @return (Integer[][]) The newly calculcated board
	 */
	private static Integer[][] performAdd(Integer[][] oldBoard,
			Integer[][] newBoard) {
		for (int i = 0; i < newBoard.length; i++) {
			for (int j = 0; j < newBoard.length; j++) {
				if (newBoard[i][j] != oldBoard[i][j] && !GameLogic.gameOver) {
					return addValue(newBoard, UPDATE_FIELD_AMOUNT);
				}
			}
		}
		return oldBoard;
	}

	/**
	 * Check if it's even possible to move.
	 * 
	 * @param board
	 * @return (boolean) true if move is possible
	 */
	private static boolean checkMovePossibility(Integer[][] board) {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {
				if (row == board.length - 1 && col != board.length - 1) {
					if (board[row][col] == board[row][col + 1]) {
						return true;
					}
				}
				if (row != board.length - 1 && col == board.length - 1) {
					if (board[row][col] == board[row + 1][col]) {
						return true;
					}
				}
				if (row < board.length - 1 && col < board.length - 1) {
					if (board[row][col] == board[row + 1][col]
							|| board[row][col] == board[row][col + 1]) {
						return true;
					}
				}
				if (!isOccupied(board, row, col)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns the current score
	 * 
	 * @return (Integer)
	 */
	public static Integer getScore() {
		return GameLogic.score;
	}

	/**
	 * Set the current score
	 * 
	 * @param score
	 */
	private static void setScore(Integer score) {
		GameLogic.score = score;
	}

	/**
	 * Returns the current scoreValue
	 * 
	 * @return (Integer)
	 */
	public static Integer getScoreValue() {
		return GameLogic.scoreValue;
	}

	/**
	 * Set the current scoreValue
	 * 
	 * @param score
	 */
	private static void setScoreValue(Integer score) {
		if (score == 0) {
			GameLogic.scoreValue = 0;
		} else {
			GameLogic.scoreValue = GameLogic.scoreValue + score;
		}
	}

	/**
	 * Set the gameOver flag
	 * 
	 * @param gameOver
	 */
	public static void setGameOver(Boolean gameOver) {
		GameLogic.gameOver = gameOver;
	}

	/**
	 * Get the gameOver flag
	 * 
	 * @return (boolean)
	 */
	public static boolean getGameOver() {
		return GameLogic.gameOver;
	}

	/**
	 * Set the gameWin flag
	 * 
	 * @param gameWin
	 */
	public static void setGameWin(Boolean gameWin) {
		GameLogic.gameWin = gameWin;
	}

	/**
	 * Get the gameWin flag
	 * 
	 * @return (boolean)
	 */
	public static boolean getGameWin() {
		return GameLogic.gameWin;
	}
}
