package eg.edu.alexu.csd.oop.game.cs01.ObjectPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.cs01.OurWorld.GameDifficulty;
import eg.edu.alexu.csd.oop.game.cs01.objects.FallenObject;
import eg.edu.alexu.csd.oop.game.cs01.objects.cs01.ModeFactory.GameMode;

public class FallenObjectsGenerator {

	private static FallenObjectsGenerator instance;
	private final int MAX_FALLEN_OBJECTS = 150;
	private Map<String, List<GameObject>> map;
	@SuppressWarnings("unused")
	private GameDifficulty difficulty;
	private GameObject fallenObject;
	private GameMode mode;
	private ArrayList<GameObject> pool;
	private ArrayList<GameObject> used;

	private FallenObjectsGenerator(GameMode mode, GameDifficulty difficulty) {
		this.mode = mode;
		this.map = mode.getMapMovable();
		this.difficulty = difficulty;
		pool = new ArrayList<GameObject>(MAX_FALLEN_OBJECTS);
		used = new ArrayList<GameObject>(MAX_FALLEN_OBJECTS);
		final int noOfAvailableColours = difficulty.getColorsOfFallenObjects();
		Random random = new Random(noOfAvailableColours+1);
		for (int i = 0; i < MAX_FALLEN_OBJECTS; i++) {
			pool.add(((FallenObject) map.get(Integer.toString(random.nextInt()))).clone());
		}
	}

	public static FallenObjectsGenerator getInstance(GameMode mode, GameDifficulty difficulty) {
		if (instance == null) {
			instance = new FallenObjectsGenerator(mode, difficulty);
		}
		return instance;
	}

	public static FallenObjectsGenerator getInstance() {
		return instance;
	}

	public GameObject getNewObject() {
		try {
			Random random = new Random(mode.getConstant().get(0).getWidth());
			fallenObject = pool.get(pool.size()-1);
			pool.remove(pool.size()-1);
			fallenObject.setX(random.nextInt());
			fallenObject.setY(0);
			used.add(fallenObject);
			return fallenObject;
		} catch (Exception e) {
			
		}
		return null;
	}
	
	public void releaseObject(GameObject oldObject) {
		used.remove(oldObject);
		oldObject.setX(0);
		oldObject.setY(0);
		pool.add(oldObject);
	}
}
