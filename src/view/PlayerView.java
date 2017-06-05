package view;

import java.util.Observable;

import controller.BoardController;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.StatsModel;

public class PlayerView extends GameView {

	private static final int FONT_SIZE_NORMAL = 40;
	private static final String FONT_STYLE = "Arial";
	private Label labelPlayer;
	private Label labelPlayerName;
	private Button playerButton;
	private VBox playerContainer;
	private HBox buttonContainer;
	private Button save;
	private Button abort;
	private TextField playerTextField;
	private Scene scene;

	public PlayerView(Parent root) {
		super(root);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable o, Object arg) {
		if (!arg.equals(null)) {
			if (arg instanceof String) {
				this.labelPlayerName.setText((String) arg);
			} else if (arg instanceof Boolean) {
				boolean window = (boolean) arg;
				if (window) {
					this.show();
				} else {
					StatsModel statsModel = (StatsModel) o;
					if (statsModel.getSave() && !statsModel.getAbort()) {
						String name = this.playerTextField.getText();
						statsModel.setName(name);
						this.close();
					} else if (!statsModel.getSave() && statsModel.getAbort()) {
						this.close();
					}
				}
			}
		}

	}

	@Override
	protected void loadComponents(Parent root) {
		this.labelPlayer = (Label) root.lookup("#player");
		this.labelPlayerName = (Label) root.lookup("#player_name");
		this.playerButton = (Button) root.lookup("#btn_player");

	}

	@Override
	protected void init() {
		this.labelPlayer.setFont(new Font(FONT_STYLE, FONT_SIZE_NORMAL));
		this.labelPlayerName.setFont(new Font(FONT_STYLE, FONT_SIZE_NORMAL));

		this.playerContainer = new VBox();

		this.buttonContainer = new HBox();

		this.save = new Button("SAVE");
		this.abort = new Button("ABORT");
		this.playerTextField = new TextField();
		this.buttonContainer.getChildren().add(this.save);
		this.buttonContainer.getChildren().add(this.abort);
		this.playerContainer.getChildren().add(buttonContainer);
		this.playerContainer.getChildren().add(this.playerTextField);

		this.playerContainer.setAlignment(Pos.CENTER);
		this.buttonContainer.setAlignment(Pos.CENTER);
		this.save.setAlignment(Pos.CENTER);
		this.abort.setAlignment(Pos.CENTER);

		this.save.setMaxWidth(220);
		this.abort.setMaxWidth(220);
		this.save.setMaxHeight(120);
		this.abort.setMaxHeight(120);
		this.save.getStyleClass().add("ki");
		this.abort.getStyleClass().add("ki");

		this.setWidth(260);
		this.setHeight(180);

		this.scene = new Scene(this.playerContainer);
		this.scene.getStylesheets().add(getClass()
				.getResource("/view/stylesheet.css").toExternalForm());
		this.setTitle("KI CHOOSER");
		this.setScene(this.scene);

	}

	public void addCoontroller(BoardController controller) {
		this.playerButton.setOnAction(event -> controller.handle(event));
		this.save.setOnAction(event -> controller.handle(event));
		this.abort.setOnAction(event -> controller.handle(event));
	}

}
