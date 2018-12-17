package eg.edu.alexu.csd.oop.game.cs01.OurWorld;

public interface IModeFactory {
	public void buildConstant();
	public void buildMovable();
	public void buildControlable();
	public GameMode createMode();
}
