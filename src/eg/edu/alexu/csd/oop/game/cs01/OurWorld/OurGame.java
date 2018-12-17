package eg.edu.alexu.csd.oop.game.cs01.OurWorld;

import java.util.ArrayList;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;

public class OurGame implements World {

	private static int MAX_TIME = 1 * 60 * 1000;	// 1 minute
	private int score = 0;
	private long startTime;
	private  List<GameObject> constant;
	private  List<GameObject> movable;
	private  List<GameObject> controlable;
	private GameDifficulty difficulty;
	private GameMode mode;
	
	public OurGame(GameDifficulty difficulty, GameMode mode) {
		this.difficulty = difficulty;
		this.mode = mode;
		startTime = System.currentTimeMillis();
		constant = new ArrayList<>();
		movable = new ArrayList<>();
		controlable = new ArrayList<>();
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
		return constant.get(0).getWidth();
	}

	@Override
	public int getHeight() {
		return constant.get(0).getHeight();
	}

	@Override
	public String getStatus() {
		return "Score=" + score + "   |   Time=" + Math.max(0, (MAX_TIME - (System.currentTimeMillis()-startTime))/1000);	
	}

	@Override
	public int getSpeed() {
		return difficulty.getSpeed();
	}

	@Override
	public int getControlSpeed() {
		return 0;
	}

	@Override
	public boolean refresh() {
		
		return false;
	}
}
