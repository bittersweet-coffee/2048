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
	public void set(Integer[][] board){
		
	}
	public void set(boolean gameWin, boolean gameOver){
		
	}
	public void set(Integer score){
		
	}
	public void set(boolean window){
		
	}
	
	
	
}
