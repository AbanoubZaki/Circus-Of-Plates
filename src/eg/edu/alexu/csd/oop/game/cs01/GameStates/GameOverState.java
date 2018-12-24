package eg.edu.alexu.csd.oop.game.cs01.GameStates;

import eg.edu.alexu.csd.oop.game.cs01.Gui.MenuBarManager;
import eg.edu.alexu.csd.oop.game.cs01.Logger4J.OurLogger;
import eg.edu.alexu.csd.oop.game.cs01.Music.Track;
import eg.edu.alexu.csd.oop.game.cs01.OurWorld.Controller;
import eg.edu.alexu.csd.oop.game.cs01.OurWorld.OurGame;
import eg.edu.alexu.csd.oop.game.cs01.leaderboard.LeaderboardManager;

public class GameOverState implements IState {

	private static GameOverState g;

	private GameOverState() {
	}

	public static GameOverState getInstance() {
		if (g == null) {
			g = new GameOverState();
		}
		return g;
	}

	@Override
	public void action() {
		Controller.getInstance().pause();
		LeaderboardManager.getInstance().addPlayer(((OurGame) MenuBarManager.getInstance().getGame()).getPlayer());
		LeaderboardManager.getInstance().writePlayers();
		Track.getInstance().getTrack("theme").stop();
		Track.getInstance().getTrack("gameover").play();
		OurLogger.info(this.getClass(), "game has ended with 0 lives");
	}

	@Override
	public CurrentState getType() {
		return CurrentState.gameOver;
	}

}
