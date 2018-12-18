package eg.edu.alexu.csd.oop.game.cs01.objects.cs01.ModeFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.cs01.objects.Background;
import eg.edu.alexu.csd.oop.game.cs01.objects.Character;
import eg.edu.alexu.csd.oop.game.cs01.objects.FallenObject;

public class ModeFactory implements IModeFactory {

	private static GameMode mode;
	private File folder;

	private static ModeFactory factory;

	private ModeFactory(GameMode mode) {
		ModeFactory.mode = mode;
	}

	public static ModeFactory getInstance(GameMode mode) {
		if (factory == null) {
			factory = new ModeFactory(mode);
		}
		if (!ModeFactory.mode.equals(mode)) {
			factory = new ModeFactory(mode);
		}
		return factory;
	}

	@Override
	public void buildConstant() {
		folder = new File(mode.getPath() + "\\constant");
		List<GameObject> list = new ArrayList<>();
		list.add(new Background(0, 0, folder.listFiles()));
		mode.setConstant(list);
	}

	// @Override
	// public void buildMovable() {
	// folder = new File(mode.getPath() + "\\plates");
	// List<GameObject> list = new ArrayList<>();
	// for(File f:folder.listFiles()) {
	// list.add(new FallenObject(515, 485, f.listFiles()));
	// }
	// mode.setMovable(list);
	// }
	// x = +-137
	// y = +- 10
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
		list.add(new Character((int) (mode.getConstant().get(0).getWidth()*0.33),
				(int) (mode.getConstant().get(0).getHeight() * 0.7), folder.listFiles()));
		mode.setControlable(list);
	}

	public GameMode createMode() {
		buildConstant();
		buildMovable();
		buildControlable();
		return ModeFactory.mode;
	}
}
