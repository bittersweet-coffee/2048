package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import model.BoardModel;
import view.BoardView;

/**
 * TODO
 */
public class BoardController implements ActionListener {
	
	@FXML
	GridPane board;

	private BoardModel boardModel;
	private BoardView boardView;
	
	/**
	 * TODO
	 */
	public BoardController() {
	}
	
	@FXML
	public void initialize() {
		System.out.println("init");
		System.out.println(this.board);
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
	
	public GridPane getBoard() {
		return board;
	}
}
