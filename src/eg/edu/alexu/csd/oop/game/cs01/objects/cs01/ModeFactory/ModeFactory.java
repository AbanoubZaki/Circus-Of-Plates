package eg.edu.alexu.csd.oop.game.cs01.objects.cs01.ModeFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.cs01.Enums.ObjectType;
import eg.edu.alexu.csd.oop.game.cs01.objects.Background;
import eg.edu.alexu.csd.oop.game.cs01.objects.Character;
import eg.edu.alexu.csd.oop.game.cs01.objects.FallenObject;
import eg.edu.alexu.csd.oop.game.cs01.objects.cs01.Difficulty.GameDifficulty;
import eg.edu.alexu.csd.oop.game.cs01.observer.ObservableX;
import eg.edu.alexu.csd.oop.game.cs01.observer.ObserverX;

public class ModeFactory implements IModeFactory {

	private static GameMode mode;
	private static GameDifficulty difficulty;
	private File folder;

	private static ModeFactory factory;

	private ModeFactory(GameMode mode, GameDifficulty difficulty) {
		ModeFactory.mode = mode;
		ModeFactory.difficulty = difficulty;
	}

	public static ModeFactory getInstance(GameMode mode, GameDifficulty difficulty) {
		if (factory == null) {
			factory = new ModeFactory(mode, difficulty);
		}
		if (!ModeFactory.mode.equals(mode) && !ModeFactory.difficulty.equals(difficulty)) {
			factory = new ModeFactory(mode, difficulty);
		}
		return factory;
	}

	@Override
	public void buildConstant() {
		folder = new File(mode.getPath() + "\\backgrounds");
		List<GameObject> list = new ArrayList<>();
		list.add(new Background(0, 0, folder.listFiles()));
		folder = new File(mode.getPath() + "\\live");
		for (int i = 0; i < 5; i++) {
			list.add(new Background(i * 55, 10, folder.listFiles()));
		}
		mode.setConstant(list);
	}

	@Override
	public void buildMovable() {
		folder = new File(mode.getPath() + "\\plates");
		Map<String, List<GameObject>> map = new HashMap<String, List<GameObject>>();
		for (File f1 : folder.listFiles()) {
			List<GameObject> list = new ArrayList<GameObject>();
			for (File f2 : f1.listFiles()) {
				list.add(new FallenObject(0, 0, new File[] { f2 }));
			}
			map.put(f1.getName(), list);
		}
		mode.setMapMovable(map);
	}

	@Override
	public void buildControlable() {
		folder = new File(mode.getPath() + "\\controlable");
		List<GameObject> list = new ArrayList<>();
		list.add(new Character((int) (mode.getConstant().get(0).getWidth() * 0.33),
				(int) (mode.getConstant().get(0).getHeight() * 0.7), folder.listFiles(), ObjectType.left));
		if (difficulty == GameDifficulty.hard) {
			ObservableX.getInstance().addPropertyChangeListener(ObserverX.getInstance());
			list.add(new Character((int) (mode.getConstant().get(0).getWidth() * 0.67),
					(int) (mode.getConstant().get(0).getHeight() * 0.7), folder.listFiles(), ObjectType.right));
		}
		System.out.println(mode.getConstant().get(0).getWidth() * 0.33-mode.getConstant().get(0).getWidth() * 0.67);
		mode.setControlable(list);
	}

	public GameMode createMode() {
		buildConstant();
		buildMovable();
		buildControlable();
		return ModeFactory.mode;
	}
}
