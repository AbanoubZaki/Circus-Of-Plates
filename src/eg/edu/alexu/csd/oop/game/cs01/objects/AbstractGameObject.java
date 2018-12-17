package eg.edu.alexu.csd.oop.game.cs01.objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import eg.edu.alexu.csd.oop.game.GameObject;

public abstract class AbstractGameObject implements GameObject {

	private int x;
	private int y;
	private boolean visible;
	private BufferedImage[] images;

	public AbstractGameObject( BufferedImage[] images) {
		this.images = images;
	}
	
	public AbstractGameObject(int x, int y, File[] imageFiles) {
		this.x = x;
		this.y = y;
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
			if (imageFiles[0].getParent().contains("constant")) {
				for (int i = 0; i < images.length; i++) {
					images[i] = image[i / 25];
				}
			}else {
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
