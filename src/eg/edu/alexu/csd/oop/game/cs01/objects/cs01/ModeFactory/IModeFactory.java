package eg.edu.alexu.csd.oop.game.cs01.objects.cs01.ModeFactory;

public interface IModeFactory {
	public void buildConstant();
	public void buildMovable();
	public void buildControlable();
	public void buildTracks();
	public GameMode createMode();
}
