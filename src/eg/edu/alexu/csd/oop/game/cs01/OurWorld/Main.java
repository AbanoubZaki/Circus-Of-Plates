package eg.edu.alexu.csd.oop.game.cs01.OurWorld;

import javax.swing.JFrame;

import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.cs01.Difficulty.GameDifficulty;
import eg.edu.alexu.csd.oop.game.cs01.Gui.MenuBarManager;
import eg.edu.alexu.csd.oop.game.cs01.ModeFactory.GameMode;
import eg.edu.alexu.csd.oop.game.cs01.ModeFactory.ModeFactory;

public class Main {

	private static Main m;
	
	private Main() {
		name = new String();
	}
	
	public static Main getInstance () {
		if (m == null) {
			m = new Main();
		}
		return m;
	}
	
	private static String name;
	private static GameDifficulty difficulty;
	private static GameMode mode;
	
	public String getName() {
		return name;
	}
	
	public GameDifficulty getDifficulty() {
		return difficulty;
	}

	public GameMode getMode() {
		return mode;
	}

	public void start(GameMode mode, GameDifficulty difficulty, String name) throws Exception {
		MenuBarManager.setGame(new OurGame(difficulty, mode, name));
		Controller.getInstance().setGameController(GameEngine.start("World of plates", MenuBarManager.getGame(),
				MenuBarManager.getInstance().getMenuBar()));
		MenuBarManager.setFrame((JFrame) MenuBarManager.getInstance().getMenuBar().getParent().getParent().getParent());
		MenuBarManager.getInstance().setMenuBar();
	}

}
