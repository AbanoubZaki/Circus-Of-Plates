package eg.edu.alexu.csd.oop.game.cs01.SnapShot;

import java.util.ArrayList;
import java.util.List;

import eg.edu.alexu.csd.oop.game.cs01.Difficulty.GameDifficulty;
import eg.edu.alexu.csd.oop.game.cs01.GameStates.CurrentState;
import eg.edu.alexu.csd.oop.game.cs01.ModeFactory.GameMode;
import eg.edu.alexu.csd.oop.game.cs01.Music.Track;
import eg.edu.alexu.csd.oop.game.cs01.OurWorld.OurGame;
import javafx.util.Duration;

public class GameSnapShot {
	private List<BackGroundSnapShot> constant;
	private List<FallenObjectSnapShot> movable;
	private List<CharacterSnapShot> controlableCharacters;
	private int lives;
	private int score;
	private GameMode mode;
	private CurrentState state;
	private double reminingTime;
	private GameDifficulty difficulty;
	private Duration themeDuration;
	private int counter;

	public GameSnapShot() {
	}

	public GameSnapShot(OurGame game) {
		this.lives = game.getLives();
		this.score = game.getScore();
		this.mode = game.getMode();
		this.state = CurrentState.paused;
		this.reminingTime = game.getRemainingTime();
		this.difficulty = game.getDifficulty();
		this.themeDuration = Track.getInstance().getTrack("theme").getCurrentTime();
		this.counter = game.getCounter();
		constant = new ArrayList<BackGroundSnapShot>();
		movable = new ArrayList<FallenObjectSnapShot>();
		controlableCharacters = new ArrayList<CharacterSnapShot>();
		for (int i = 0; i < game.getConstantObjects().size(); i++) {
			BackGroundSnapShot snapShot = new BackGroundSnapShot(game.getConstantObjects().get(i));
			constant.add(snapShot);
		}
		for (int i = 0; i < game.getMovableObjects().size(); i++) {
			FallenObjectSnapShot snapShot = new FallenObjectSnapShot(game.getMovableObjects().get(i));
			movable.add(snapShot);
		}
		for (int i = 0; i < game.getDifficulty().getNoOfCharacters(); i++) {
			CharacterSnapShot snapShot = new CharacterSnapShot(game.getControlableObjects().get(i));
			controlableCharacters.add(snapShot);
		}
	}

	/**
	 * @return the counter
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * @return the controlableCharacters
	 */
	public List<CharacterSnapShot> getControlableCharacters() {
		return controlableCharacters;
	}

	/**
	 * @return the difficulty
	 */
	public GameDifficulty getDifficulty() {
		return difficulty;
	}

	/**
	 * @return the reminingTime
	 */
	public double getReminingTime() {
		return reminingTime;
	}

	/**
	 * @return the constant
	 */
	public List<BackGroundSnapShot> getConstant() {
		return constant;
	}

	/**
	 * @return the movable
	 */
	public List<FallenObjectSnapShot> getMovable() {
		return movable;
	}

	/**
	 * @return the lives
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @return the mode
	 */
	public GameMode getMode() {
		return mode;
	}

	/**
	 * @return the state
	 */
	public CurrentState getState() {
		return state;
	}

	/**
	 * @return theme stopping duration
	 */
	public Duration getThemeDuration() {
		return themeDuration;
	}

}
