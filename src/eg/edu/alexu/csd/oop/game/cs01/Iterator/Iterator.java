package eg.edu.alexu.csd.oop.game.cs01.Iterator;

import java.util.ArrayList;

import eg.edu.alexu.csd.oop.game.GameObject;

public class Iterator implements IteratorInterface {

	ArrayList<GameObject> objects;

	public Iterator() {
		objects = new ArrayList<GameObject>();
	}
	
	
	@Override
	public java.util.Iterator createIterator() {
		// TODO Auto-generated method stub
		return objects.iterator();
	}

}
