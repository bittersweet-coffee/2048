package view;

import java.util.Observable;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class HighScoreView extends GameView {

	private static final int FONT_SIZE_NORMAL = 40;
	private static final String FONT_STYLE = "Arial";

	private Label labelHighScore;
	private Label labelHighScoreValue;

	/**
	 * @param root
	 */
	public HighScoreView(Parent root) {
		super(root);
	}

	/**
	 * sets up the colors and formats the text on the highscore labels.
	 * 
	 * @param board
	 */
	@Override
	protected void init() {
		this.labelHighScore.setFont(new Font(FONT_STYLE, FONT_SIZE_NORMAL));
		this.labelHighScoreValue
				.setFont(new Font(FONT_STYLE, FONT_SIZE_NORMAL));
	}

	/**
	 * loads the components form the fxml file which are important for this
	 * view.
	 * 
	 * @param root
	 */
	@Override
	protected void loadComponents(Parent root) {
		this.labelHighScore = (Label) root.lookup("#highscore");
		this.labelHighScoreValue = (Label) root.lookup("#high_score_value");
	}

	/**
	 * Updates the highscore when view gets a notified.
	 */
	@Override
	public void update(Observable obs, Object obj) {
		if (!obj.equals(null)) {
			if (obj instanceof Integer) {
				int score = (Integer) obj;
				if (score < 10) {
					this.labelHighScoreValue
							.setText("000" + Integer.toString(score));
				} else if (score < 100) {
					this.labelHighScoreValue
							.setText("00" + Integer.toString(score));
				} else if (score < 1000) {
					this.labelHighScoreValue
							.setText("0" + Integer.toString(score));
				} else {
					this.labelHighScoreValue.setText(Integer.toString(score));
				}
			}
		}
	}
}