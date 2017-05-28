package model;

import java.util.Observable;

public abstract class GameModel extends Observable {
	
	public GameModel() {
		// TODO Auto-generated constructor stub
	}
	
	public void initializeModel() {
		init();
	}	
	
	protected abstract void init();
	public abstract void set(Integer[][] board);
	public abstract void set(boolean gameWin, boolean gameOver);
	public abstract void set(int score);
	public abstract void set(boolean window);
	
	
	
}
