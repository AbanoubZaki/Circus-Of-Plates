package eg.edu.alexu.csd.oop.game.cs01.SnapShot;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.cs01.objects.AbstractFallenObject;

public class FallenObjectSnapShot extends AbstractSnapShot {
	private String color;
	private String className;

	public FallenObjectSnapShot() {
		super();
	}

	public FallenObjectSnapShot(GameObject gameObject) {
		super(gameObject);
		this.color = (String) ((AbstractFallenObject) gameObject).getColor();
		this.className = gameObject.getClass().getName().substring(gameObject.getClass().getName().lastIndexOf(".") + 1)
				.toLowerCase();
	}

	/**
	 * @return the path
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

}
