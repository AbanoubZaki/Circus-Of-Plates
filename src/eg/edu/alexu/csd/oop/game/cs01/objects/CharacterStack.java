package eg.edu.alexu.csd.oop.game.cs01.objects;

import java.util.List;
import java.util.Stack;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.cs01.Enums.ObjectType;
import eg.edu.alexu.csd.oop.game.cs01.Enums.Score;
import eg.edu.alexu.csd.oop.game.cs01.SnapShot.CharacterStackSnapShot;
import eg.edu.alexu.csd.oop.game.cs01.SnapShot.FallenObjectSnapShot;
import eg.edu.alexu.csd.oop.game.cs01.Strategy.MovableXCondition;
import eg.edu.alexu.csd.oop.game.cs01.Strategy.NotMovableY;

public class CharacterStack extends AbstractGameObject {

	private final int MAX_FALLEN_OBJECTS = 15;
	private Stack<GameObject> stack;
	private List<GameObject> controlable;
	private ObjectType type;

	public CharacterStack() {
	}

	public CharacterStack(int x, int y, ObjectType type) {
		super(x, y, 35, 0);
		stack = new Stack<>();
		this.type = type;
	}

	public Score addFallenObject(GameObject fallenObject, List<GameObject> controlable, int backGroundWidth,
			int noOfCharacter) {
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
			((AbstractGameObject) fallenObject).setMovableY(new NotMovableY(fallenObject.getY()));
			((AbstractGameObject) fallenObject).setMovableX(new MovableXCondition(backGroundWidth,
					controlable.get(noOfCharacter).getWidth(), type, fallenObject.getX()));
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

	public int getSize() {
		return stack.size();
	}

	public CharacterStackSnapShot getSnapShot() {
		return new CharacterStackSnapShot(this);
	}

	public void loadCharacterStack(CharacterStackSnapShot snapShot) {
		this.loadGameObject(snapShot);
		stack = new Stack<GameObject>();
		for (int i = 0; i < snapShot.getStack().size(); i++) {
			FallenObject o = new FallenObject();
			o.loadFallenObject((FallenObjectSnapShot) snapShot.getStack().get(i));
			stack.add(o);
		}
		this.type = snapShot.getType();
	}

	public ObjectType getType() {
		return this.type;
	}

	/**
	 * @return the stack
	 */
	public Stack<GameObject> getStack() {
		return stack;
	}

}
