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
public class ScoreView extends Stage implements Observer {

	private static final int FONT_SIZE_NORMAL = 40;
	private static final String FONT_STYLE = "Arial";

	private Parent root;
	private GridPane board;
	private Label lbl_score;
	private Label lbl_score_value;

	/**
	 * TODO
	 * 
	 * @param root
	 */
	public ScoreView(Parent root) {
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
		this.lbl_score.setFont(new Font(FONT_STYLE, FONT_SIZE_NORMAL));
		this.lbl_score_value.setFont(new Font(FONT_STYLE, FONT_SIZE_NORMAL));
	}

	/**
	 * TODO
	 * 
	 * @param root
	 */
	private void loadComponents(Parent root) {
		this.board = (GridPane) root.lookup("#board");
		this.lbl_score = (Label) root.lookup("#score");
		this.lbl_score_value = (Label) root.lookup("#score_value");
	}

	/**
	 * TODO
	 */
	public void update(Observable obs, Object obj) {
		int score = (int) obj;
		if (score < 10) {
			this.lbl_score_value.setText("000" + Integer.toString(score));
		} else if (score < 100) {
			this.lbl_score_value.setText("00" + Integer.toString(score));
		} else if (score < 1000) {
			this.lbl_score_value.setText("0" + Integer.toString(score));
		} else {
			this.lbl_score_value.setText(Integer.toString(score));
		}
	}		
}
