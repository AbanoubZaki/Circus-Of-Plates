package eg.edu.alexu.csd.oop.game.cs01.objects;

import java.awt.image.BufferedImage;
import java.io.File;

import eg.edu.alexu.csd.oop.game.GameObject;

public class FallenObject extends AbstractGameObject{

	private String path;
	
	public FallenObject(int x, int y, File[] imageFiles) {
		super(x, y, imageFiles);
		path = imageFiles[0].getParent();
	}
	public FallenObject(int x , int y , BufferedImage[] images) {
		super(images);
		setX(x);
		setY(y);
	}
	
	public String getPath() {
		return path;
	}
	
	public GameObject clone () {
		GameObject newObject = new FallenObject(getX(), getY() ,getSpriteImages());
		return newObject;
	}
}
