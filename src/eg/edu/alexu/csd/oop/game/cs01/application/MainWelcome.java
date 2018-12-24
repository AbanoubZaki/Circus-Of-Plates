package eg.edu.alexu.csd.oop.game.cs01.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainWelcome extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {

			Parent root = FXMLLoader
					.load(getClass().getResource("/eg/edu/alexu/csd/oop/game/cs01/application/Welcoming.fxml"));
			StackPane pane = new StackPane();
			pane.getChildren().add(root);
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("World of Plates");
			primaryStage.initStyle(StageStyle.UNDECORATED);
			HandleDragging.getInstance().makeStageDragebale(root, primaryStage);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}