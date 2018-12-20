package eg.edu.alexu.csd.oop.game.cs01.observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ObservableX {
	private int x;
	private PropertyChangeSupport support;
	private static ObservableX instance;

	private ObservableX() {
		support = new PropertyChangeSupport(this);
	}

	public static ObservableX getInstance() {
		if (instance == null) {
			instance = new ObservableX();
		}
		return instance;
	}

	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		support.addPropertyChangeListener(pcl);

	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		support.removePropertyChangeListener(pcl);
	}

	public void setX(int x) {
		support.firePropertyChange("x", this.x, x);
		this.x = x;
	}

}
