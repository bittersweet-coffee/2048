package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Statistic {
	
	@XmlElement(name = "highScore")
	private int highScore;
	
	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}

	public int getHighScore() {
		return highScore;
	}

	
}
