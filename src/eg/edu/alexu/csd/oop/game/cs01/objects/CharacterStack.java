package eg.edu.alexu.csd.oop.game.cs01.objects;

import java.util.List;
import java.util.Stack;

import eg.edu.alexu.csd.oop.game.GameObject;

public class CharacterStack extends AbstractGameObject {

	private final int MAX_FALLEN_OBJECTS = 15;
	private Stack<GameObject> stack;
	private List<GameObject> controlable;

	public CharacterStack(int x, int y) {
		super(x, y, 25, 0);
		stack = new Stack<>();
	}

	// @Override
	// public void setX(int x) {
	// for (int i = 0; i < stack.size(); i++) {
	// stack.get(i).setX(x);
	// }
	// }
	
	public Score addFallenObject(GameObject fallenObject, List<GameObject> controlable) {
		this.controlable = controlable;
		if (CheckColors(fallenObject)) {
			removeFallenObject();
			return Score.win;
		}
		if (stack.size() < MAX_FALLEN_OBJECTS) {
			setY(getY() - 7);
			fallenObject.setX(getX());
			fallenObject.setY(getY());
			stack.push(fallenObject);
			this.controlable.add(fallenObject);
			return Score.noChange;
		}
		return Score.lose;
	}

	/**
	 * the previous 2 plates were of the same color;
	 */
	private void removeFallenObject() {
		try {
			this.controlable.remove(stack.pop());
			this.controlable.remove(stack.pop());
			setY(getY() + 14);
		} catch (Exception e) {
		}
	}

	private boolean CheckColors(GameObject fallenObject) {
		try {
			if (((FallenObject) stack.get(stack.size() - 1)).getPath().equals(((FallenObject) fallenObject).getPath())
					&& ((FallenObject) stack.get(stack.size() - 2)).getPath()
							.equals(((FallenObject) fallenObject).getPath())) {
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

}
