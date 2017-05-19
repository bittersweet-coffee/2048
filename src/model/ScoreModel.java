package model;

import java.util.Observable;

public class ScoreModel extends Observable {

	private int score; 
	
	public void initScore() {
		setScore(0);
	}
	
	public void setScore(int score) {
		this.score = score;
		setChanged();
		notifyObservers(this.score);
	}
	
	public int getScore() {
		return score;
	}
	
	
}
