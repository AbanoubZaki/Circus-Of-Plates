package Strategy;

public class NotMovableX implements IMovableX {
	private int initialX;

	public NotMovableX() {
	}

	public NotMovableX(int x) {
		this.initialX = x;
	}

	@Override
	public int setX(int x) {
		// TODO Auto-generated method stub
		return initialX;
	}

}
