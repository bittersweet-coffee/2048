package model;

/**
 * TODO
 */
public class ScoreModel extends GameModel {

	private Integer score; 
	
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
	@Override
	public void set(Integer score) {
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
	public Integer getScore() {
		return score;
	}
}
