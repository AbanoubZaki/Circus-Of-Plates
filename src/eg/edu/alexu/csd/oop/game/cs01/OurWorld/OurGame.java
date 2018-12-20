package eg.edu.alexu.csd.oop.game.cs01.OurWorld;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.cs01.Difficulty.GameDifficulty;
import eg.edu.alexu.csd.oop.game.cs01.Enums.Score;
import eg.edu.alexu.csd.oop.game.cs01.GameStates.CurrentState;
import eg.edu.alexu.csd.oop.game.cs01.Iterator.GameObjectCollection;
import eg.edu.alexu.csd.oop.game.cs01.Iterator.IGameObjectCollection;
import eg.edu.alexu.csd.oop.game.cs01.Logger4J.OurLogger;
import eg.edu.alexu.csd.oop.game.cs01.ModeFactory.GameMode;
import eg.edu.alexu.csd.oop.game.cs01.Music.Track;
import eg.edu.alexu.csd.oop.game.cs01.ObjectPool.FallenObjectsGenerator;
import eg.edu.alexu.csd.oop.game.cs01.RefreshDelegation.Refresh;
import eg.edu.alexu.csd.oop.game.cs01.objects.Character;
import eg.edu.alexu.csd.oop.game.cs01.objects.CharacterStack;

public class OurGame implements World {

	private static int MAX_TIME = 2 * 60 * 1000; // 1 minute
	private int lives;
	private int score;
	private long startTime;
	private IGameObjectCollection constant;
	private IGameObjectCollection movable;
	private IGameObjectCollection controlable;
	private GameDifficulty difficulty;
	private GameMode mode;
	private CurrentState state;
	private int counter;

	public OurGame() {
	}

	public OurGame(GameDifficulty difficulty, GameMode mode) {
		OurLogger.info(this.getClass(), "a new game of " + mode + " mode & " + difficulty + " difficulty has started");
		lives = 5;
		this.difficulty = difficulty;
		this.setMode(mode);
		startTime = System.currentTimeMillis();
		constant = new GameObjectCollection(mode.getConstant());
		movable = new GameObjectCollection(new ArrayList<GameObject>());
		// call 1st constructor only one time to set map of mode & difficulty.
		FallenObjectsGenerator.getInstance(mode, difficulty);
		for (int i = 0; i < 5; i++) {
			movable.add(FallenObjectsGenerator.getInstance().getNewObject());
		}
		controlable = new GameObjectCollection(mode.getControlable());
		counter = 0;
		score = 0;
		state = CurrentState.running;
		Track.getInstance().getTrack("theme").setVolume(10);
		Track.getInstance().getTrack("theme").play();
	}

	@Override
	public List<GameObject> getConstantObjects() {
		return constant.getList();
	}

	@Override
	public List<GameObject> getMovableObjects() {
		return movable.getList();
	}

	@Override
	public List<GameObject> getControlableObjects() {
		return controlable.getList();
	}

	@Override
	public int getWidth() {
		try {
			return constant.get(0).getWidth();

		} catch (Exception e) {
		}
		return 10;
	}

	@Override
	public int getHeight() {
		try {
			return constant.get(0).getHeight();
		} catch (Exception e) {
		}
		return 20;
	}

	@Override
	public String getStatus() {
		String status = "Score=" + score + "   |   Time="
				+ Math.max(0, (MAX_TIME - (System.currentTimeMillis() - startTime)) / 1000);
		for (int i = 0; i < difficulty.getNoOfCharacters(); i++) {
			status += "   |    left = " + ((CharacterStack) (((Character) controlable.get(i)).getLeftStack())).getSize()
					+ "    |    right = "
					+ ((CharacterStack) (((Character) controlable.get(i)).getRightStack())).getSize();
		}
		status += "   |   lives = " + lives;
		return status;
	}

	@Override
	public int getSpeed() {
		return difficulty.getSpeed();
	}

	@Override
	public int getControlSpeed() {
		return 20;
	}

	public GameMode getMode() {
		return mode;
	}

	public void setMode(GameMode mode) {
		this.mode = mode;
	}

	public CurrentState getState() {
		return state;
	}

	public void setState(CurrentState state) {
		this.state = state;
	}

