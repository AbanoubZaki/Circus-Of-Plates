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
		setMovableY(new NotMovableY(y));
		
		/**
		 * x & y are the positions of the top of the empty stack.
		 */
		leftStack = new CharacterStack(x, y, StackType.left);
		rightStack = new CharacterStack(x + CONSTANT_X, y, StackType.right);
	}

	@Override
	public void setX(int x) {
		indexOfClown = x < getX() ? 0 : 1;
		super.setX(x);
		leftStack.setX(x);
		rightStack.setX(x + CONSTANT_X);
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
