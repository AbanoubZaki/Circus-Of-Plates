package eg.edu.alexu.csd.oop.game.cs01.objects;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import eg.edu.alexu.csd.oop.game.GameObject;

public abstract class AbstractGameObject implements GameObject {

	private int x;
	private int y;
	private boolean visible;
	private BufferedImage[] images;

	public AbstractGameObject(int x, int y, String[] paths) {
		setX(x);
		setY(y);
		visible = true;
		images = new BufferedImage[paths.length];
		for (int i = 0; i < paths.length; i++) {
			try {
				this.images[i] = ImageIO.read(getClass().getResourceAsStream(paths[i]));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int getWidth() {
		try {
			return images[0].getWidth();
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int getHeight() {
		try {
			return images[0].getHeight();
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public BufferedImage[] getSpriteImages() {
		return images;
	}

}
