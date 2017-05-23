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

	@Override
	public void set(Integer[][] board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void set(int score) {
		// TODO Auto-generated method stub
		
	}
}
