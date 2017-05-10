package controller;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import model.BoardModel;
import model.IGame;

/**
 * TODO
 */
public class BoardController {

	private ArrayList<IGame> componentList = new ArrayList<IGame>();
	
	/**
	 * TODO
	 */
	public BoardController() {
		
	}
	
	public void handleArrowKey(KeyEvent key, BoardModel boardModel) {
		switch (key.getCode()) {
		case UP:
			boardModel.moveTop();
			break;
		case DOWN:
			boardModel.moveDown();
			break;
		case LEFT:
			boardModel.moveLeft();
			break;
		case RIGHT:
			boardModel.moveRight();
			break;
		default:
			break;
		}
	}
	/**
	 * TODO
	 * @param boardModel 
	 */
	public void actionPerformed(ActionEvent e, BoardModel boardModel) {
		System.out.println(e);
	}
	
	
	public void add(IGame igame) {
		this.componentList.add(igame);
	}

	public void initStart() {
		for (IGame iGame : componentList) {
			iGame.init();
		}
	}

	public void actionTop(ActionEvent event, BoardModel boardModel) {
		boardModel.moveTop();
	}

	public void actionLeft(ActionEvent event, BoardModel boardModel) {
		boardModel.moveLeft();
	}

	public void actionDown(ActionEvent event, BoardModel boardModel) {
		boardModel.moveDown();
	}

	public void actionRight(ActionEvent event, BoardModel boardModel) {
		boardModel.moveRight();
	}
	
}
