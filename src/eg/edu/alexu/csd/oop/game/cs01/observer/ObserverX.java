package eg.edu.alexu.csd.oop.game.cs01.observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ObserverX implements PropertyChangeListener {

	private int x;
	private static ObserverX instance;

	private ObserverX() {
	}

	public static ObserverX getInstance() {
		if (instance == null) {
			instance = new ObserverX();
		}
		return instance;
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub
		this.setDiiference(Math.abs((int) arg0.getNewValue() - (int) arg0.getOldValue()));
	}

	public int getDifference() {
		return x;
	}

	private void setDiiference(int x) {
		this.x = x;
	}

}
