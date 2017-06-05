package model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "Stats")
public class StatsModel extends GameModel {

	private Highscore highscore;

	@XmlElementWrapper(name = "highscoreList")
	@XmlElement(name = "highscore")
	private List<Highscore> highscoreList;
	private String path;
	private String file;
	private boolean abort;
	private boolean save;

	/**
	 * TODO
	 */
	public StatsModel(String path, String file) {
		this.highscoreList = new ArrayList<Highscore>();
		this.path = path;
		this.file = file;
	}

	public StatsModel() {
		this.highscoreList = new ArrayList<Highscore>();
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
		
		if (listContainsName(name)) {
			for (Highscore highscore : this.highscoreList) {
				if (highscore.getName().equals(name)) {
					this.highscore = highscore;
					setChanged();
					this.notifyObservers(this.getCurrentName());
					setChanged();
					this.notifyObservers(this.getCurrentHighscore());
				}
			}
		} else {
			addNewPlayer(name);
		}
	}

	private void addNewPlayer(String name) {
		this.highscore = new Highscore();
		this.highscore.setName(name);
		this.highscore.setHighScore(0);
		this.highscoreList.add(this.highscore);
		this.setChanged();
		this.notifyObservers(this.getCurrentName());
		this.setChanged();
		this.notifyObservers(this.getCurrentHighscore());

	}

	/**
	 * TODO
	 * 
	 * @param highscore
	 */
	@Override
	public void set(Integer highscore) {
		if (highscore >= getCurrentHighscore()) {
			this.highscore.setHighScore(highscore);
			this.setChanged();
			this.notifyObservers(highscore);
		}

	}

	@Override
	public void set(boolean window) {
		this.setChanged();
		this.notifyObservers(window);
	}

	public void set(boolean abort, boolean save) {
		this.abort = abort;
		this.save = save;
	}

	/**
	 * TODO
	 * 
	 * @return
	 */
	public int getCurrentHighscore() {
		return this.highscore.getHighScore();
	}

	public boolean getAbort() {
		return this.abort;
	}

	public boolean getSave() {
		return this.save;
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
		if (this.highscore == null) {
			if (this.highscoreList.size() <= 0) {
				addNewPlayer("NoName");
			} else {
				if (listContainsName("NoName")) {
					for (Highscore highscore : this.highscoreList) {
						if (highscore.getName().equals("NoName")) {
							this.highscore = highscore;
						}
					}
					setName(this.getCurrentName());
					set(this.getCurrentHighscore());
				} else {
					addNewPlayer("NoName");
				}
				
			}
		} else {
			setName(this.getCurrentName());
			set(this.getCurrentHighscore());
		}
		
		
	}

	private boolean listContainsName(String name) {
		for (Highscore highscore : this.highscoreList) {
			if (highscore.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

}
