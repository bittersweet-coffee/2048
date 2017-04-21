package model;

import java.util.Random;

/**
 * Stores all game Data
 * @author Henzi
 *
 */
public final class Data {

	private static final int RAND_RATIO_MAX = 50;
	public static final int ROW = 4;
	public static final int COL = 4;
	public static final int INIT_FIELD_AMOUNT = 2;
	public static final int UPDATE_FIELD_AMOUNT = 1;
	
	private Field[][] board = new Field[ROW][COL];
	private Random random;
	
	public Data() {
		this.random = new Random();
		setUpBoard();
	}
	
	/**
	 * TODO
	 */
	private void setUpBoard() {
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				this.board[i][j] = new Field();
			}
		}
	}
	
	/**
	 * Returns a random integer (standard 0 <= ROW < 4). Is Used to determine a next row for to place a new Number. 
	 * @return random integer in the size of ROW 
	 */
	public int getRandomRow() {
		return this.random.nextInt(ROW);
	}
	
	
	/**
	 * Returns a random integer (standard 0 <= COL < 4). Is Used to determine a next column for to place a new Number.
	 * @return random integer in the size of COL
	 */
	public int getRandomCol() {
		return this.random.nextInt(COL);
	}
	
	/**
	 * TODO
	 */
	public void resetBoard() {
		for (Field[] fields : board) {
			for (Field field : fields) {
				field.setValue(0);
			}
		}
	}
	
	/**
	 * TODO
	 * @return
	 */
	public int getRandomValue() {
		return this.random.nextInt(RAND_RATIO_MAX) <= 10 ? 4 : 2;
	}
	
	public Field[][] getBoard() {
		return board;
	}
	
	public void setBoard(Field[][] board) {
		this.board = board;
	}
}