	@Override
	public boolean refresh() {
		boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME;
		if (lives == 0) {
			Controller.getInstance().pause();
			state = CurrentState.gameOver;
			Track.getInstance().getTrack("theme").stop();
			Track.getInstance().getTrack("gameover").play();
			OurLogger.info(this.getClass(), "game has ended with 0 lives");
			return false;
		}
		try {
			int MovingStrategy = 0;
			Iterator<GameObject> iterator = movable.iterator();
			while (iterator.hasNext()) {
				GameObject o = iterator.next();
				if (difficulty == GameDifficulty.hard) {
					if (MovingStrategy == 0) {
						o.setX(o.getX() + 1);
						MovingStrategy = 1;
					} else if (MovingStrategy == 1) {
						o.setX(o.getX() - 1);
						MovingStrategy = 0;
					}
				}
				o.setY(o.getY() + 1);
				boolean objectRemoved = false;
				for (int i = 0; i < difficulty.getNoOfCharacters(); i++) {
					if (Refresh.getInstance().intersectWithGood(o, ((Character) this.controlable.get(i)).getLeftStack(),
							((Character) this.controlable.get(i)).getRightStack())) {
						movable.remove(o);
						objectRemoved = true;
						Track.getInstance().getTrack("present").play();
						score++;
						OurLogger.info(this.getClass(), "score increased by 1");
						break;
					} else if (Refresh.getInstance().intersectWithBad(o,
							((Character) this.controlable.get(i)).getLeftStack(),
							((Character) this.controlable.get(i)).getRightStack())) {
						movable.remove(o);
						objectRemoved = true;
						lives--;
						OurLogger.info(this.getClass(), "a bomb hit an character");
						constant.remove(constant.size() - 1);
						Track.getInstance().getTrack("bomb").play();

						break;
					} else if (Refresh.getInstance().intersectWithPlateLeft(o,
							((Character) this.controlable.get(i)).getLeftStack())) {
						CharacterStack stack = (CharacterStack) ((Character) this.controlable.get(i)).getLeftStack();
						Score s = stack.addFallenObject(o, controlable.getList(), getWidth(), i);
						movable.remove(o);
						objectRemoved = true;
						if (s == Score.win) {
							score++;
							OurLogger.info(this.getClass(), "3 plates of same colour has been collected");
						} else if (s == Score.lose) {
							Controller.getInstance().pause();
							state = CurrentState.gameOver;
							// System.out.println("1");
							Track.getInstance().getTrack("theme").stop();
							Track.getInstance().getTrack("gameover").play();
							OurLogger.info(this.getClass(), "character stack full with 15 plate");
							return false;
						}
						break;
					} else if (Refresh.getInstance().intersectWithPlateRight(o,
							((Character) this.controlable.get(i)).getRightStack())) {
						CharacterStack stack = (CharacterStack) ((Character) this.controlable.get(i)).getRightStack();
						Score s = stack.addFallenObject(o, controlable.getList(), getWidth(), i);
						movable.remove(o);
						objectRemoved = true;
						if (s == Score.win) {
							score++;
							OurLogger.info(this.getClass(), "3 plates of same colour has been collected");
						} else if (s == Score.lose) {
							Controller.getInstance().pause();
							state = CurrentState.gameOver;
							// System.out.println("2");
							Track.getInstance().getTrack("theme").stop();
							Track.getInstance().getTrack("gameover").play();
							OurLogger.info(this.getClass(), "character stack full with 15 plate");
							return false;
						}
						break;
					}
				}
				if (!objectRemoved) {
					if (o.getY() == getHeight() / 3) {
						counter++;
					}
					if (o.getY() == getHeight() - controlable.get(0).getHeight() + 20) {
						movable.remove(o);
						FallenObjectsGenerator.getInstance().releaseObject(o);
						OurLogger.info(this.getClass(), "plate released into pool");
					}
				}
			}
			if (counter >= 5) {
				OurLogger.info(this.getClass(), "plates are added to screen");
				for (int i = 0; i < 5; i++) {
					movable.add(FallenObjectsGenerator.getInstance().getNewObject());
				}
				counter = 0;
			}
		} catch (Exception e) {
		}
		if (timeout) {
			Controller.getInstance().pause();
			state = CurrentState.gameOver;
			Track.getInstance().getTrack("theme").stop();
			Track.getInstance().getTrack("gameover").play();
			OurLogger.info(this.getClass(), "game is over, time ended");

			// System.out.println("3");
		}
		return !timeout;
	}
}
