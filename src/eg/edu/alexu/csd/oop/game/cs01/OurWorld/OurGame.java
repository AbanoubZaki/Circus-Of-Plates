package eg.edu.alexu.csd.oop.game.cs01.OurWorld;

import java.util.ArrayList;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.cs01.ObjectPool.FallenObjectsGenerator;
import eg.edu.alexu.csd.oop.game.cs01.objects.Character;
import eg.edu.alexu.csd.oop.game.cs01.objects.CharacterStack;
import eg.edu.alexu.csd.oop.game.cs01.objects.cs01.ModeFactory.GameMode;
import eg.edu.alexu.csd.oop.game.cs01.objects.Score;

public class OurGame implements World {

	private static int MAX_TIME = 1 * 60 * 1000; // 1 minute
	private int score;
	private long startTime;
	private List<GameObject> constant;
	private List<GameObject> movable;
	private List<GameObject> controlable;
	private GameDifficulty difficulty;
	private GameMode mode;
	private int counter;

	public OurGame(GameDifficulty difficulty, GameMode mode) {
		this.difficulty = difficulty;
		this.setMode(mode);
		startTime = System.currentTimeMillis();
		constant = mode.getConstant();
		movable = new ArrayList<GameObject>();
		// call 1st constructor only one time to set map of mode & difficulty.
		FallenObjectsGenerator.getInstance(mode, difficulty);
		for (int i = 0; i < 10; i++) {
			movable.add(FallenObjectsGenerator.getInstance().getNewObject());
		}
		controlable = mode.getControlable();
		counter = 0;
		score = 0;
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
			// TODO: handle exception
		}
		return 10;
	}

	@Override
	public int getHeight() {
		try {
			return constant.get(0).getHeight();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 20;
	}

	@Override
	public String getStatus() {
		return "Score=" + score + "   |   Time="
				+ Math.max(0, (MAX_TIME - (System.currentTimeMillis() - startTime)) / 1000);
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

	private boolean intersect(GameObject object2, GameObject object1) {
		return (Math
				.abs((object1.getX() + object1.getWidth() / 2) - (object2.getX() + object2.getWidth() / 2)) <= object1
						.getWidth())
				&& (Math.abs((object1.getY() + object1.getHeight() / 2)
						- (object2.getY() + object2.getHeight() / 2)) <= object1.getHeight());
	}

	@Override
	public boolean refresh() {
		boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME;
		try {
			for (GameObject o : movable) {
				if (intersect(o, ((Character) this.controlable.get(0)).getLeftStack())) {
					CharacterStack stack = (CharacterStack) ((Character) this.controlable.get(0)).getLeftStack();
					Score s = stack.addFallenObject(o,controlable);
					movable.remove(o);
					if (s == Score.win) {
						score++;
					} else if (s == Score.lose) {
						return false;
					}
				} else if (intersect(o, ((Character) this.controlable.get(0)).getRightStack())) {
					CharacterStack stack = (CharacterStack) ((Character) this.controlable.get(0)).getRightStack();
					Score s = stack.addFallenObject(o,controlable);
					movable.remove(o);
					if (s == Score.win) {
						score++;
					} else if (s == Score.lose) {
						return false;
					}
				} else {
					o.setY(o.getY() + 1);
					if (o.getY() == getHeight() / 3) {
						counter++;
					}
					if (o.getY() == getHeight()) {
						movable.remove(o);
						FallenObjectsGenerator.getInstance().releaseObject(o);
					}
				}
				if (counter >= 10) {
					for (int i = 0; i < 10; i++) {
						movable.add(FallenObjectsGenerator.getInstance().getNewObject());
					}
					counter = 0;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return !timeout;
	}
}
