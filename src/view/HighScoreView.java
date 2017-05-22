package view;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * TODO
 */
public class HighScoreView extends Stage implements Observer {

	private static final int FONT_SIZE_NORMAL = 40;
	private static final String FONT_STYLE = "Arial";

	private Parent root;
	private GridPane board;
	private Label labelHighScore;
	private Label labelHighScoreValue;

	/**
	 * TODO
	 * 
	 * @param root
	 */
	public HighScoreView(Parent root) {
		this.root = root;
		loadComponents(this.root);
		initScore(this.board);
	}

	/**
	 * TODO
	 * 
	 * @param board
	 */
	private void initScore(GridPane board) {
		this.labelHighScore.setFont(new Font(FONT_STYLE, FONT_SIZE_NORMAL));
		this.labelHighScoreValue.setFont(new Font(FONT_STYLE, FONT_SIZE_NORMAL));
	}

	/**
	 * TODO
	 * 
	 * @param root
	 */
	private void loadComponents(Parent root) {
		this.board = (GridPane) root.lookup("#board");
		this.labelHighScore = (Label) root.lookup("#highscore");
		this.labelHighScoreValue = (Label) root.lookup("#high_score_value");
	}

	/**
	 * TODO
	 */
	@Override
	public void update(Observable obs, Object obj) {
		System.out.println(obj);
		int score = (int) obj;
		if (score < 10) {
			this.labelHighScoreValue.setText("000" + Integer.toString(score));
		} else if (score < 100) {
			this.labelHighScoreValue.setText("00" + Integer.toString(score));
		} else if (score < 1000) {
			this.labelHighScoreValue.setText("0" + Integer.toString(score));
		} else {
			this.labelHighScoreValue.setText(Integer.toString(score));
		}
	}
}