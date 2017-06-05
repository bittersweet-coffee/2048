package view;

import java.util.Observable;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * TODO
 */
public class ScoreView extends GameView {

	private static final int FONT_SIZE_NORMAL = 40;
	private static final String FONT_STYLE = "Arial";

	private Label labelScore;
	private Label labelScoreValue;

	/**
	 * TODO
	 * 
	 * @param root
	 */
	public ScoreView(Parent root) {
		super(root);
	}

	/**
	 * TODO
	 * 
	 * @param board
	 */
	@Override
	protected void init() {
		this.labelScore.setFont(new Font(FONT_STYLE, FONT_SIZE_NORMAL));
		this.labelScoreValue.setFont(new Font(FONT_STYLE, FONT_SIZE_NORMAL));
	}

	/**
	 * TODO
	 * 
	 * @param root
	 */
	@Override
	protected void loadComponents(Parent root) {
		this.labelScore = (Label) root.lookup("#score");
		this.labelScoreValue = (Label) root.lookup("#score_value");
	}

	/**
	 * TODO
	 */
	@Override
	public void update(Observable obs, Object obj) {
		Integer score = (Integer) obj;
		if (score < 10) {
			this.labelScoreValue.setText("000" + Integer.toString(score));
		} else if (score < 100) {
			this.labelScoreValue.setText("00" + Integer.toString(score));
		} else if (score < 1000) {
			this.labelScoreValue.setText("0" + Integer.toString(score));
		} else {
			this.labelScoreValue.setText(Integer.toString(score));
		}
	}
}
