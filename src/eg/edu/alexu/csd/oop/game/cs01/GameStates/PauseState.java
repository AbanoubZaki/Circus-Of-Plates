package eg.edu.alexu.csd.oop.game.cs01.GameStates;

import eg.edu.alexu.csd.oop.game.cs01.Music.Track;
import eg.edu.alexu.csd.oop.game.cs01.OurWorld.Controller;

public class PauseState implements IState {
	private static PauseState p;

	private PauseState() {
	}

	public static PauseState getInstance() {
		if (p == null) {
			p = new PauseState();
		}
		return p;
	}

	@Override
	public void action() {
		Controller.getInstance().pause();
		Track.getInstance().getTrack("theme").pause();
	}

	@Override
	public CurrentState getType() {
		// TODO Auto-generated method stub
		return CurrentState.paused;
	}

}
