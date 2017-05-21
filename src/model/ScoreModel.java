package model;

import java.util.Observable;

/**
 * TODO
 */
public class ScoreModel extends Observable {

	private int score; 
	
	/**
	 * TODO
	 */
	public void initScore() {
		this.score = 0;
		setChanged();
		notifyObservers(this.score);
	}
	
	/**
	 * TODO
	 * @param score
	 */
	public void setScore(int score) {
		if (score != 0) {
			this.score = score;
		}
		setChanged();
		notifyObservers(this.score);
	}
	
	/**
	 * TODO
	 * @return
	 */
	public int getScore() {
		return score;
	}
}
