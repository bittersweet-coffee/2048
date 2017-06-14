package model;

/**
 * Sets notifiys its view when a game is over and whether the game is lost or
 * won.
 */
public class GameScreenModel extends GameModel {

	private boolean gameOver;
	private boolean gameWin;

	public GameScreenModel() {
	}

	/**
	 * Initializes the values of this object, so that a game can be played.
	 */
	@Override
	public void init() {
		this.gameOver = false;
		this.gameWin = false;
	}

	/**
	 * Sets the values for game win/lost.
	 */
	public void set(boolean gameWin, boolean gameOver) {
		this.gameOver = gameOver;
		this.gameWin = gameWin;
	}

	/**
	 * 
	 * @return gameOver (boolean)
	 */
	public boolean getGameOver() {
		return this.gameOver;
	}

	
	/**
	 * 
	 * @return gameWin (boolean)
	 */
	public boolean getGameWin() {
		return this.gameWin;
	}

	
	/**
	 * Notifies its observers that a game is over now.
	 */
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
