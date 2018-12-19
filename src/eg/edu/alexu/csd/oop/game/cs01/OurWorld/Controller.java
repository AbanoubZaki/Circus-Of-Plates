package eg.edu.alexu.csd.oop.game.cs01.OurWorld;

import eg.edu.alexu.csd.oop.game.GameEngine.GameController;
import eg.edu.alexu.csd.oop.game.World;

public class Controller implements GameController {
	
	private static Controller c;
	
	private Controller() {
	}

	public static Controller getInstance() {
		if (c == null) {
			c = new Controller();
		}
		return c;
	}
	
	private GameController gameController;

	public GameController getGameController() {
		return gameController;
	}

	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}
	
	@Override
	public void changeWorld(World game) {
		gameController.changeWorld(game);
	}

	@Override
	public void pause() {
		gameController.pause();
	}

	@Override
	public void resume() {
		gameController.resume();
	}
}
