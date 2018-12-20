package eg.edu.alexu.csd.oop.game.cs01.objects;

import java.awt.image.BufferedImage;
import java.io.File;

import eg.edu.alexu.csd.oop.game.GameObject;

public class FallenObject extends AbstractGameObject {

	private String path;

	public FallenObject() {
	}

	public FallenObject(int x, int y, File[] imageFiles) {
		super(x, y, imageFiles);
		this.path = imageFiles[0].getParent();
	}

	public FallenObject(int x, int y, BufferedImage[] images, String path) {
		super(images);
		setX(x);
		setY(y);
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public GameObject clone() {
		return new FallenObject(getX(), getY(), getSpriteImages(), getPath());
	}
}
