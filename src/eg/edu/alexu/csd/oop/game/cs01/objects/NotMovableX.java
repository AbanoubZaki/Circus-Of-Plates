package eg.edu.alexu.csd.oop.game.cs01.objects;

public class NotMovableX implements IMovableX {
	private int initialX;
	public NotMovableX(int x) {
		this.initialX=x;
	}
	@Override
	public int setX(int x) {
		// TODO Auto-generated method stub
		return initialX;
	}

}
