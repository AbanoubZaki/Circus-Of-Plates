package eg.edu.alexu.csd.oop.game.cs01.OurWorld;

import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;

public enum GameMode {
	christmass(), robot();
	
	private  List<GameObject> constant;
	private  List<GameObject> movable;
	private  List<GameObject> controlable;
	
	public List<GameObject> getConstant() {
		return constant;
	}
	public void setConstant(List<GameObject> constant) {
		this.constant = constant;
	}
	public List<GameObject> getMovable() {
		return movable;
	}
	public void setMovable(List<GameObject> movable) {
		this.movable = movable;
	}
	public List<GameObject> getControlable() {
		return controlable;
	}
	public void setControlable(List<GameObject> controlable) {
		this.controlable = controlable;
	}
	
}
