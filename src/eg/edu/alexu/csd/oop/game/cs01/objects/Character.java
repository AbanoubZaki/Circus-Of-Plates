package eg.edu.alexu.csd.oop.game.cs01.objects;

import java.io.File;

import eg.edu.alexu.csd.oop.game.GameObject;

public class Character extends AbstractGameObject {
	
	//According to dimensions and properties of the clown shape.
	private final int CONSTANT_X = 137;
	private final int CONSTANT_Y = 10;
	private GameObject leftStack;
	private GameObject rightStack;
	
	public Character(int x, int y, File[] imageFiles) {
		super(x, y, imageFiles);
		/**
		 * x & y are the positions of the top of the empty stack.
		 */
		leftStack = new CharacterStack(x - CONSTANT_X, y + CONSTANT_Y, null);
		rightStack = new CharacterStack(x + CONSTANT_X, y + CONSTANT_Y, null);
	}

	@Override
	public void setX(int x) {
		super.setX(x);
		leftStack.setX(x - CONSTANT_X);
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
	
}
