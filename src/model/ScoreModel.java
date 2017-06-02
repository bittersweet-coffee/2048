package model;

/**
 * TODO
 */
public class ScoreModel extends GameModel {

	private int score; 
	
	/**
	 * TODO
	 */
	@Override
	public void init() {
		this.score = 0;
		setChanged();
		notifyObservers(this.score);
	}
	
	/**
	 * TODO
	 * @param score
	 */
	public void set(int score) {
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
