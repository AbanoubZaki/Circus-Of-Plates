package eg.edu.alexu.csd.oop.game.cs01.objects;

import java.awt.image.BufferedImage;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.cs01.Strategy.IMovableX;
import eg.edu.alexu.csd.oop.game.cs01.Strategy.IMovableY;
import eg.edu.alexu.csd.oop.game.cs01.Strategy.MovableX;
import eg.edu.alexu.csd.oop.game.cs01.Strategy.MovableY;

public abstract class AbstractFallenObject implements GameObject, Cloneable {
	private int x;
	private int y;
	private boolean visible;
	private BufferedImage[] images;
	private IMovableX movableX;
	private IMovableY movableY;

	public AbstractFallenObject() {
		this.movableX = new MovableX();
		this.movableY = new MovableY();
		this.images = new BufferedImage[10];
		this.visible = true;
	}

	public AbstractFallenObject(Integer x, Integer y, BufferedImage[] images) {
		this.movableX = new MovableX();
		this.movableY = new MovableY();
		this.x = x;
		this.y = y;
		movableX.setX(x);
		movableY.setY(y);
		visible = true;
		this.images = images;
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
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public void setY(int y) {
		this.y = movableY.setY(y);
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		try {
			return images[0].getWidth();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		try {
			return images[0].getHeight();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return visible;
	}

	@Override
	public BufferedImage[] getSpriteImages() {
		// TODO Auto-generated method stub
		return images;
	}

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

	@Override
	public abstract Object clone() throws CloneNotSupportedException;

	public abstract Object getColor();

	public abstract void setColor(Object color);

	public abstract Object getsnapShot();

	public abstract void loadFallenObject(Object snapShot);

	/**
	 * @param visible
	 *            the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * @param images
	 *            the images to set
	 */
	public void setImages(BufferedImage[] images) {
		this.images = images;
	}
}
