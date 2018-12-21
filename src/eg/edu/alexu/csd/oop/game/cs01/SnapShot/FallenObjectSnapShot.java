package eg.edu.alexu.csd.oop.game.cs01.SnapShot;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.cs01.objects.FallenObject;

public class FallenObjectSnapShot extends AbstractSnapShot {
	private String path;

	public FallenObjectSnapShot() {
		super();
	}

	public FallenObjectSnapShot(GameObject gameObject) {
		super(gameObject);
		this.path = ((FallenObject) gameObject).getPath();
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

}
