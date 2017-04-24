package model;

import controller.MainController;

/**
 * Initializes the game data and Controllers
 * @author Henzi
 *
 */
public class Game {
	
	
	private static final Data data = new Data();
	
	public Game() {
		MainController mainController = new MainController();
		mainController.startNewGame(data);
		
		
	}
}
