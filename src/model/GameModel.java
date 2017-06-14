package model;

import java.util.Observable;

/**
 * 
 * This is a template Object from which all the models have to extend (root for
 * Template-Pattern). This Object has several Methods which can and some have to
 * be overridden in the real models.
 */
public abstract class GameModel extends Observable {

	public GameModel() {
	}

	/**
	 * Initializes all models. This method is made because of the similar method
	 * in the GameView object.
	 */
	public void initializeModel() {
		init();
	}

	protected abstract void init();

	public void set(Integer[][] board) {

	}

	public void set(boolean gameWin, boolean gameOver) {

	}

	public void set(Integer score) {

	}

	public void set(boolean window) {

	}

}
