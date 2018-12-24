package eg.edu.alexu.csd.oop.game.cs01.application;

import java.net.URL;
import java.util.ResourceBundle;

import eg.edu.alexu.csd.oop.game.cs01.leaderboard.LeaderboardManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

public class LeaderboardController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		LeaderboardManager.getInstance().fillTable(leaderboardTable);
	}
	@FXML
	private TableView<?> leaderboardTable;

}
