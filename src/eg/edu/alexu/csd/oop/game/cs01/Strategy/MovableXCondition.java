package eg.edu.alexu.csd.oop.game.cs01.Strategy;

import eg.edu.alexu.csd.oop.game.GameObject;

public class MovableXCondition implements IMovableX {

	GameObject stack;

	public MovableXCondition(GameObject stack) {
		this.stack = stack;
	}

	@Override
	public int setX(int x) {
		return stack.getX();
	}

}
