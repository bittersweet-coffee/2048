package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Board extends Application{

	private FXMLLoader loader;
	
	public Board() {
		this.loader = new FXMLLoader(getClass().getResource("/view/view_board.fxml"));
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("2048");
		Scene scene = new Scene((Parent) loader.load());
		scene.getStylesheets().add(getClass().getResource("/view/stylesheet.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();    
		
		
		
	}
	
	

}
