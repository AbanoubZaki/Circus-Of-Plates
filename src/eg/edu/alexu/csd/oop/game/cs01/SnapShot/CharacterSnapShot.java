package eg.edu.alexu.csd.oop.game.cs01.SnapShot;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.cs01.Enums.ObjectType;
import eg.edu.alexu.csd.oop.game.cs01.ModeFactory.GameMode;
import eg.edu.alexu.csd.oop.game.cs01.objects.Character;
import eg.edu.alexu.csd.oop.game.cs01.objects.CharacterStack;

public class CharacterSnapShot extends AbstractSnapShot {

	private CharacterStackSnapShot left, right;
	private ObjectType type;
	private GameMode mode;

	/**
	 * @return the mode
	 */
	public GameMode getMode() {
		return mode;
	}

	public CharacterSnapShot() {
		super();
	}

	public CharacterSnapShot(GameObject gameObject) {
		super(gameObject);
		this.left = ((CharacterStack) ((Character) gameObject).getLeftStack()).getSnapShot();
		this.right = ((CharacterStack) ((Character) gameObject).getRightStack()).getSnapShot();
		this.type = ((Character) gameObject).getType();
		this.mode = ((Character) gameObject).getMode();
	}

	/**
	 * @return the type
	 */
	public ObjectType getType() {
		return type;
	}

	/**
	 * @return the left
	 */
	public CharacterStackSnapShot getLeft() {
		return left;
	}

	/**
	 * @return the right
	 */
	public CharacterStackSnapShot getRight() {
		return right;
	}

}
