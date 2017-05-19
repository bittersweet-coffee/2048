package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "Stats")
public class StatsModel {
	
	@XmlElement
	private Highscore highscore;
	
	public StatsModel() {
		this.highscore = new Highscore();
	}
	
	public void setHighscore(int highscore) {
		this.highscore.setHighScore(highscore);
	}
	
	public void setName(String name) {
		this.highscore.setName(name);
	}
	
	public int getCurrentHighscore() {
		return this.highscore.getHighScore();
	}
	
	public String getCurrentName() {
		return this.highscore.getName();
	}
	
	static class Highscore {
		
		private int highScore;
		private String name;
		
		public void setHighScore(int highScore) {
			this.highScore = highScore;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		@XmlValue
		public int getHighScore() {
			return highScore;
		}
		
		@XmlAttribute
		public String getName() {
			return name;
		}
	}

}
