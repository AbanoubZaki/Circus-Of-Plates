package eg.edu.alexu.csd.oop.game.cs01.Iterator;

import java.util.Iterator;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;

public class GameObjectCollection implements IGameObjectCollection {

	private List<GameObject> list;

	public GameObjectCollection(List<GameObject> list) {
		this.list = list;
	}

	@Override
	public void add(GameObject o) {
		list.add(o);
	}

	@Override
	public void remove(GameObject o) {
		list.remove(o);
	}

	@Override
	public void remove(int index) {
		list.remove(index);
	}

	@Override
	public GameObject get(int index) {

		return list.get(index);
	}

	@Override
	public int size() {

		return list.size();
	}

	@Override
	public Iterator<GameObject> iterator() {

		return new GameObjectIterator(this);
	}

	@Override
	public List<GameObject> getList() {
		// TODO Auto-generated method stub
		return list;
	}

}
