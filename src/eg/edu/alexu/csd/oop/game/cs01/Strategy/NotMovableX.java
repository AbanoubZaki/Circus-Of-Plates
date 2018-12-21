package eg.edu.alexu.csd.oop.game.cs01.Strategy;

public class NotMovableX implements IMovableX {
	private int initialX;

	public NotMovableX() {
	}

	public NotMovableX(int x) {
		this.initialX = x;
	}

	@Override
	public int setX(int x) {
		return initialX;
	}

}
