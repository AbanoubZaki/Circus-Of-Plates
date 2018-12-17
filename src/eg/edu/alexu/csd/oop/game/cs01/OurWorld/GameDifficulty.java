package eg.edu.alexu.csd.oop.game.cs01.OurWorld;

public enum GameDifficulty {
	
	easy(20 , 4) , medium(10, 7), hard(5, 10);
	
	private int speed;
	private int colorsOfFallenObjects;
	
	private GameDifficulty(int speed, int colorsOfFallenObjects) {
		this.speed = speed;
		this.colorsOfFallenObjects = colorsOfFallenObjects;
	}

	public int getSpeed() {
		return speed;
	}

	public int getColorsOfFallenObjects() {
		return colorsOfFallenObjects;
	}
}
