package eg.edu.alexu.csd.oop.game.cs01.objects;

import java.util.Stack;

import eg.edu.alexu.csd.oop.game.GameObject;

public class CharacterStack extends AbstractGameObject {

	private final int MAX_FALLEN_OBJECTS = 15;
	private Stack<GameObject> stack;

	public CharacterStack(int x, int y, String[] paths) {
		super(x, y, paths);
		stack = new Stack<>();
	}

	@Override
	public void setX(int x) {
		for (int i = 0; i < stack.size(); i++) {
			stack.get(i).setX(x);
		}
	}

	public Score addFallenObject(GameObject fallenObject) {
		if (CheckColors(fallenObject)) {
			removeFallenObject();
			return Score.win;
		}
		if (stack.size() < MAX_FALLEN_OBJECTS) {
			stack.add(fallenObject);
			return Score.noChange;
		}
		return Score.lose;
	}

	/**
	 * the previous 2 plates were of the same color;
	 */
	private void removeFallenObject() {
		try {
			stack.pop();
			stack.pop();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private boolean CheckColors(GameObject fallenObject) {
		try {
			if (((FallenObject) stack.get(stack.size() - 1)).getPath()
					.equals(((FallenObject) fallenObject).getPath())
					&& ((FallenObject) stack.get(stack.size() - 2)).getPath()
					.equals(((FallenObject) fallenObject).getPath())) {
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

}
