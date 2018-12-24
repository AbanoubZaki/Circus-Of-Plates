package eg.edu.alexu.csd.oop.game.cs01.GameStates;

public interface IState {

	void action();
	CurrentState getType();
}
