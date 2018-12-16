package eg.edu.alexu.csd.oop.game.cs01.objects;

public class FallenObject extends AbstractGameObject{

	private String path;
	
	public FallenObject(int x, int y, String[] paths) {
		super(x, y, paths);
		path = paths[0];
	}
	
	public String getPath() {
		return path;
	}
}
