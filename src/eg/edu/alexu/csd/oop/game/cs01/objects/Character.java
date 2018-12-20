package eg.edu.alexu.csd.oop.game.cs01.objects;

import java.awt.image.BufferedImage;
import java.io.File;

import Strategy.NotMovableY;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.cs01.Enums.ObjectType;
import eg.edu.alexu.csd.oop.game.cs01.objects.cs01.ModeFactory.GameMode;
import eg.edu.alexu.csd.oop.game.cs01.observer.ObservableX;
import eg.edu.alexu.csd.oop.game.cs01.observer.ObserverX;

public class Character extends AbstractGameObject {

	// According to dimensions and properties of the clown shape.
	private final int CONSTANT_X = 150;
	private GameObject leftStack;
	private GameObject rightStack;
	private int indexOfClown;
	private ObjectType type;
	private GameMode mode;

	public Character() {
		leftStack = new CharacterStack();
		rightStack = new CharacterStack();
	}

	public Character(int x, int y, File[] imageFiles, ObjectType type, GameMode mode) {
		super(x, y, imageFiles);
		indexOfClown = 0;
		this.setType(type);
		setMode(mode);
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
			if (mode == GameMode.christmass) {
				indexOfClown = x < getX() ? 0 : 1;
			} else {
				indexOfClown = 0;
			}
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

	public GameMode getMode() {
		return mode;
	}

	private void setMode(GameMode mode) {
		this.mode = mode;
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
