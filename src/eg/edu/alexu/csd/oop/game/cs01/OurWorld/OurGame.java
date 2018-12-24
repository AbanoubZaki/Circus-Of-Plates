package eg.edu.alexu.csd.oop.game.cs01.OurWorld;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.cs01.Difficulty.GameDifficulty;
import eg.edu.alexu.csd.oop.game.cs01.DynamicLinkage.GameObjectLoader;
import eg.edu.alexu.csd.oop.game.cs01.Enums.Score;
import eg.edu.alexu.csd.oop.game.cs01.GameStates.GameOverState;
import eg.edu.alexu.csd.oop.game.cs01.GameStates.IState;
import eg.edu.alexu.csd.oop.game.cs01.GameStates.RunningState;
import eg.edu.alexu.csd.oop.game.cs01.Iterator.GameObjectCollection;
import eg.edu.alexu.csd.oop.game.cs01.Iterator.IGameObjectCollection;
import eg.edu.alexu.csd.oop.game.cs01.Logger4J.OurLogger;
import eg.edu.alexu.csd.oop.game.cs01.ModeFactory.GameMode;
import eg.edu.alexu.csd.oop.game.cs01.ModeFactory.ModeFactory;
import eg.edu.alexu.csd.oop.game.cs01.Music.Track;
import eg.edu.alexu.csd.oop.game.cs01.ObjectPool.FallenObjectsGenerator;
import eg.edu.alexu.csd.oop.game.cs01.RefreshDelegation.Refresh;
import eg.edu.alexu.csd.oop.game.cs01.SnapShot.FallenObjectSnapShot;
import eg.edu.alexu.csd.oop.game.cs01.SnapShot.GameSnapShot;
import eg.edu.alexu.csd.oop.game.cs01.leaderboard.Player;
import eg.edu.alexu.csd.oop.game.cs01.objects.AbstractFallenObject;
import eg.edu.alexu.csd.oop.game.cs01.objects.Background;
import eg.edu.alexu.csd.oop.game.cs01.objects.Character;
import eg.edu.alexu.csd.oop.game.cs01.objects.CharacterStack;

public class OurGame implements World {

	private static int MAX_TIME = 2 * 60 * 1000; // 1 minute

	private Player player;
//	private String name;
//	private int score;
	private IState currentState;

	private int lives;
	private long startTime;
	private IGameObjectCollection constant;
	private IGameObjectCollection movable;
	private IGameObjectCollection controlable;
	private GameDifficulty difficulty;
	private GameMode mode;
	private int counter;
	private double passedTime;

	public double getPassedTime() {
		return passedTime;
	}

	private javafx.util.Duration duration;

	public OurGame() {
		GameObjectLoader.getInstance().loadClasses();
	}

	public OurGame(GameDifficulty difficulty, GameMode mode, String name) {
		GameObjectLoader.getInstance().loadClasses();
		player = new Player();
		player.setName(name);
		lives = 5;
		this.difficulty = difficulty;
		this.setMode(ModeFactory.getInstance(mode, difficulty).createMode());
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
		player.setScore(0);
		this.setCurrentState(RunningState.getInstance());
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
		int remainingTime = (int) (MAX_TIME - (this.passedTime = System.currentTimeMillis() - startTime));
		String status = "Score=" + player.getScore() + "   |   Time=" + Math.max(0, remainingTime / 1000);
		for (int i = 0; i < difficulty.getNoOfCharacters(); i++) {
			status += "   |    left = " + ((CharacterStack) (((Character) controlable.get(i)).getLeftStack())).getSize()
					+ "    |    right = "
					+ ((CharacterStack) (((Character) controlable.get(i)).getRightStack())).getSize();
		}
		status += "   |   lives = " + lives;
		return status;
	}

	/**
	 * @return user name.
	 */
	public String getName() {
		return player.getName();
	}

	public Player getPlayer() {
		return player;
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
		return player.getScore();
	}

