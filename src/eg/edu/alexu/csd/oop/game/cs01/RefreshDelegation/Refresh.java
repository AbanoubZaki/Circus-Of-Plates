package eg.edu.alexu.csd.oop.game.cs01.RefreshDelegation;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.cs01.objects.AbstractFallenObject;

public class Refresh implements IRefresh {

	private static Refresh r;

	private Refresh() {
	}

	public static Refresh getInstance() {
		if (r == null) {
			r = new Refresh();
		}
		return r;
	}

	@Override
	public boolean intersect(GameObject o2, GameObject o1) {
		return ((o1.getX() < o2.getX() + o2.getWidth()) && (o1.getX() > o2.getX() - o2.getWidth())
				&& (o1.getY() < o2.getY() + o2.getHeight()) && (o1.getY() > o2.getY() - o2.getHeight()));
	}

	@Override
	public boolean intersectWithGood(GameObject o, GameObject gameObject, GameObject gameObject2) {
		return (((AbstractFallenObject) o).getColor().equals("0")
				&& (intersect(o, gameObject) || intersect(o, gameObject2)));
	}

	@Override
	public boolean intersectWithBad(GameObject o, GameObject leftStack, GameObject rightStack) {
		return (((AbstractFallenObject) o).getColor().equals("1")
				&& (intersect(o, leftStack) || intersect(o, rightStack)));
	}

	@Override
	public boolean intersectWithPlateLeft(GameObject o, GameObject leftStack) {
		return intersect(o, leftStack);
	}

	@Override
	public boolean intersectWithPlateRight(GameObject o, GameObject rightStack) {
		return intersect(o, rightStack);
	}

}
