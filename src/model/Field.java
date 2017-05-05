package model;

public class Field {
	
	private static final int INIT_FIELD_VALUE = 0;
	private static final int WIN_FIELD_VALUE = 2014;
	private static final boolean INIT_FIELD_STATE = false;
	private boolean occupied;
	private int value;
	
	
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

}
