package eg.edu.alexu.csd.oop.game.cs01.RefreshDelegation;

import eg.edu.alexu.csd.oop.game.GameObject;

public interface IRefresh {
	public boolean intersect (GameObject o2, GameObject o1);
	public boolean intersectWithGood (GameObject o, GameObject leftStack, GameObject rightStack);
	public boolean intersectWithBad (GameObject o, GameObject leftStack, GameObject rightStack);
	public boolean intersectWithPlateLeft(GameObject o, GameObject leftStack);
	public boolean intersectWithPlateRight(GameObject o, GameObject rightStack);
}
