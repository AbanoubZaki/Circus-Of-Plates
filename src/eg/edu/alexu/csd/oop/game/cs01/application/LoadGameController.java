package eg.edu.alexu.csd.oop.game.cs01.application;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.cs01.Gui.MenuBarManager;
import eg.edu.alexu.csd.oop.game.cs01.Music.Track;
import eg.edu.alexu.csd.oop.game.cs01.OurWorld.Controller;
import eg.edu.alexu.csd.oop.game.cs01.OurWorld.OurGame;
import eg.edu.alexu.csd.oop.game.cs01.SnapShot.SnapShot;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoadGameController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		File folder = new File("SavedGames");
		ArrayList<String> fileNames = new ArrayList<>();
		for (int i = 0; i < folder.listFiles().length; i++) {
			fileNames.add(folder.listFiles()[i].getName().substring(0, folder.listFiles()[i].getName().length()-5));
		}
		ObservableList<String> savedGames =  FXCollections.observableArrayList(fileNames);
		myList.setItems(savedGames);
	}
	
    @FXML
    private ListView<String> myList;

    @FXML
    void mouseClickAct(MouseEvent event) {
    	try {
        	Track.getInstance().getTrack("theme").stop();
		} catch (Exception e) {
		}
		World game = SnapShot.getSnapShot().loadGame(myList.getSelectionModel().getSelectedItem());
		MenuBarManager.setGame(game);
		
		Controller.getInstance().setGameController(
				GameEngine.start("Circus of plates", game, MenuBarManager.getInstance().getMenuBar()));
		Controller.getInstance().changeWorld(game);
		MenuBarManager.setFrame((JFrame) MenuBarManager.getInstance().getMenuBar().getParent().getParent().getParent());
		Track.getInstance().getTrack("theme").seek(((OurGame) game).getDuration());
		Track.getInstance().getTrack("theme").play();
		MenuBarManager.getInstance().setMenuBar();
		// get a handle to the stage
	    Stage stage = (Stage) myList.getScene().getWindow();
	    // do what you have to do
	    stage.close();
    }

}
