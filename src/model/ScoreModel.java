package model;

/**
 * TODO
 */
public class ScoreModel extends GameModel {

	private Integer score;

	/**
	 * sets the score to the default (0) value when a new game gets started.
	 */
	@Override
	public void init() {
		this.score = 0;
		setChanged();
		notifyObservers(this.score);
	}

	/**
	 * sets the score to the new calculated score and notifies its views that
	 * the score now has changed.
	 * 
	 * @param score
	 */
	@Override
	public void set(Integer score) {
		if (score != 0) {
			this.score = score;
		}
		setChanged();
		notifyObservers(this.score);
	}

	/**
	 * 
	 * @return score (Integer)
	 */
	public Integer getScore() {
		return score;
	}
}
