package model;

public class KiModel extends GameModel {


	@Override
	protected void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void set(boolean window) {
		setChanged();
		notifyObservers(window);

	}

	@Override
	public void set(boolean gameWin, boolean gameOver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void set(int score) {
		// TODO Auto-generated method stub

	}

	@Override
	public void set(Integer[][] board) {
		// TODO Auto-generated method stub

	}

}
