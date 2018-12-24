package eg.edu.alexu.csd.oop.game.cs01.application;

import java.net.URL;
import java.util.ResourceBundle;

import eg.edu.alexu.csd.oop.game.cs01.Difficulty.GameDifficulty;
import eg.edu.alexu.csd.oop.game.cs01.ModeFactory.GameMode;
import eg.edu.alexu.csd.oop.game.cs01.OurWorld.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewGameController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> diffContent = FXCollections.observableArrayList("easy", "medium", "hard");
		ObservableList<String> modeContent = FXCollections.observableArrayList("christmass", "robot", "Tas");
		difficultyBox.setItems(diffContent);
		modeBox.setItems(modeContent);
	}

	@FXML
	private ComboBox<String> difficultyBox;

	@FXML
	private ComboBox<String> modeBox;

	@FXML
	private TextField userName;

	@FXML
	private Button startBtn;

	@FXML
	void startAct(ActionEvent event) {
		if (userName.getText() == null || userName.getText().equals("")
				|| modeBox.getValue() == null || modeBox.getValue().equals("")
				|| difficultyBox.getValue() == null || difficultyBox.getValue().equals("")) {
			return;
		}
		
		GameDifficulty diff;
		if (difficultyBox.getValue().equals("easy")) {
			diff = GameDifficulty.easy;
		} else if (difficultyBox.getValue().equals("medium")) {
			diff = GameDifficulty.medium;
		} else if (difficultyBox.getValue().equals("hard")) {
			diff = GameDifficulty.hard;
		} else {
			diff = GameDifficulty.easy;
		}

		GameMode mode;
		if (modeBox.getValue().equals("christmass")) {
			mode = GameMode.christmass;
		} else if (modeBox.getValue().equals("robot")) {
			mode = GameMode.robot;
		} else if (modeBox.getValue().equals("Tas")) {
			mode = GameMode.Tas;
		} else {
			mode = GameMode.christmass;
		}
		try {
			Main.getInstance().start(mode, diff, userName.getText());
			// get a handle to the stage
		    Stage stage = (Stage) startBtn.getScene().getWindow();
		    // do what you have to do
		    stage.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}