package model;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * 
 * An object that handels the in and output to a XML file. It sets handles
 * highsocre and palyername in - and output.
 *
 */
@XmlRootElement(name = "Stats")
public class StatsModel extends GameModel {

	private Highscore highscore;

	@XmlElementWrapper(name = "highscoreList")
	@XmlElement(name = "highscore")
	private List<Highscore> highscoreList;
	private String path;
	private boolean abort;
	private boolean save;

	/**
	 * TODO
	 */
	public StatsModel() {
		this.highscoreList = new ArrayList<Highscore>();
		this.path = Paths.get(System.getProperty("user.dir"), "src", "model",
				"stats.xml").toString();
	}

	public StatsModel(String path) {
		this.highscoreList = new ArrayList<Highscore>();
		this.path = path;
	}

	/**
	 * 
	 * Sets the name of the Player and sets it's highscore if the player is
	 * known. If a player is not known a new PLayer will be set.
	 * 
	 * @param name
	 *            (String)
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
	 * 
	 * The highscore only gets set when it is higher or equal than the highscore
	 * which is already set.
	 * 
	 * @param highscore (Integer)
	 */
	@Override
	public void set(Integer highscore) {
		if (highscore >= getCurrentHighscore()) {
			this.highscore.setHighScore(highscore);
			this.setChanged();
			this.notifyObservers(highscore);
		}

	}

	/**
	 * Handles window open/close for player-name input.
	 * @param window (boolen)
	 */
	@Override
	public void set(boolean window) {
		this.setChanged();
		this.notifyObservers(window);
	}

	/**
	 * Sets flags for save / abortion when a name was entered.
	 */
	public void set(boolean abort, boolean save) {
		this.abort = abort;
		this.save = save;
	}

	/**
	 * 
	 * gets the current highscore stored in the inner highscore-class.
	 * 
	 * @return currentHightscore (int)
	 */
	public int getCurrentHighscore() {
		return this.highscore.getHighScore();
	}

	/**
	 * 
	 * abort flag for player name window
	 * 
	 * @return abort (boolean)
	 */
	public boolean getAbort() {
		return this.abort;
	}

	/**
	 * 
	 * save flag for player name window
	 * 
	 * @return save (boolean)
	 */
	public boolean getSave() {
		return this.save;
	}

	/**
	 * 
	 * gets the current player name stored in the inner highscore-class.
	 * 
	 * @return name (String)
	 */
	public String getCurrentName() {
		return this.highscore.getName();
	}

	/**
	 * 
	 * returns the path where the XML-Stats-File is located.
	 * 
	 * @return path (String)
	 */
	public String getPath() {
		return path;
	}

	/**
	 * An inner class used for storing a player name and a highscore in a
	 * object. this object also handles XML formatting.
	 */
	static class Highscore {

		private int highScore;
		private String name;

		/**
		 * 
		 * sets the highscore for a highscore-object.
		 * @param highScore (int)
		 */
		public void setHighScore(int highScore) {
			this.highScore = highScore;
		}

		/**
		 * 
		 * sets the name of a player for a highscore-object.
		 * @param name (string)
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * 
		 * @return highscore (int)
		 */
		@XmlValue
		public int getHighScore() {
			return highScore;
		}

		/**
		 * 
		 * @return name (String)
		 */
		@XmlAttribute
		public String getName() {
			return name;
		}
	}

	/**
	 * Initializes the model adds a dummy player if no player is found.
	 */
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

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof StatsModel))
			return false;
		StatsModel other = (StatsModel) obj;
		if (abort != other.abort)
			return false;
		if (highscore == null) {
			if (other.highscore != null)
				return false;
		} else if (!highscore.equals(other.highscore))
			return false;
		if (highscoreList == null) {
			if (other.highscoreList != null)
				return false;
		} else if (!highscoreList.equals(other.highscoreList))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (save != other.save)
			return false;
		return true;
	}
}
