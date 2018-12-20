package eg.edu.alexu.csd.oop.game.cs01.Iterator;

import java.util.Iterator;

import eg.edu.alexu.csd.oop.game.GameObject;

public class GameObjectIterator implements Iterator<GameObject> {

	private IGameObjectCollection list;
	private int index;

	public GameObjectIterator(IGameObjectCollection list) {
		this.list = list;
		index = 0;
	}

	@Override
	public boolean hasNext() {
		return index < list.size();
	}

	@Override
	public GameObject next() {
		if (this.hasNext()) {
			return list.get(index++);
		}
		return null;
	}

}
