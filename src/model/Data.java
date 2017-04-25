package model;

import java.util.Random;

/**
 * Stores all game Data
 * @author Henzi
 *
 */
public final class Data {

	private static final int ROW = 4;
	private static final int COL = 4;
	public static final int INIT_FIELD_SCORE = 2;
	
	int[][] board = new int[COL][ROW];
	private Random random;
	
	public Data() {
		this.random = new Random();
		resetBoard();
	}
	
	public int[][] getBoard() {
		return board;
	}
	
	public void setBoard(int[][] board) {
		this.board = board;
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
	
	
	public void resetBoard() {
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				this.board[i][j] = 0;
			}
		}
	}
}
