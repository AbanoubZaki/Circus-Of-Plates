package eg.edu.alexu.csd.oop.game.cs01.Strategy;

public class NotMovableY implements IMovableY {

	private int initialY;

	public NotMovableY() {
	}

	public NotMovableY(int y) {
		this.initialY = y;
	}

	@Override
	public int setY(int y) {
		return initialY;
	}

}
