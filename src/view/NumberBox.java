package view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * This class is used to display the values on the View.
 * @author Henzi
 *
 */
public class NumberBox extends HBox{

	Label lbl;
	private static final int FONT_SIZE = 40;
	private static final String FONT_STYLE = "Arial";

	public NumberBox(int fieldScore){
		this.setAlignment(Pos.CENTER);
		this.lbl = new Label();
		this.lbl.setFont(new Font(FONT_STYLE, FONT_SIZE));
		this.lbl.setText(Integer.toString(fieldScore));
		this.getChildren().add(lbl);
	}
}
