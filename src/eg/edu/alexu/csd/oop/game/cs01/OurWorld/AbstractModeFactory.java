package eg.edu.alexu.csd.oop.game.cs01.OurWorld;

import java.io.File;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;

public abstract class AbstractModeFactory implements IModeAbstractFactory {

	private List<GameObject> constant;
	private List<GameObject> movable;
	private List<GameObject> controlable;
	private GameMode mode;

	@Override
	public abstract void buildConstant();

	@Override
	public abstract void buildMovable();

	@Override
	public abstract void buildControlable();

	@Override
	public GameMode getMode() {
		return mode;
	}

	@Override
	public void setMode(GameMode mode) {
		this.mode = mode;
	}
	
	@Override
	public List<GameObject> getConstant() {
		return constant;
	}

	@Override
	public void setConstant(List<GameObject> constant) {
		this.constant = constant;
	}

	@Override
	public List<GameObject> getMovable() {
		return movable;
	}

	@Override
	public void setMovable(List<GameObject> movable) {
		this.movable = movable;
	}

	@Override
	public List<GameObject> getControlable() {
		return controlable;
	}

	@Override
	public void setControlable(List<GameObject> controlable) {
		this.controlable = controlable;
	}
	
}
