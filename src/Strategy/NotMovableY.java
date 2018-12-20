package Strategy;

public class NotMovableY implements IMovableY {

	private int initialY;

	public NotMovableY() {
	}

	public NotMovableY(int y) {
		this.initialY = y;
	}

	@Override
	public int setY(int y) {
		// TODO Auto-generated method stub
		return initialY;
	}

}
