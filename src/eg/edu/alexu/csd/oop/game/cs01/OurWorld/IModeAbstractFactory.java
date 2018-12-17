package eg.edu.alexu.csd.oop.game.cs01.OurWorld;

import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;

public interface IModeAbstractFactory {
	public void buildConstant();
	public void buildMovable();
	public void buildControlable();
	public GameMode getMode();
	public void setMode (GameMode mode);
	public List<GameObject> getConstant();
	public void setConstant(List<GameObject> constant);
	public List<GameObject> getMovable();
	public void setMovable(List<GameObject> movable);
	public List<GameObject> getControlable();
	public void setControlable(List<GameObject> controlable);
}
