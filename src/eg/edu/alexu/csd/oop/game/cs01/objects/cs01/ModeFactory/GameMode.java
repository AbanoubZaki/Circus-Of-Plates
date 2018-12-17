package eg.edu.alexu.csd.oop.game.cs01.objects.cs01.ModeFactory;

import java.util.List;
import java.util.Map;

import eg.edu.alexu.csd.oop.game.GameObject;

public enum GameMode {
	christmass("resources\\christmass"), robot("resources\\robot");

	private GameMode(String path) {
		this.path = path;
	}

	private List<GameObject> constant;
	private List<GameObject> movable;
	private List<GameObject> controlable;
	private Map<String, List<GameObject>> mapMovable;
	private String path;

	public List<GameObject> getConstant() {
		return constant;
	}

	public void setConstant(List<GameObject> constant) {
		this.constant = constant;
	}

	public List<GameObject> getMovable() {
		return movable;
	}

	public void setMovable(List<GameObject> movable) {
		this.movable = movable;
	}

	public List<GameObject> getControlable() {
		return controlable;
	}

	public void setControlable(List<GameObject> controlable) {
		this.controlable = controlable;
	}

	public String getPath() {
		return path;
	}

	/**
	 * @return the mapMovable
	 */
	public Map<String, List<GameObject>> getMapMovable() {
		return mapMovable;
	}

	/**
	 * @param mapMovable
	 *            the mapMovable to set
	 */
	public void setMapMovable(Map<String, List<GameObject>> mapMovable) {
		this.mapMovable = mapMovable;
	}

}
