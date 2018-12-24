package eg.edu.alexu.csd.oop.game.cs01.GameStates;

import eg.edu.alexu.csd.oop.game.cs01.Music.Track;

public class RunningState implements IState {

	private static RunningState r;

	private RunningState() {
	}

	public static RunningState getInstance() {
		if (r == null) {
			r = new RunningState();
		}
		return r;
	}

	@Override
	public void action() {
		Track.getInstance().getTrack("theme").setVolume(50);
		Track.getInstance().getTrack("theme").play();
	}

	@Override
	public CurrentState getType() {
		// TODO Auto-generated method stub
		return CurrentState.running;
	}

}
