package model;

public interface IGame {
	
	public static final int ROW = 4;
	public static final int COL = 4;
	public static final int INIT_FIELD_AMOUNT = 2;
	public static final int UPDATE_FIELD_AMOUNT = 1;
	public static final int INIT_FIELD_VALUE = 0;
	public static final int FONT_SIZE_NORMAL = 40;
	public static final int FONT_SIZE_SMALL = 33;
	public static final String FONT_STYLE = "Arial";
	public static final int WIN_VALUE=2048;
	
	public void init();


}
