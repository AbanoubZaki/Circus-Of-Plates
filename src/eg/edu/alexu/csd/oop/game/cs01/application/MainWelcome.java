package eg.edu.alexu.csd.oop.game.cs01.application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainWelcome extends Application {
	private Stage mainStage;
	
	@Override
	public void start(Stage primaryStage) {
		mainStage = primaryStage;
		try {

			Parent root = FXMLLoader.load(getClass().getResource("/eg/edu/alexu/csd/oop/game/cs01/application/Welcoming.fxml"));
			StackPane pane = new StackPane();
			pane.getChildren().add(root);
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("World of Plates");
			primaryStage.initStyle(StageStyle.UTILITY);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public void showNewGameScene() {
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/eg/edu/alexu/csd/oop/game/cs01/application/NewGame.fxml"));
			Pane pane = new Pane();
			pane.getChildren().add(root);
			Scene scene = new Scene(pane);
			mainStage.setScene(scene);
			mainStage.setResizable(false);
			mainStage.setTitle("World of Plates");
			mainStage.initStyle(StageStyle.UTILITY);
			mainStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}