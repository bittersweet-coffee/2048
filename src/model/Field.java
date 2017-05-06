package model;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import view.NumberBox;

public class Field {
	
	private static final int INIT_FIELD_VALUE = 0;
	private static final int WIN_FIELD_VALUE = 2014;
	private static final boolean INIT_FIELD_STATE = false;
	private boolean occupied;
	private int value;
	HBox box;
	
	public Field() {
		this.occupied = INIT_FIELD_STATE;
		this.value = INIT_FIELD_VALUE;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
		if (value == INIT_FIELD_VALUE) {
			setOccupied(false);
		} else {
			setOccupied(true);
		}
	}
	
	private void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	
	public boolean isOccupied() {
		return occupied;
	}
	
	public static int getInitFieldValue() {
		return INIT_FIELD_VALUE;
	}
	
	public static int getWinFieldValue() {
		return WIN_FIELD_VALUE;
	}

	public void update(GridPane board, int i, int j) {
		for (Node node : board.getChildren()) {
			if (node instanceof HBox && GridPane.getRowIndex(node) == i && GridPane.getColumnIndex(node) == j) {
				if (this.value != 0) {
					((NumberBox)node).setLabelText(Integer.toString(this.value));
				} else {
					((NumberBox)node).setLabelText("");
				}
			}
		}
	}
	

	public void init(GridPane board, int col, int row) {
		this.box = new NumberBox();
		board.add(this.box, col, row);
	}

}
