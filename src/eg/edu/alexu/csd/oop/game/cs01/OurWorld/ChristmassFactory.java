package eg.edu.alexu.csd.oop.game.cs01.OurWorld;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.cs01.objects.Background;
import eg.edu.alexu.csd.oop.game.cs01.objects.Character;

public class ChristmassFactory extends AbstractModeFactory{

	public ChristmassFactory() {
		setMode(GameMode.christmass);
	}
	
	@Override
	public void buildConstant() {
		File folder = new File(".\resources\\christmass\\constant");
		List<GameObject>list = new ArrayList<>();
		list.add(new Background(0, 0 ,folder.list()));
		setConstant(list);
	}

	@Override
	public void buildMovable() {
		
	}

	@Override
	public void buildControlable() {
		File folder = new File(".\resources\\christmass\\controlable");
		List<GameObject>list = new ArrayList<>();
		list.add(new Character(0, 0 ,folder.list()));
		setConstant(list);
	}
	
}
