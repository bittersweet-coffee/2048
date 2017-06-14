package model;

public class KiModel extends GameModel {

	/**
	 * KI does not have any values but since it also extends GameModel this
	 * method has to be overridden.
	 */
	@Override
	protected void init() {
	}

	/**
	 * notifies its views that the window shall open or close.
	 */
	@Override
	public void set(boolean window) {
		setChanged();
		notifyObservers(window);

	}

}
