package model;

import java.util.Observable;

public class GameScreenModel extends Observable{

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
	public void initGameScreen() {
		this.gameOver = false;
		this.gameWin = false;
		
	}

	/**
	 * TODO
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
		
	}

	/**
	 * TODO
	 */
	public void setGameWin(boolean gameWin) {
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

	

}
