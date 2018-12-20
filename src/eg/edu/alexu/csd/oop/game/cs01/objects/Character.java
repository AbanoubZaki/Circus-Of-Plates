package eg.edu.alexu.csd.oop.game.cs01.objects;

import java.awt.image.BufferedImage;
import java.io.File;

import Strategy.NotMovableY;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.cs01.Enums.ObjectType;
import eg.edu.alexu.csd.oop.game.cs01.observer.ObservableX;
import eg.edu.alexu.csd.oop.game.cs01.observer.ObserverX;

public class Character extends AbstractGameObject {

	// According to dimensions and properties of the clown shape.
	private final int CONSTANT_X = 150;
	private GameObject leftStack;
	private GameObject rightStack;
	private int indexOfClown;
	private ObjectType type;

	public Character() {
		leftStack = new CharacterStack();
		rightStack = new CharacterStack();
	}

	public Character(int x, int y, File[] imageFiles, ObjectType type) {
		super(x, y, imageFiles);
		indexOfClown = 0;
		this.setType(type);
		setMovableY(new NotMovableY(y));
		ObservableX.getInstance().setX(x);
		/**
		 * x & y are the positions of the top of the empty stack.
		 */
		leftStack = new CharacterStack(x, y, ObjectType.left);
		rightStack = new CharacterStack(x + CONSTANT_X, y, ObjectType.right);
	}

	@Override
	public void setX(int x) {
		ObservableX.getInstance().setX(x);
		if (ObserverX.getInstance().getDifference() == 0 || ObserverX.getInstance().getDifference() > 375) {
			indexOfClown = x < getX() ? 0 : 1;
			super.setX(x);
			leftStack.setX(x);
			rightStack.setX(x + CONSTANT_X);
		}
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

	public ObjectType getType() {
		return type;
	}

	public void setType(ObjectType type) {
		this.type = type;
	}

}
