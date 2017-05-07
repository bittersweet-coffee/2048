package view;

import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Displays a JavaFX - FXML board View.
 * Only Displays the minimal static view of the board.
 * @author Henzi
 *
 */
public class Board extends Application {

	private FXMLLoader loader;
	
	public Board() {
		this.loader = new FXMLLoader(getClass().getResource("/view/view_board.fxml"));
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		System.out.println("Board");
		primaryStage.setTitle("2048");
		Scene scene = new Scene((Parent) loader.load());
		scene.getStylesheets().add(getClass().getResource("/view/stylesheet.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();    
	}

}
