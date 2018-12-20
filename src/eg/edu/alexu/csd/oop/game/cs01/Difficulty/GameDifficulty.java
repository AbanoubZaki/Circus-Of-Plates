package eg.edu.alexu.csd.oop.game.cs01.Difficulty;

import eg.edu.alexu.csd.oop.game.GameObject;

public enum GameDifficulty {
	
	easy(10, 3, 1) , medium(7, 4, 1), hard(1, 5, 2);
	
	private int speed;
	private int colorsOfFallenObjects;
	private int noOfCharacters;
	
	private GameDifficulty(int speed, int colorsOfFallenObjects, int noOfCharacters) {
		this.speed = speed;
		this.colorsOfFallenObjects = colorsOfFallenObjects;
		this.noOfCharacters = noOfCharacters;
		
	}

	public int getSpeed() {
		return speed;
	}

	public int getColorsOfFallenObjects() {
		return colorsOfFallenObjects;
	}
	
	public int getNoOfCharacters() {
		return noOfCharacters;
	}
	
	public void moveHard (GameObject o, int MovingStrategy) {
		if(MovingStrategy==0) {
			o.setX(o.getX()+1);
			MovingStrategy=1;
		} else if(MovingStrategy==1) {
			o.setX(o.getX()-1);
			MovingStrategy=0;
		}
	}
}
