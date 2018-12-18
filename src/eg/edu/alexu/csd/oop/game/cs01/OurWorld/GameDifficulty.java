package eg.edu.alexu.csd.oop.game.cs01.OurWorld;

public enum GameDifficulty {
	
	easy(20 , 3) , medium(10, 4), hard(5, 5);
	
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
