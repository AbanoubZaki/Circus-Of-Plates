package eg.edu.alexu.csd.oop.game.cs01.OurWorld;

import java.util.ArrayList;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.cs01.GameStates.CurrentState;
import eg.edu.alexu.csd.oop.game.cs01.ObjectPool.FallenObjectsGenerator;
import eg.edu.alexu.csd.oop.game.cs01.RefreshDelegation.Refresh;
import eg.edu.alexu.csd.oop.game.cs01.objects.Character;
import eg.edu.alexu.csd.oop.game.cs01.objects.CharacterStack;
import eg.edu.alexu.csd.oop.game.cs01.objects.FallenObject;
import eg.edu.alexu.csd.oop.game.cs01.objects.Score;
import eg.edu.alexu.csd.oop.game.cs01.objects.cs01.Difficulty.GameDifficulty;
import eg.edu.alexu.csd.oop.game.cs01.objects.cs01.ModeFactory.GameMode;

public class OurGame implements World {

	private static int MAX_TIME = 2 * 60 * 1000; // 1 minute
	private int lives;
	private int score;
	private long startTime;
	private List<GameObject> constant;
	private List<GameObject> movable;
	private List<GameObject> controlable;
	private GameDifficulty difficulty;
	private GameMode mode;
	private CurrentState state;
	private int counter;

	public OurGame(GameDifficulty difficulty, GameMode mode) {
		lives = 5;
		this.difficulty = difficulty;
		this.setMode(mode);
		startTime = System.currentTimeMillis();
		constant = mode.getConstant();
		movable = new ArrayList<GameObject>();
		// call 1st constructor only one time to set map of mode & difficulty.
		FallenObjectsGenerator.getInstance(mode, difficulty);
		for (int i = 0; i < 5; i++) {
			movable.add(FallenObjectsGenerator.getInstance().getNewObject());
		}
		controlable = mode.getControlable();
		counter = 0;
		score = 0;
		state = CurrentState.running;
	}

	@Override
	public List<GameObject> getConstantObjects() {
		return constant;
	}

	@Override
	public List<GameObject> getMovableObjects() {
		return movable;
	}

	@Override
	public List<GameObject> getControlableObjects() {
		return controlable;
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

	// private boolean intersect(GameObject object2, GameObject object1) {
	// return (Math
	// .abs((object1.getX() + object1.getWidth() / 2) - (object2.getX() +
	// object2.getWidth() / 2)) <= object1
	// .getWidth())
	// && (Math.abs((object1.getY() + object1.getHeight() / 2)
	// - (object2.getY() + object2.getHeight() / 2)) <= object1.getHeight());
	// }

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
			return false;
		}
		try {
			int MovingStrategy = 0;
			for (GameObject o : movable) {
				if (difficulty == GameDifficulty.hard) {
					difficulty.moveHard(o, MovingStrategy);
				}
				o.setY(o.getY() + 1);
				boolean objectRemoved = false;
				for (int i = 0; i < difficulty.getNoOfCharacters(); i++) {
					if (Refresh.getInstance().intersectWithGood(o, ((Character) this.controlable.get(i)).getLeftStack(),
							((Character) this.controlable.get(i)).getRightStack())) {
						movable.remove(o);
						objectRemoved = true;
						score++;
						break;
					} else if (Refresh.getInstance().intersectWithBad(o,
							((Character) this.controlable.get(i)).getLeftStack(),
							((Character) this.controlable.get(i)).getRightStack())) {
						movable.remove(o);
						objectRemoved = true;
						lives--;
						constant.remove(constant.size() - 1);
						break;
					} else if (Refresh.getInstance().intersectWithPlateLeft(o,
							((Character) this.controlable.get(i)).getLeftStack())) {
						CharacterStack stack = (CharacterStack) ((Character) this.controlable.get(i)).getLeftStack();
						Score s = stack.addFallenObject(o, controlable, getWidth());
						movable.remove(o);
						objectRemoved = true;
						if (s == Score.win) {
							score++;
						} else if (s == Score.lose) {
							Controller.getInstance().pause();
							state = CurrentState.gameOver;
							// System.out.println("1");
							return false;
						}
						break;
					} else if (Refresh.getInstance().intersectWithPlateRight(o,
							((Character) this.controlable.get(i)).getRightStack())) {
						CharacterStack stack = (CharacterStack) ((Character) this.controlable.get(i)).getRightStack();
						Score s = stack.addFallenObject(o, controlable, getWidth());
						movable.remove(o);
						objectRemoved = true;
						if (s == Score.win) {
							score++;
						} else if (s == Score.lose) {
							Controller.getInstance().pause();
							state = CurrentState.gameOver;
							// System.out.println("2");
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
					}
				}
			}
			if (counter >= 5) {
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
			// System.out.println("3");
		}
		return !timeout;
	}
}