	/**
	 * @return the difficulty
	 */
	public GameDifficulty getDifficulty() {
		return difficulty;
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

	public javafx.util.Duration getDuration() {
		return duration;
	}

	@Override
	public boolean refresh() {
		boolean timeout = (this.passedTime = System.currentTimeMillis() - startTime) > MAX_TIME;
		if (lives == 0) {
			this.setCurrentState(GameOverState.getInstance());
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
						OurLogger.info(this.getClass(), "intersection with good object happened");
						movable.remove(o);
						counter++;
						FallenObjectsGenerator.getInstance().releaseObject(o);
						OurLogger.info(this.getClass(), "plate released into pool");
						Track.getInstance().getTrack("present").play();
						player.setScore(player.getScore() + 1);
						break;
					} else if (Refresh.getInstance().intersectWithBad(o,
							((Character) this.controlable.get(i)).getLeftStack(),
							((Character) this.controlable.get(i)).getRightStack())) {
						OurLogger.info(this.getClass(), "intersection with good object happened");
						movable.remove(o);
						counter++;
						FallenObjectsGenerator.getInstance().releaseObject(o);
						OurLogger.info(this.getClass(), "plate released into pool");
						lives--;
						constant.remove(constant.size() - 1);
						Track.getInstance().getTrack("bomb").play();

						break;
					} else if (Refresh.getInstance().intersectWithPlateLeft(o,
							((Character) this.controlable.get(i)).getLeftStack())) {
						OurLogger.info(this.getClass(), "intersection of left stack with a plate happened");
						CharacterStack stack = (CharacterStack) ((Character) this.controlable.get(i)).getLeftStack();
						Score s = stack.addFallenObject(o, controlable.getList());
						movable.remove(o);
						objectRemoved = true;
						if (s == Score.win) {
							Track.getInstance().getTrack("present").play();
							player.setScore(player.getScore() + 1);
						} else if (s == Score.lose) {
							this.setCurrentState(GameOverState.getInstance());
							return false;
						}
						break;
					} else if (Refresh.getInstance().intersectWithPlateRight(o,
							((Character) this.controlable.get(i)).getRightStack())) {
						OurLogger.info(this.getClass(), "intersection of right stack with a plate happened");
						CharacterStack stack = (CharacterStack) ((Character) this.controlable.get(i)).getRightStack();
						Score s = stack.addFallenObject(o, controlable.getList());
						movable.remove(o);
						objectRemoved = true;
						if (s == Score.win) {
							Track.getInstance().getTrack("present").play();
							player.setScore(player.getScore() + 1);
						} else if (s == Score.lose) {
							this.setCurrentState(GameOverState.getInstance());
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
			this.setCurrentState(GameOverState.getInstance());
		}
		return !timeout;
	}

	public GameSnapShot getSnapShot() {
		this.setCurrentState(RunningState.getInstance());
		return new GameSnapShot(this);
	}

	/**
	 * @return the counter
	 */
	public int getCounter() {
		return counter;
	}

	public IState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(IState currentState) {
		this.currentState = currentState;
		this.currentState.action();
	}

	@SuppressWarnings("unchecked")
	public void loadGame(GameSnapShot snapShot) {
		player = snapShot.getPlayer();
		this.currentState = RunningState.getInstance();
		this.lives = snapShot.getLives();
		this.difficulty = snapShot.getDifficulty();
		this.duration = snapShot.getThemeDuration();
		this.mode = snapShot.getMode();
		this.mode = ModeFactory.getInstance(this.mode, this.difficulty).loadMode();
		FallenObjectsGenerator.getInstance(mode, difficulty);
		ArrayList<GameObject> list = new ArrayList<GameObject>();
		for (int i = 0; i < snapShot.getConstant().size(); i++) {
			Background backGround = new Background();
			backGround.loadBackGround(snapShot.getConstant().get(i));
			list.add(backGround);
		}
		this.constant = new GameObjectCollection((List<GameObject>) list.clone());
		list = new ArrayList<GameObject>();
		for (int i = 0; i < snapShot.getMovable().size(); i++) {
			AbstractFallenObject o = GameObjectLoader.getInstance()
					.newInstance(((FallenObjectSnapShot) snapShot.getMovable().get(i)).getClassName());
			o.loadFallenObject((FallenObjectSnapShot) snapShot.getMovable().get(i));
			list.add(o);
		}
		this.movable = new GameObjectCollection((List<GameObject>) list.clone());
		List<GameObject> controllableList = new ArrayList<GameObject>();
		for (int i = 0; i < snapShot.getControlableCharacters().size(); i++) {
			Character o = new Character();
			((Character) o).loadCharacter(snapShot.getControlableCharacters().get(i));
			controllableList.add(o);
		}
		list = new ArrayList<GameObject>();
		for (GameObject c : controllableList) {
			for (GameObject o : ((CharacterStack) ((Character) c).getLeftStack()).getStack()) {
				list.add(o);
			}
			for (GameObject o : ((CharacterStack) ((Character) c).getRightStack()).getStack()) {
				list.add(o);
			}
		}
		controllableList.addAll(list);
		this.controlable = new GameObjectCollection(controllableList);
		startTime = System.currentTimeMillis();
		counter = snapShot.getCounter();
		MAX_TIME -= snapShot.getPassedTime();
		this.mode.setConstant(getConstantObjects());
		this.mode.setControlable(getControlableObjects());

	}
}
