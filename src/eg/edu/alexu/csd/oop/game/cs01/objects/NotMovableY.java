package eg.edu.alexu.csd.oop.game.cs01.objects;

public class NotMovableY implements IMovableY {

	private int initialY;

	public NotMovableY(int y) {
		this.initialY=y;
	}

	@Override
	public int setY(int y) {
		// TODO Auto-generated method stub
		return initialY;
	}

}
