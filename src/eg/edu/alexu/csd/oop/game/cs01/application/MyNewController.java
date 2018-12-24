package eg.edu.alexu.csd.oop.game.cs01.application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import eg.edu.alexu.csd.oop.game.cs01.leaderboard.LeaderboardManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MyNewController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		LeaderboardManager.getInstance().readPlayers();
	}

	@FXML
	private Label newGameLabel;

	@FXML
	private Label loadGameLabel;

	@FXML
	private Label leaderBoardLabel;

	@FXML
	void leaderBoardAct(MouseEvent event) {
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader
					.load(getClass().getResource("/eg/edu/alexu/csd/oop/game/cs01/application/LeaderboardTable.fxml"));
			StackPane pane = new StackPane();
			pane.getChildren().add(root);
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Leader Board");
			primaryStage.initStyle(StageStyle.UTILITY);
			primaryStage.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	public void loadGameAct(MouseEvent event) {
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader
					.load(getClass().getResource("/eg/edu/alexu/csd/oop/game/cs01/application/LoadGame.fxml"));
			StackPane pane = new StackPane();
			pane.getChildren().add(root);
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("World of Plates");
			primaryStage.initStyle(StageStyle.UTILITY);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void newGameAct(MouseEvent event) {
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader
					.load(getClass().getResource("/eg/edu/alexu/csd/oop/game/cs01/application/NewGame.fxml"));
			StackPane pane = new StackPane();
			// pane.getChildren().add(mv);
			pane.getChildren().add(root);
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("World of Plates");
			primaryStage.initStyle(StageStyle.UTILITY);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}