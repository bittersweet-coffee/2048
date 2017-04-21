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
	
	
	public NumberBox() {
		this.setAlignment(Pos.CENTER);
		this.lbl = new Label("");
		this.getChildren().add(this.lbl);
	}
	
	public void setLabelText(String str) {
		this.lbl.setText(str);
		this.lbl.setFont(new Font(FONT_STYLE, FONT_SIZE));
	}
}
