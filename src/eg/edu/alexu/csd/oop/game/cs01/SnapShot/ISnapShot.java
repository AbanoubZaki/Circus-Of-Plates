package eg.edu.alexu.csd.oop.game.cs01.SnapShot;

import eg.edu.alexu.csd.oop.game.World;

public interface ISnapShot {
	/**
	 * save game.
	 * @param ourGame
	 */
	public void saveGame(World ourGame, String fileName);
	/*
	 * load game.
	 */
	public World loadGame(String path);
}
