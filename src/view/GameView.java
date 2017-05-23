package view;

import java.util.Observer;

import javafx.scene.Parent;
import javafx.stage.Stage;

public abstract class GameView extends Stage implements Observer {
	
	private Parent root;

	public GameView(Parent root) {
		this.root = root;
		
	}
	
	public void initializeView() {
		loadComponents(root);
		init();
	}
	
	abstract void loadComponents(Parent root);
	abstract void init();
	
	public Parent getRoot() {
		return root;
	}

}
