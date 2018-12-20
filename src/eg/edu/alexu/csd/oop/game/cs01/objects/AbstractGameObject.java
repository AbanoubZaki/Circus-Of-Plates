package eg.edu.alexu.csd.oop.game.cs01.objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import eg.edu.alexu.csd.oop.game.GameObject;

public abstract class AbstractGameObject implements GameObject {

	private int x;
	private int y;
	private int width;
	private int height;
	private boolean visible;
	private BufferedImage[] images;
	private IMovableX movableX;
	private IMovableY movableY;

	/**
	 * @param movableX
	 *            the movableX to set
	 */
	public void setMovableX(IMovableX movableX) {
		this.movableX = movableX;
	}

	/**
	 * @param movableY
	 *            the movableY to set
	 */
	public void setMovableY(IMovableY movableY) {
		this.movableY = movableY;
	}

	public AbstractGameObject(int x, int y, int width, int height) {
		this.height = height;
		this.width = width;
		this.movableX = new MovableX();
		this.movableY = new MovableY();
		this.x = x;
		this.y = y;
		movableX.setX(x);
		movableY.setY(y);
		visible = true;
	}

	public AbstractGameObject(BufferedImage[] images) {
		this.movableX = new MovableX();
		this.movableY = new MovableY();
		this.images = images;
		visible = true;

	}

	public AbstractGameObject(int x, int y, File[] imageFiles) {
		this.movableX = new MovableX();
		this.movableY = new MovableY();
		this.x = x;
		this.y = y;
		movableX.setX(x);
		movableY.setY(y);
		visible = true;
		try {
			images = new BufferedImage[175];
			BufferedImage[] image = new BufferedImage[imageFiles.length];
			for (int i = 0; i < imageFiles.length; i++) {
				try {
					image[i] = ImageIO.read(imageFiles[i]);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (imageFiles[0].getParent().contains("backgrounds")) {
				for (int i = 0; i < images.length; i++) {
					images[i] = image[i / 25];
				}
			} else {
				images = image;
			}
		} catch (Exception e) {
		}
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int x) {
		this.x = movableX.setX(x);
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int y) {
		this.y = movableY.setY(y);
	}

	@Override
	public int getWidth() {
		try {
			return images[0].getWidth();
		} catch (Exception e) {
			return width;
		}
	}

	@Override
	public int getHeight() {
		try {
			return images[0].getHeight();
		} catch (Exception e) {
			return height;
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
