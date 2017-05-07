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
	
	/**
	 * TODO
	 */
	public void actionPerformed(ActionEvent e) {
		
	}
	
	/**
	 * TODO
	 * @param boardModel
	 */
	public void addBoardModel(BoardModel boardModel) {
		this.boardModel = boardModel;
	}
	
	/**
	 * TODO
	 * @param boardView
	 */
	public void addBoardView(BoardView boardView) {
		this.boardView = boardView;
	}
	
	/**
	 * TODO
	 */
	public void initBoardModel() {
		this.boardModel.initializeBoardModel();
	}
}
