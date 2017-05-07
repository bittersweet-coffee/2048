package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.BoardModel;
import view.BoardView;

/**
 * TODO
 */
public class BoardController implements ActionListener {

	private BoardModel boardModel;
	private BoardView boardView;
	
	/**
	 * TODO
	 */
	public BoardController() {
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public void addBoardModel(BoardModel boardModel) {
		this.boardModel = boardModel;
	}
	
	public void addBoardView(BoardView boardView) {
		this.boardView = boardView;
	}
	
	public void initBoardModel() {
		this.boardModel.initializeBoardModel();
	}
}