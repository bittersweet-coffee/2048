package view;

import java.util.Observer;

import javafx.scene.Parent;
import javafx.stage.Stage;

public abstract class GameView extends Stage implements Observer {

	private Parent root;

	public GameView(Parent root) {
		this.root = root;

	}

	/**
	 * loads the components on all views and initializes all the board views.
	 */
	public void initializeView() {
		loadComponents(root);
		init();
	}

	protected abstract void loadComponents(Parent root);

	protected abstract void init();

	/**
	 * gets the fxml file which is loaded.
	 * @return root (Parent)
	 */
	public Parent getRoot() {
		return root;
	}

}
