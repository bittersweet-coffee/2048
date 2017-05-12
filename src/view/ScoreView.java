package view;

import java.util.Observable;
import java.util.Observer;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import model.IGame;

public class ScoreView implements Observer, IGame {

	private Label lbl_score;
	private Label lbl_score_value;
	public static int SCORE = 0;
	public static boolean SCORE_SET = false;
	public static boolean RESET = false;

	public ScoreView(Label lbl_score, Label lbl_score_value) {
		this.lbl_score = lbl_score;
		this.lbl_score_value = lbl_score_value;
	}

	@Override
	public void init() {
		this.lbl_score.setFont(new Font(FONT_STYLE, FONT_SIZE_NORMAL));
		this.lbl_score_value.setFont(new Font(FONT_STYLE, FONT_SIZE_NORMAL));

	}

	@Override
	public void update(Observable obs, Object obj) {
		if (SCORE_SET) {
			int current_score = Integer
					.parseInt(this.lbl_score_value.getText());
			int new_score = current_score + SCORE;
			if (new_score < 10) {
				this.lbl_score_value
						.setText("000" + Integer.toString(new_score));
			} else if (new_score < 100) {
				this.lbl_score_value
						.setText("00" + Integer.toString(new_score));
			} else if (new_score < 1000) {
				this.lbl_score_value.setText("0" + Integer.toString(new_score));
			} else {
				this.lbl_score_value.setText(Integer.toString(new_score));
			}
			if (RESET) {
				this.lbl_score_value.setText("0000");
			
			}
			SCORE = 0;
			SCORE_SET = false;
		}
	}

}
