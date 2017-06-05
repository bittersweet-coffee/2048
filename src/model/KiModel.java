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

}
