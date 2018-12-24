package eg.edu.alexu.csd.oop.game.cs01.SnapShot;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.cs01.objects.AbstractGameObject;

public class AbstractSnapShot {

	private int x;
	private int y;

	private int width;
	private int height;

	/**
	 * @return the paths
	 */
	public String[] getPaths() {
		return paths;
	}

	private String[] paths;

	public AbstractSnapShot() {
	}

	public AbstractSnapShot(GameObject gameObject) {
		this.x = gameObject.getX();
		this.y = gameObject.getY();
		this.width = gameObject.getWidth();
		this.height = gameObject.getHeight();
		if (gameObject instanceof AbstractGameObject) {
			try {
				this.paths = new String[((AbstractGameObject) gameObject).getImageFiles().length];
				for (int i = 0; i < ((AbstractGameObject) gameObject).getImageFiles().length; i++) {
					paths[i] = ((AbstractGameObject) gameObject).getImageFiles()[i].getPath();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
}
