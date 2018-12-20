package eg.edu.alexu.csd.oop.game.cs01.ObjectPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.cs01.Difficulty.GameDifficulty;
import eg.edu.alexu.csd.oop.game.cs01.objects.FallenObject;
import eg.edu.alexu.csd.oop.game.cs01.objects.cs01.ModeFactory.GameMode;

public class FallenObjectsGenerator {

	private static FallenObjectsGenerator instance;
	private final int MAX_FALLEN_OBJECTS = 75;
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
		for (int i = 0; i < MAX_FALLEN_OBJECTS; i++) {
			Random random = new Random();
			// 2 is the number of the extra objects.
			int r = random.nextInt(difficulty.getColorsOfFallenObjects() + 2);
			if (i%14 <8 || (i%14 > 8 && i%14 <13)) {
				r = (r + 2) % difficulty.getColorsOfFallenObjects() + 2;
			} else if (i % 8 == 8) {
				r = 0;
			} else if (i % 8 == 13) {
				r = 1;
			}
			// System.out.println(r + 1);
			for (GameObject o : map.get(Integer.toString(r))) {
				pool.add(((FallenObject) o).clone());
			}
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
			Random random = new Random();
			int r = random.nextInt(pool.size());
			fallenObject = pool.get(r);
			pool.remove(r);
			fallenObject.setX(random.nextInt(mode.getConstant().get(0).getWidth() - 35));
			fallenObject.setY(random.nextInt(30));
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
