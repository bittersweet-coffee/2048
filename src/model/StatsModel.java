package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "Stats")
public class StatsModel {
	
	@XmlElement
	private Highscore highscore;
	
	/**
	 * TODO
	 */
	public StatsModel() {
		this.highscore = new Highscore();
	}

	/**
	 * TODO
	 * @param name
	 */
	public void setName(String name) {
		this.highscore.setName(name);
	}

	/**
	 * TODO
	 * @param highscore
	 */
	public void setHighscore(int highscore) {
		this.highscore.setHighScore(highscore);
	}

	/**
	 * TODO
	 * @return
	 */
	public int getCurrentHighscore() {
		return this.highscore.getHighScore();
	}
	
	/**
	 * TODO
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
		 * @param highScore
		 */
		public void setHighScore(int highScore) {
			this.highScore = highScore;
		}
		
		/**
		 * TODO
		 * @param name
		 */
		public void setName(String name) {
			this.name = name;
		}
		
		/**
		 * TODO
		 * @return
		 */
		@XmlValue
		public int getHighScore() {
			return highScore;
		}
		
		/**
		 * TODO
		 * @return
		 */
		@XmlAttribute
		public String getName() {
			return name;
		}
	}

}
