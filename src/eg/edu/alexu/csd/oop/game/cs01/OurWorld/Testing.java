package eg.edu.alexu.csd.oop.game.cs01.OurWorld;


import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.cs01.objects.cs01.ModeFactory.GameMode;
import eg.edu.alexu.csd.oop.game.cs01.objects.cs01.ModeFactory.ModeFactory;

public class Testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameEngine.start("Circus of plates", new OurGame(GameDifficulty.hard , ModeFactory.getInstance(GameMode.christmass).createMode()));

	}

}
