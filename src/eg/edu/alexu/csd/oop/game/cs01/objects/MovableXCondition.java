package eg.edu.alexu.csd.oop.game.cs01.objects;

public class MovableXCondition implements IMovableX {

	private int backgroundWidth;
	private int characterWidth;
	private StackType type;
	private int oldX;

	public MovableXCondition(int backgroundWidth, int characterWidth, StackType type) {
		this.backgroundWidth = backgroundWidth;
		this.characterWidth = characterWidth;
		this.type = type;
	}

	@Override
	public int setX(int x) {
		if (type == StackType.left && x > backgroundWidth - characterWidth) {
			return oldX;
		}
		if (type == StackType.right && x < characterWidth-40) {
			return oldX;
		}
		return oldX = x;
	}

}
