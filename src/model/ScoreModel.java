package model;

import java.util.Observable;

public class ScoreModel extends Observable {

	private int score; 
	
	public void initScore() {
		this.score = 0;
		setChanged();
		notifyObservers(this.score);
	}
	
	public void setScore(int score) {
		if (score != 0) {
			this.score = score;
		}
		setChanged();
		notifyObservers(this.score);
	}
	
	public int getScore() {
		return score;
	}
	
	
}
