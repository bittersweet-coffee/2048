package view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * This class is used to display the values on the View.
 */
public class NumberBox extends HBox{

	Label lbl;
	private static final int FONT_SIZE = 40;
	private static final String FONT_STYLE = "Arial";
	
	/**
	 * TODO
	 */
	public NumberBox() {
		this.setAlignment(Pos.CENTER);
		this.lbl = new Label("");
		this.lbl.setFont(new Font(FONT_STYLE, FONT_SIZE));
		this.getChildren().add(this.lbl);
	}
	
	/**
	 * TODO
	 * @param str
	 */
	public void setLabelText(String str) {
		this.lbl.setText(str);
	}

	/**
	 * TODO
	 * @param fontSizeSmall
	 */
	public void setLabelFontSize(int fontSizeSmall) {
		this.lbl.setFont(new Font(FONT_STYLE, fontSizeSmall));
	}
}
