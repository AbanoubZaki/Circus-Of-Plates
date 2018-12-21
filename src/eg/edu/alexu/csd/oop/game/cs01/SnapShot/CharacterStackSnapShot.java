package eg.edu.alexu.csd.oop.game.cs01.SnapShot;

import java.util.Stack;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.cs01.Enums.ObjectType;
import eg.edu.alexu.csd.oop.game.cs01.objects.CharacterStack;;

public class CharacterStackSnapShot extends AbstractSnapShot {

	private Stack<FallenObjectSnapShot> stack;
	private ObjectType type;

	public CharacterStackSnapShot() {
		super();
	}

	public CharacterStackSnapShot(GameObject gameObject) {
		super(gameObject);
		this.type = ((CharacterStack) gameObject).getType();
		stack = new Stack<FallenObjectSnapShot>();
		for (int i = 0; i < ((CharacterStack) gameObject).getSize(); i++) {
			FallenObjectSnapShot snapShot = new FallenObjectSnapShot(((CharacterStack) gameObject).getStack().get(i));
			stack.add(snapShot);
		}
	}

	/**
	 * @return the stack
	 */
	public Stack<FallenObjectSnapShot> getStack() {
		return stack;
	}

	/**
	 * @return the type
	 */
	public ObjectType getType() {
		return type;
	}

}
