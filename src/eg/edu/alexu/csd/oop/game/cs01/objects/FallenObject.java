package eg.edu.alexu.csd.oop.game.cs01.objects;

import java.io.File;

public class FallenObject extends AbstractGameObject{

	private String path;
	
	public FallenObject(int x, int y, File[] imageFiles) {
		super(x, y, imageFiles);
		path = imageFiles[0].getParent();
	}
	
	public String getPath() {
		return path;
	}
}
