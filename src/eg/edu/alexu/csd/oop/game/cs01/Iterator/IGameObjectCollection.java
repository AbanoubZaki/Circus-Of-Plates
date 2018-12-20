package eg.edu.alexu.csd.oop.game.cs01.Iterator;

import java.util.Iterator;

import eg.edu.alexu.csd.oop.game.GameObject;

public interface IGameObjectCollection {

	void add(GameObject o);

	void remove(GameObject o);

	void remove(int index);

	GameObject get(int index);

	int size();

	Iterator<GameObject> iterator();
}
