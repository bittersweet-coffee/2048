package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "Stats")
public class StatsModel extends GameModel {

	@XmlElement
	private Highscore highscore;
	private String path;
	private String file;

	/**
	 * TODO
	 */
	public StatsModel(String path, String file) {
		this.highscore = new Highscore();
		this.path = path;
		this.file = file;
	}

	public StatsModel() {
		this.highscore = new Highscore();
		this.path = System.getProperty("user.dir") + "\\src\\model";
		this.file = "\\stats.xml";
	}

	public String getFile() {
		return file;
	}

	public String getPath() {
		return path;
	}

	/**
	 * TODO
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.highscore.setName(name);
	}

	/**
	 * TODO
	 * 
	 * @param highscore
	 */
	@Override
	public void set(int highscore) {
		if (highscore >= getCurrentHighscore()) {
			this.highscore.setHighScore(highscore);
			this.setChanged();
			this.notifyObservers(highscore);
		}

	}

	/**
	 * TODO
	 * 
	 * @return
	 */
	public int getCurrentHighscore() {
		return this.highscore.getHighScore();
	}

	/**
	 * TODO
	 * 
	 * @return
	 */
	public String getCurrentName() {
		return this.highscore.getName();
	}

	/**
	 * TODO
	 */
	static class Highscore {

		private int highScore;
		private String name;

		/**
		 * TODO
		 * 
		 * @param highScore
		 */
		public void setHighScore(int highScore) {
			this.highScore = highScore;
		}

		/**
		 * TODO
		 * 
		 * @param name
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * TODO
		 * 
		 * @return
		 */
		@XmlValue
		public int getHighScore() {
			return highScore;
		}

		/**
		 * TODO
		 * 
		 * @return
		 */
		@XmlAttribute
		public String getName() {
			return name;
		}
	}

	@Override
	public void init() {
		set(getCurrentHighscore());
		setName(getCurrentName());
		setChanged();
		notifyObservers(getCurrentHighscore());
	}

	@Override
	public void set(Integer[][] board) {
		// TODO Auto-generated method stub

	}

	@Override
	public void set(boolean gameWin, boolean gameOver) {
		// TODO Auto-generated method stub

	}

}
