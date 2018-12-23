package eg.edu.alexu.csd.oop.game.cs01.OurWorld;

import javax.swing.JFrame;

import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.cs01.Difficulty.GameDifficulty;
import eg.edu.alexu.csd.oop.game.cs01.Gui.MenuBarManager;
import eg.edu.alexu.csd.oop.game.cs01.ModeFactory.GameMode;
import eg.edu.alexu.csd.oop.game.cs01.ModeFactory.ModeFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {

		GameDifficulty d = GameDifficulty.medium;
		GameMode m = GameMode.christmass;

		MenuBarManager.setGame(new OurGame(d, ModeFactory.getInstance(m, d).createMode()));
		Controller.getInstance().setGameController(GameEngine.start("Circus of plates", MenuBarManager.getGame(),
				MenuBarManager.getInstance().getMenuBar()));
		MenuBarManager.setFrame((JFrame) MenuBarManager.getInstance().getMenuBar().getParent().getParent().getParent());
		MenuBarManager.getInstance().setMenuBar();
	}

	@Override
	public void start(Stage arg0) throws Exception {

	}

}
