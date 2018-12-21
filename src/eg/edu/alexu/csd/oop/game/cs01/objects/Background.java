package eg.edu.alexu.csd.oop.game.cs01.objects;

import java.io.File;

import eg.edu.alexu.csd.oop.game.cs01.SnapShot.BackGroundSnapShot;

public class Background extends AbstractGameObject {

	public Background(int x, int y, File[] imageFiles) {
		super(x, y, imageFiles);
	}

	public Background() {
	}

	/**
	 * @return the snapShot
	 */
	public BackGroundSnapShot getSnapShot() {
		return new BackGroundSnapShot(this);
	}

	public void loadBackGround(BackGroundSnapShot snapShot) {
		this.loadGameObject(snapShot);
	}
}
