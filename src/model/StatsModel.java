package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StatsModel {
	
	@XmlElement(name = "highScore")
	private int highScore;
	
	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}

	public int getHighScore() {
		return this.highScore;
	}
}
