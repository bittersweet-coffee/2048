package model;

import generic.GameLogic;

/**
 * Provides a Data object, which stores all necessary game data and states. This
 * class does not keep track of the game score.
 */
public final class BoardModel extends GameModel {

	private Integer[][] boardModel = new Integer[GameLogic.ROW][GameLogic.COL];

	/**
	 * Initialize the initial Data object and state.
	 */
	public BoardModel() {
	}

	/**
	 * Initializes the board and sets game to not won nor lose.
	 */
	@Override
	public void init() {
		for (int i = 0; i < GameLogic.ROW; i++) {
			for (int j = 0; j < GameLogic.COL; j++) {
				this.boardModel[i][j] = GameLogic.INIT_FIELD_VALUE;
			}
		}
		this.boardModel = GameLogic.addValue(this.boardModel,
				GameLogic.INIT_FIELD_AMOUNT);
		setChanged();
		notifyObservers(this.boardModel);
	}

	/**
	 * Resets the board to its inital values so that a new game is possible
	 */
	public void resetModel() {
		for (int i = 0; i < GameLogic.ROW; i++) {
			for (int j = 0; j < GameLogic.COL; j++) {
				this.boardModel[i][j] = GameLogic.INIT_FIELD_VALUE;
			}
		}
		setChanged();
		notifyObservers(this.boardModel);
	}

	/**
	 * 
	 * 
	 * @return Integer[][] board
	 */
	public Integer[][] getModel() {
		return boardModel;
	}

	/**
	 * sets the board and notifies the models observers.
	 * 
	 */
	@Override
	public void set(Integer[][] boardModel) {
		this.boardModel = boardModel;
		setChanged();
		notifyObservers(this.boardModel);
	}
}
