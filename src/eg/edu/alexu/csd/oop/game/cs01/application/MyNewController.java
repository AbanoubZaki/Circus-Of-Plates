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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MyNewController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		LeaderboardManager.getInstance().readPlayers();
		backGround.setImage(new Image("File:welcome02.jpg"));
		closeView.setImage(new Image("File:close.png"));

	}

	@FXML
	private Label newGameLabel;

	@FXML
	private Label loadGameLabel;

	@FXML
	private Label leaderBoardLabel;

	@FXML
	void leaderBoardAct(MouseEvent event) {
		loadFxml("/eg/edu/alexu/csd/oop/game/cs01/application/LeaderboardTable.fxml", "Leader Board");
	}

	@FXML
	private ImageView backGround;
	@FXML
	private ImageView closeView;

	@FXML
	public void loadGameAct(MouseEvent event) {
		loadFxml("/eg/edu/alexu/csd/oop/game/cs01/application/LoadGame.fxml", "Load Game");
	}

	@FXML
	void newGameAct(MouseEvent event) {
		loadFxml("/eg/edu/alexu/csd/oop/game/cs01/application/NewGame.fxml", "New Game");
	}

	@FXML
	private void closeApp(MouseEvent event) {
		System.exit(0);
	}

	private void loadFxml(String fxmlPath, String title) {
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
			StackPane pane = new StackPane();
			pane.getChildren().add(root);
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle(title);
			primaryStage.initStyle(StageStyle.UTILITY);
			HandleDragging.getInstance().makeStageDragebale(root, primaryStage);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}