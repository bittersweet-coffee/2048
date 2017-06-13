package model;

/**
 * TODO
 */
public class GameScreenModel extends GameModel {

	private boolean gameOver;
	private boolean gameWin;

	/**
	 * TODO
	 */
	public GameScreenModel() {
	}

	/**
	 * TODO
	 */
	@Override
	public void init() {
		this.gameOver = false;
		this.gameWin = false;
	}

	/**
	 * TODO
	 */
	public void set(boolean gameWin, boolean gameOver) {
		this.gameOver = gameOver;
		this.gameWin = gameWin;
	}
	
	public boolean getGameOver() {
		return this.gameOver;
	}

	public boolean getGameWin() {
		return this.gameWin;
	}

	public void state() {
		boolean state = false;
		if (this.gameOver && this.gameWin) {
			state = true;
		} else if (this.gameOver && !this.gameWin) {
			state = false;
		}
		setChanged();
		notifyObservers(state);
	}
}
