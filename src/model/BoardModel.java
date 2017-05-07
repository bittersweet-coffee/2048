package model;
import java.util.Observable;
import java.util.Random;

/**
 * Provides a Data object, which stores all necessary game data and states. This
 * class does not keep track of the game score.
 */
public final class BoardModel extends Observable {

	private static final int RAND_RATIO_MAX = 50;
	public static final int ROW = 4;
	public static final int COL = 4;
	public static final int INIT_FIELD_AMOUNT = 2;
	public static final int UPDATE_FIELD_AMOUNT = 1;
	private static final int INIT_FIELD_VALUE = 0;
	
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
	public void initializeBoardModel() {
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				this.boardModel[i][j] = INIT_FIELD_VALUE;
			}
		}
		
		setChanged();
		notifyObservers(this.boardModel);
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
	 * @return random integer between 0 and ROW
	 */
	public int getRandomRow() {
		return this.random.nextInt(ROW);
	}
	
	/**
	 * Returns a random integer (default <= 0 && >= COL). Used to determine the
	 * next column to place a new number. 
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
		if (this.boardModel[row][col] == INIT_FIELD_VALUE) {
			return false;
		} else {
			return true;
		}
	}
}