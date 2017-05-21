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
	private static final String STYLE_VALUE_2 = "value-2";
	private static final String STYLE_VALUE_4 = "value-4";
	private static final String STYLE_VALUE_8 = "value-8";
	private static final String STYLE_VALUE_16 = "value-16";
	private static final String STYLE_VALUE_32 = "value-32";
	private static final String STYLE_VALUE_64 = "value-64";
	private static final String STYLE_VALUE_128 = "value-128";
	private static final String STYLE_VALUE_256 = "value-256";
	private static final String STYLE_VALUE_512 = "value-512";
	private static final String STYLE_VALUE_1024 = "value-1024";
	private static final String STYLE_VALUE_2048 = "value-2048";
	
	/**
	 * TODO
	 */
	public NumberBox() {
		this.setAlignment(Pos.CENTER);
		this.lbl = new Label("");
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
	 * @param score
	 */
	public void setStyle(int score) {
		switch (score) {
		case 2:
			this.getStyleClass().clear();
			this.getStyleClass().add(STYLE_VALUE_2);
			break;
		case 4:
			this.getStyleClass().clear();
			this.getStyleClass().add(STYLE_VALUE_4);
			break;
		case 8:
			this.getStyleClass().clear();
			this.getStyleClass().add(STYLE_VALUE_8);
			break;
		case 16:
			this.getStyleClass().clear();
			this.getStyleClass().add(STYLE_VALUE_16);
			break;
		case 32:
			this.getStyleClass().clear();
			this.getStyleClass().add(STYLE_VALUE_32);
			break;
		case 64:
			this.getStyleClass().clear();
			this.getStyleClass().add(STYLE_VALUE_64);
			break;
		case 128:
			this.getStyleClass().clear();
			this.getStyleClass().add(STYLE_VALUE_128);
			break;
		case 256:
			this.getStyleClass().clear();
			this.getStyleClass().add(STYLE_VALUE_256);
			break;
		case 512:
			this.getStyleClass().clear();
			this.getStyleClass().add(STYLE_VALUE_512);
			break;
		case 1024:
			this.getStyleClass().clear();
			this.getStyleClass().add(STYLE_VALUE_1024);
			break;
		case 2048:
			this.getStyleClass().clear();
			this.getStyleClass().add(STYLE_VALUE_2048);
			break;
		default:
			this.getStyleClass().clear();
			break;
		}
	}
}
