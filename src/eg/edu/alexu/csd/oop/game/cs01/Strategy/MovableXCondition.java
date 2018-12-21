package eg.edu.alexu.csd.oop.game.cs01.Strategy;

import eg.edu.alexu.csd.oop.game.cs01.objects.CharacterStack;

public class MovableXCondition implements IMovableX {

	CharacterStack stack;

	public MovableXCondition(CharacterStack stack) {
		this.stack = stack;
	}

	@Override
	public int setX(int x) {
		// TODO Auto-generated method stub
		return stack.getX();
	}

}
