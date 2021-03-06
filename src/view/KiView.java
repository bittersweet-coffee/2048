package view;

import java.util.Observable;

import controller.BoardController;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class KiView extends GameView {

	private Button random, greedy, ki;
	private VBox kIContainer;
	private Scene scene;

	public KiView(Parent root) {
		super(root);
	}

	/**
	 * When this view gets notified it opens or closes the window for KI
	 * selection.
	 */
	@Override
	public void update(Observable o, Object arg) {
		boolean window = (boolean) arg;
		if (window) {
			this.show();
		}
		if (!window) {
			this.close();
		}

	}

	/**
	 * loads the components form the fxml file which are important for this
	 * view.
	 */
	@Override
	protected void loadComponents(Parent root) {
		this.ki = (Button) root.lookup("#btn_ki");

	}

	/**
	 * Prepares a view for choosing a KI.
	 */
	@Override
	protected void init() {
		this.kIContainer = new VBox();

		this.random = new Button("RANDOM");
		this.greedy = new Button("GREEDY");

		this.kIContainer.getChildren().add(this.random);
		this.kIContainer.getChildren().add(this.greedy);

		this.kIContainer.setAlignment(Pos.CENTER);
		this.random.setAlignment(Pos.CENTER);
		this.greedy.setAlignment(Pos.CENTER);

		this.random.setMaxWidth(220);
		this.greedy.setMaxWidth(220);
		this.random.setMaxHeight(120);
		this.greedy.setMaxHeight(120);
		this.random.getStyleClass().add("ki");
		this.greedy.getStyleClass().add("ki");

		this.setWidth(260);
		this.setHeight(180);

		this.scene = new Scene(this.kIContainer);
		this.scene.getStylesheets().add(getClass()
				.getResource("/view/stylesheet.css").toExternalForm());
		this.setTitle("KI CHOOSER");
		this.setScene(this.scene);
	}

	public void addController(BoardController controller) {
		this.random.setOnAction(event -> controller.handle(event));
		this.greedy.setOnAction(event -> controller.handle(event));
		this.ki.setOnAction(event -> controller.handle(event));
	}

}
