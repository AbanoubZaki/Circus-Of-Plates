package eg.edu.alexu.csd.oop.game.cs01.objects;

import java.awt.image.BufferedImage;
import java.io.File;

import eg.edu.alexu.csd.oop.game.GameObject;

public class Character extends AbstractGameObject {

	// According to dimensions and properties of the clown shape.
	private final int CONSTANT_X = 150;
	private GameObject leftStack;
	private GameObject rightStack;
	private int indexOfClown;

	public Character(int x, int y, File[] imageFiles) {
		super(x, y, imageFiles);
		indexOfClown = 0;
		/**
		 * x & y are the positions of the top of the empty stack.
		 */
		leftStack = new CharacterStack(x, y);
		rightStack = new CharacterStack(x + CONSTANT_X, y);
	}

	@Override
	public void setX(int x) {
		indexOfClown = x < getX() ? 0 : 1;
		super.setX(x);
		leftStack.setX(x);
		rightStack.setX(x + CONSTANT_X);
	}

	/**
	 * empty method as the character doesn't in y direction;
	 */
	@Override
	public void setY(int y) {
	}

	public GameObject getLeftStack() {
		return leftStack;
	}

	public GameObject getRightStack() {
		return rightStack;
	}

	@Override
	public BufferedImage[] getSpriteImages() {
		return new BufferedImage[] { super.getSpriteImages()[indexOfClown] };
	}

}
