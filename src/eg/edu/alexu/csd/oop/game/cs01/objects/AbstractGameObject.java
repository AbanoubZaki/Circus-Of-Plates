package eg.edu.alexu.csd.oop.game.cs01.objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.cs01.Logger4J.OurLogger;
import eg.edu.alexu.csd.oop.game.cs01.SnapShot.AbstractSnapShot;
import eg.edu.alexu.csd.oop.game.cs01.Strategy.IMovableX;
import eg.edu.alexu.csd.oop.game.cs01.Strategy.IMovableY;
import eg.edu.alexu.csd.oop.game.cs01.Strategy.MovableX;
import eg.edu.alexu.csd.oop.game.cs01.Strategy.MovableY;

public class AbstractGameObject implements GameObject {

	private int x;
	private int y;
	private int width;
	private int height;

	private File[] imageFiles;

	/**
	 * @return the imageFiles
	 */
	public File[] getImageFiles() {
		return imageFiles;
	}

	/**
	 * @param imageFiles
	 *            the imageFiles to set
	 */
	public void setImageFiles(File[] imageFiles) {
		this.imageFiles = imageFiles;
	}

	/**
	 * @param images
	 *            the images to set
	 */
	public void setImages(BufferedImage[] images) {
		this.images = images;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @param visible
	 *            the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

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

	public AbstractGameObject() {
		this.movableX = new MovableX();
		this.movableY = new MovableY();
		images = new BufferedImage[175];
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
		this.setImageFiles(imageFiles);
		loadImages();

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

	public void loadGameObject(AbstractSnapShot snapShot) {
		this.setHeight(snapShot.getHeight());
		this.setWidth(snapShot.getWidth());
		this.setX(snapShot.getX());
		this.setY(snapShot.getY());
		this.setVisible(true);
		this.setMovableX(new MovableX());
		this.setMovableY(new MovableY());
		try {
			File[] images = new File[snapShot.getPaths().length];
			for (int i = 0; i < snapShot.getPaths().length; i++) {
				images[i] = new File(snapShot.getPaths()[i]);

			}
			this.setImageFiles(images);
			loadImages();
		} catch (Exception e) {
			OurLogger.error(getClass(), e.getMessage());
		}
	}

	private void loadImages() {
		try {
			BufferedImage[] image = new BufferedImage[imageFiles.length];
			for (int i = 0; i < imageFiles.length; i++) {
				try {
					image[i] = ImageIO.read(imageFiles[i]);
				} catch (IOException e) {
					e.printStackTrace();
					OurLogger.error(getClass(), e.getMessage());
				}
			}
			if (imageFiles[0].getParent().contains("backgrounds")) {
				images = new BufferedImage[175];
				for (int i = 0; i < images.length; i++) {
					images[i] = image[i / 25];
				}
			} else {
				images = image;
			}
		} catch (Exception e) {
			OurLogger.error(getClass(), e.getMessage());
		}
	}

}
