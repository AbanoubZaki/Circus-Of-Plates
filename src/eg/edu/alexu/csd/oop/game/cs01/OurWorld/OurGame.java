package eg.edu.alexu.csd.oop.game.cs01.OurWorld;

import java.util.ArrayList;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.cs01.ObjectPool.FallenObjectsGenerator;
import eg.edu.alexu.csd.oop.game.cs01.objects.cs01.ModeFactory.GameMode;

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
		this.setMode(mode);
		startTime = System.currentTimeMillis();
		constant = mode.getConstant();
		movable = new ArrayList<GameObject>();
		//call 1st constructor only one time to set map of mode & difficulty.
		FallenObjectsGenerator.getInstance(mode.getMapMovable(), difficulty);
		controlable = mode.getControlable();
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
		return "Score=" + score + "   |   Time=" + Math.max(0, (MAX_TIME - (System.currentTimeMillis()-startTime))/1000);	
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

	@Override
	public boolean refresh() {
//		movable.get(0).setX(movable.get(0).getX()+1);
//		movable.get(0).setY(movable.get(0).getY()+1);
//		movable.get(1).setX(movable.get(1).getX()-1);
//		movable.get(2).setY(movable.get(2).getY()+1);
//		movable.get(3).setX(movable.get(3).getX()+1);
//		movable.get(4).setX(movable.get(4).getX()-1);
//		movable.get(4).setY(movable.get(4).getY()+1);
		return true;
	}
}
