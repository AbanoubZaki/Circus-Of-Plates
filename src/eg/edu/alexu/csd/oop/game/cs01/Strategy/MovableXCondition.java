package eg.edu.alexu.csd.oop.game.cs01.Strategy;

import eg.edu.alexu.csd.oop.game.cs01.Enums.ObjectType;

public class MovableXCondition implements IMovableX {

	private int backgroundWidth;
	private int characterWidth;
	private ObjectType stackType;
	private int oldX;
	public MovableXCondition() {
	}
	public MovableXCondition(int backgroundWidth, int characterWidth, ObjectType type, int oldX) {
		this.backgroundWidth = backgroundWidth;
		this.characterWidth = characterWidth;
		this.stackType = type;
		this.oldX = oldX;
	}

	@Override
	public int setX(int x) {
		if (stackType == ObjectType.left && x > backgroundWidth - characterWidth) {
			return oldX;
		}
		if (stackType == ObjectType.right && x < characterWidth - 40) {
			return oldX;
		}
		return oldX = x;
	}

}
