package eg.edu.alexu.csd.oop.game.cs01.Iterator;

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorClass {

	Iterator iterator;
	IteratorInterface iter;
	
	public IteratorClass(IteratorInterface newiterator) {
		iter  = newiterator;
	}
	
	public void showTheObjects() {
		Iterator iterator = iter.createIterator();
	}	
}
