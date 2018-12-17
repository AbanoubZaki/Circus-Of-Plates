package eg.edu.alexu.csd.oop.game.cs01.ObjectPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.cs01.OurWorld.GameDifficulty;
import eg.edu.alexu.csd.oop.game.cs01.objects.FallenObject;

public class FallenObjectsGenerator {

	private static FallenObjectsGenerator instance;
	private final int MAX_FALLEN_OBJECTS = 150;
	private Map<String, List<GameObject>> map;
	private GameDifficulty difficulty;
	private GameObject fallenObject;
	private ArrayList<GameObject> pool;
	private ArrayList<GameObject> used;

	private FallenObjectsGenerator(Map<String, List<GameObject>> map, GameDifficulty difficulty) {
		this.map = map;
		this.difficulty = difficulty;
		pool = new ArrayList<GameObject>(MAX_FALLEN_OBJECTS);
		used = new ArrayList<GameObject>(MAX_FALLEN_OBJECTS);
		for (int i = 0; i < MAX_FALLEN_OBJECTS; i++) {
			
		}
	}

	public static FallenObjectsGenerator getInstance(Map<String, List<GameObject>> map, GameDifficulty difficulty) {
		if (instance == null) {
			instance = new FallenObjectsGenerator(map, difficulty);
		}
		return instance;
	}

	public static FallenObjectsGenerator getInstance() {
		return instance;
	}

	public GameObject getNewObject() {
		Random random = new Random(difficulty.getColorsOfFallenObjects());
		try {
			fallenObject = pool.get(pool.size()-1);
			
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
